package org.wsh.common.web.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户登录
 * File Name: <LoginAction.java>
 * Comments:  <LoginController负责打开登录页面(GET请求)和登录出错页面(POST请求),真正登录的POST请求由Filter完成>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-1 下午5:39:11
 */
@Controller
public class LoginAction {
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(HttpServletRequest request,Model model) {
		if(SecurityUtils.getSubject().isAuthenticated()){
			model.addAttribute("authened","authened");
		}
		return "/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, @RequestParam(FormAuthenticationFilter.DEFAULT_PASSWORD_PARAM) String password, Model model,HttpServletRequest request) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "/login";
	}
	
	@RequestMapping(value="/logout")
	public String logout(Model model){
		return "/login";
	}
	
	/**
	 * 是否登录
	 * 
	 * @param target
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 */
	@RequestMapping("/islogin")
	public String isLogin(String target, HttpServletResponse response)
			throws ServletException, IOException {
		// 如果用户登录
		if (SecurityUtils.getSubject().isAuthenticated()) {
			if (target != null && !target.equals("") && !target.equals("/")) {
				return "forward:" + target;
			}
		} else {
			response.sendError(403, "You haven't login!");
		}
		return null;
	}
}
