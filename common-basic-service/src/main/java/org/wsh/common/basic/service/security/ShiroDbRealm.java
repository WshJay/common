package org.wsh.common.basic.service.security;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.CollectionUtils;
import org.apache.shiro.web.filter.mgt.DefaultFilterChainManager;
import org.apache.shiro.web.filter.mgt.NamedFilterList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.wsh.common.model.basic.PermissionDO;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.model.basic.RolePermissionDO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.service.api.PermissionService;
import org.wsh.common.service.api.RoleService;
import org.wsh.common.service.api.UserService;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.util.shiro.encrypt.Encodes;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;

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

	private static final int INTERATIONS = 1024;
	private static final String ALGORITHM = "SHA-1";
	
	@Resource
	private UserService userService;

	/**
	 * 认证信息
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		log.info("认证信息...");
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		// 记住密码
		token.setRememberMe(true);
		// 判断用户名是否为空
		if (StringUtils.isEmpty(token.getUsername())) {
			return null;
		}
//		 根据用户名查询用户信息
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
	public void initCredentialsMatcher() throws Exception{
		log.info("自定义密码加密方式...");
		// 指定散列算法，需要和生成密码时的一样
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(ALGORITHM);
		// 散列迭代次数，需要和生成密码时的一样
		matcher.setHashIterations(INTERATIONS);
		setCredentialsMatcher(matcher);

		try {
			List<RoleDO> roleList = roleService.getAllRole();
			for (RoleDO roleDO : roleList) {
				System.out.println(roleDO.getRoleName());
			}
			List<RolePermissionDO> rolePermissionList = permissionService.getRolePermissionList();
			List<PermissionDO> permissionList = permissionService.getAllPermission();
			for (RoleDO roleDO : roleList) {
				for (RolePermissionDO rolePermissionDO : rolePermissionList) {
					if (roleDO.getId().equals(rolePermissionDO.getRoleId())) {
						for (PermissionDO permissionDO : permissionList) {
							if (rolePermissionDO.getPermissionId().equals(permissionDO.getId())) {
								if (CollectionUtils.isEmpty(roleDO.getPermissionSet())) {
									roleDO.setPermissionSet(new HashSet<PermissionDO>());
								}else{
									roleDO.getPermissionSet().add(permissionDO);
								}
							}
						}
					}
				}
			}
			initFilterChains(roleList, permissionList);
			log.info("filterChainManager",filterChainManager);
		} catch (BusinessException e) {
			log.error("初始化权限异常:",e);
			throw e;
		}
	}

	@Autowired
	private DefaultFilterChainManager filterChainManager;

	@Autowired
	private RoleService roleService;

	@Autowired
	private PermissionService permissionService;

	private LinkedHashMap<String, NamedFilterList> defaultFilterChains;

	public void initFilterChains(List<RoleDO> roleList, List<PermissionDO> permissionList) {
		//1、首先删除以前老的filter chain并注册默认的
		filterChainManager.getFilterChains().clear();
		if(defaultFilterChains != null) {
			filterChainManager.getFilterChains().putAll(defaultFilterChains);
		}
		//2、注册filter chain
		for (RoleDO roleDO : roleList) {
			if (!CollectionUtils.isEmpty(roleDO.getPermissionSet())) {
				for (PermissionDO permissionDO : roleDO.getPermissionSet()) {
					filterChainManager.addToChain(permissionDO.getTarget(), "roles", roleDO.getRoleCode());
				}
			}
		}
		for (PermissionDO permissionDO : permissionList) {
			filterChainManager.addToChain(permissionDO.getTarget(), "perms", permissionDO.getCode());
		}
	}
	
}
