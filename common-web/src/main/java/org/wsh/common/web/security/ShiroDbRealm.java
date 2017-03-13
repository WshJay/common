package org.wsh.common.web.security;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wsh.common.model.basic.PermissionDO;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.service.api.UserService;
import org.wsh.common.util.shiro.encrypt.Encodes;

/**
 * Shiro用户校验及权限认证
 * File Name: <ShiroDbRealm.java>
 * Comments:  <用户登录后密码加密与数据库中的密码相比较>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015-4-1 下午5:11:46
 */
public class ShiroDbRealm extends AuthorizingRealm {
	
	private Logger log = LoggerFactory.getLogger(getClass());
	
	public static final int INTERATIONS = 1024;
	private static final String ALGORITHM = "SHA-1";

	@Autowired
	private UserService userService;
	
	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 记住密码
		token.setRememberMe(true);
		log.info("认证信息...");
		// 判断用户名是否为空
		if (StringUtils.isEmpty(token.getUsername())) {
			return null;
		}
		// 根据用户名查询用户信息
		UserBasicDO user = userService.getUserBasicByUserName(token.getUsername());
		if (user == null) {
			return null;
		}
		// 密钥
		byte[] salt = Encodes.decodeHex(user.getSalt());
		return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(),
				ByteSource.Util.bytes(salt), getName());
	}

	/**
	 * 授权查询回调函数
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		String userName = String.valueOf(principals);
		log.info("授权查询回调函数 userName" + userName);
		UserBasicDO user = userService.getUserRoleByUserName(userName);
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (RoleDO roleDO : user.getRoleList()) {
				log.info("用户角色名" + roleDO.getRoleName());
				// 基于Role的权限信息
				info.addRole(roleDO.getRoleCode());
				List<PermissionDO> permissionsList = new ArrayList(user.getPermissionSet());
				// 加入权限认证
				for (PermissionDO permission : permissionsList) {
					log.info("用户权限" + permission.getName() + "权限code" + permission.getCode());
					info.addStringPermission(permission.getCode());
				}
			}
			return info;
		} else {
			return null;
		}
	}
	
	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 自定义密码加密方式
	 * 此方法表示在表单提交后自动为密码加密，不需要写代码加密
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		log.info("Init HashedCredentialsMatcher...");
		// 指定散列算法，需要和生成密码时的一样
//		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
//		// 散列迭代次数，需要和生成密码时的一样
//		matcher.setHashIterations(INTERATIONS);
		LoginRetryLimit matcher = new LoginRetryLimit("classpath:shiro-ehcache.xml");
		matcher.setHashAlgorithmName(ALGORITHM);
		matcher.setHashIterations(INTERATIONS);
		setCredentialsMatcher(matcher);
	}

}
