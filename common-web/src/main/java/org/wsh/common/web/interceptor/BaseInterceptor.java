package org.wsh.common.web.interceptor;

import org.springframework.beans.factory.annotation.Value;
import org.wsh.common.web.util.SessionUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.wsh.common.enums.response.ModelKey;
import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.util.ip.IpUtil;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.util.List;

/**
 * 拦截器
 * File Name: <BaseInterceptor.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-2 上午11:58:21
 */
public class BaseInterceptor extends HandlerInterceptorAdapter{
	
	private static Logger log = LoggerFactory.getLogger(BaseInterceptor.class);
	
	@Autowired 
	private MenuService menuService;

	@Value("#{system.serverHost}")
	private String SERVER_HOST;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {

		log.info("SERVER_HOST=>" + SERVER_HOST);
//		String queryString = request.getQueryString();
    	
		log.info("IP: " + IpUtil.getIpAddr(request));
		if (!StringUtils.isBlank(request.getQueryString())) {
			StringBuffer accessAddressBuffer = new StringBuffer();
			accessAddressBuffer.append(SERVER_HOST).append(request.getRequestURI()).append("?").append(URLDecoder.decode(request.getQueryString(), "UTF-8"));
			log.info("URL: " + accessAddressBuffer.toString());
		}else{
			log.info("URL: " + SERVER_HOST + request.getRequestURI());
		}
		// 从Cookie中获取用户信息
		String userName=null;
		Cookie[] cookies=request.getCookies();
		if(cookies!=null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("userName")){
					userName = URLDecoder.decode(cookie.getValue(),"UTF-8");
				}
			}
		}
		// 如果未获取到用户信息则跳往登录页面
		if (userName == null || !SessionUtil.isLogin(request)) {
			// 设置跳转到登录页必须保证login.html请求不经过此拦截器，否则将形成死循环
			response.sendRedirect("/login.html");
			return false;
		}
		String requestURL = request.getRequestURL().toString();
		requestURL = requestURL.replace(SERVER_HOST, "");
		List<MenuDO> menuList = menuService.getMenuList(userName, requestURL);
		if (!CollectionUtils.isEmpty(menuList)) {
			request.setAttribute(ModelKey.requestMenus.name(), menuList);
		}
		
//		boolean isHavePerm = menuService.validatePermission(userName, requestURL);
//		if (isHavePerm == false) {
//			response.sendRedirect("/403.html");
//			return false;
//		}
		return true;
	}
    
    

}
