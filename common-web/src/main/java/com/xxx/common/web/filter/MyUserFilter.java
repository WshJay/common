package com.xxx.common.web.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.web.filter.authc.UserFilter;


public class MyUserFilter extends UserFilter{
	
	@Override
	protected boolean onAccessDenied(ServletRequest request,
			ServletResponse response) throws Exception {
		HttpServletRequest httpServletRequest=(HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		//当session失效时
		if(!httpServletRequest.isRequestedSessionIdValid()){
			//当session失效时，请求是ajax请求
			if(httpServletRequest.getHeader("x-requested-with")!=null 
					&& httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
//				System.out.println("ajax??????????????");
				httpServletResponse.sendError(401, "Session was timeout");
				return false;
			}
			saveRequest(request);
//			System.out.println("session was timeout");
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath()+ "/login.html?f=ns");
			return false;
		}
		//当请求是ajax请求
		if(httpServletRequest.getHeader("x-requested-with")!=null 
				&& httpServletRequest.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			httpServletResponse.sendError(403, "You haven't login");
			return false;
		}
//		httpServletRequest.getSession().setAttribute("nouser", "nouser");
		return super.onAccessDenied(request, response);
	}

}
