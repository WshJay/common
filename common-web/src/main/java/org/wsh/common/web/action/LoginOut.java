package org.wsh.common.web.action;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 登出
 * File Name: <LoginOut.java>
 * Comments:  <注销登录>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-15 上午11:14:42
 */
@Controller
public class LoginOut {

	private static Logger log = LoggerFactory.getLogger(HomePageAction.class);
	
	/**
	 * 登出
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/loginOut")
	public String goHomePage(Model model, HttpServletRequest request, HttpServletResponse response) {
		log.info("登出...");
		// 清空cookie
		Cookie cookie = new Cookie("userName", null);
		cookie.setMaxAge(0);
		cookie.setPath(request.getContextPath().length() > 0 ? request
				.getContextPath() : "/");
		response.addCookie(cookie);
		// shiro登出
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.logout();
		// TODO 清空缓存
		
		return "redirect:/login.html";
	}
}

