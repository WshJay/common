package org.wsh.common.web.security;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 用户登录次数限制 
 * File Name: <LoginRetryLimit.java> 
 * Comments: <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6>
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-3 下午2:44:50
 */
public class LoginRetryLimit extends HashedCredentialsMatcher {

	private Ehcache passwordRetryCache;

	public LoginRetryLimit() {
		// 获取缓存配置
		CacheManager cacheManager = CacheManager.newInstance(CacheManager.class
				.getClassLoader().getResource("ehcache.xml"));
		passwordRetryCache = cacheManager.getCache("passwordRetryCache");
	}

	@Override
	public boolean doCredentialsMatch(AuthenticationToken token,
			AuthenticationInfo info) {
		// 获取当前登录用户用户名
		String username = (String) token.getPrincipal();
		// 登录失败次数加1
		Element element = passwordRetryCache.get(username);
		if (element == null) {
			element = new Element(username, new AtomicInteger(0));
			passwordRetryCache.put(element);
		}
		AtomicInteger retryCount = (AtomicInteger) element.getObjectValue();
		// 如果登录失败次数大于5，锁定账号10分钟
		if (retryCount.incrementAndGet() > 5) {
			throw new ExcessiveAttemptsException();
		}
		// 若一旦登录成功则清空缓存
		boolean matches = super.doCredentialsMatch(token, info);
		if (matches) {
			passwordRetryCache.remove(username);
		}
		return matches;
	}
}
