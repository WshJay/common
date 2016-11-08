package org.wsh.common.rest.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.service.api.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 首页Action
 * Project:     <common-web>
 * File Name:   <UserAction.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2015-3-27 下午5:38:35
 */
@Controller
public class HomePageAction {
	
	private static Logger log = LoggerFactory.getLogger(HomePageAction.class);
	
	@Autowired
	private UserService userService;
	
	/**
	 * 首页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/index")
	public String goHomePage(Model model, UserBasicDO user, Pagination pagination, HttpServletRequest request) {
		List<UserBasicDO> userList = userService.getUserList(user, pagination);
		model.addAttribute("userList", userList);
		ModelAndView mav = new ModelAndView("pay");
		mav.addObject("html", "Test...");
		return "/index";
	}
}