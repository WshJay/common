package org.wsh.common.rest.action.control;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wsh.common.model.basic.MenuDO;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.service.api.UserService;

import java.util.List;

@Controller
@RequestMapping("/control")
public class ControlAction {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;
	
	private final Logger log = LoggerFactory.getLogger(ControlAction.class);

	@RequestMapping("/menu")
	public String getUserMenuList(Model model){
		
		// 获取当前用户及用户角色
		Subject subject = SecurityUtils.getSubject();
		String userName = (String)subject.getPrincipal();
		log.info("userName:" + userName);
		List<MenuDO> menuList = menuService.getMenuListByUserName(userName);
		model.addAttribute("menuList", menuList);
		return "/control/menu";
	}
}

