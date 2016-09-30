package org.wsh.common.web.action.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.service.api.MenuService;
import org.wsh.common.service.api.RoleService;
import org.wsh.common.service.api.UserService;
import org.wsh.common.support.exception.BusinessException;

import javax.servlet.http.HttpServletRequest;

import static org.wsh.common.web.util.ModelUtil.newModelParameters;
import static org.wsh.common.web.util.ModelUtil.newModelQuerys;


/**
 * 用户管理Action
 * File Name: <UserAction.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-4-2 下午5:57:02
 */
@Controller
@RequestMapping("/user")
public class UserAction {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private MenuService menuService;
	
	@Autowired
	private RoleService roleService;
	
	private static Logger log = LoggerFactory.getLogger(UserAction.class);

	/**
	 * 用户列表页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String getUserList(Model model, Pagination pagination, UserBasicDO userBasicDO, HttpServletRequest request){
		
		newModelParameters(model, userBasicDO);
		try {
			newModelQuerys(model, request, userService.getUserListByParams(userBasicDO, pagination));
		} catch (BusinessException e) {
			log.error("用户列表查询页",e);
		}
		return "user/list";
	}
}

