package org.wsh.common.rest.util;

import org.wsh.common.enums.SessionKey;
import org.wsh.common.model.basic.UserBasicDO;

import javax.servlet.http.HttpServletRequest;


/**
 * SessionUtil
 * 
 * Created with IntelliJ IDEA. User: caden Date: 5/5/13 Time: 3:53 PM
 * 
 * To change this template use File | Settings | File Templates.
 */
public final class SessionUtil {

	/**
	 * SessionUtil
	 */
	private SessionUtil() {

	}

	/**
	 * isLogin
	 * @param request
	 * @return
	 */
	public static boolean isLogin(HttpServletRequest request) {
		return request.getSession().getAttribute(SessionKey.user.name()) != null;
	}
	
	/**
	 * 放入session
	 * @param request
	 * @param sessionKey
	 * @param values
	 */
	public static<T> void setSession(HttpServletRequest request, SessionKey sessionKey, T values){
		request.getSession().setAttribute(sessionKey.name(), values);
	}
	
	/**
	 * 获取当前用户
	 * @param request
	 * @param sessionKey
	 * @return
	 */
	public static UserBasicDO getCurrentUser(HttpServletRequest request, SessionKey sessionKey){
		return (UserBasicDO)request.getSession().getAttribute(sessionKey.name());
	}

}
