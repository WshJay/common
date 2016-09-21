package com.xxx.common.web.interceptor;

import java.net.URLDecoder;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.Setter;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.xxx.common.repository.data.enums.ModelKey;
import com.xxx.common.repository.entity.MenuDO;
import com.xxx.common.server.service.MenuService;
import com.xxx.common.util.ip.IpUtil;
import com.xxx.common.web.util.ConstantsUtil;
import com.xxx.common.web.util.SessionUtil;

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
	
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
            Object handler) throws Exception {
    	
//		String queryString = request.getQueryString();
    	
		log.info("IP: " + IpUtil.getIpAddr(request));
		if (!StringUtils.isBlank(request.getQueryString())) {
			StringBuffer accessAddressBuffer = new StringBuffer();
			accessAddressBuffer.append(ConstantsUtil.SERVER_HOST).append(request.getRequestURI()).append("?").append(URLDecoder.decode(request.getQueryString(), "UTF-8"));
			log.info("URL: " + accessAddressBuffer.toString());
		}else{
			log.info("URL: " + ConstantsUtil.SERVER_HOST + request.getRequestURI());
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
		requestURL = requestURL.replace(ConstantsUtil.SERVER_HOST, "");
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
