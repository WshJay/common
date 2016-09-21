package com.xxx.common.web.action.common;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xxx.common.repository.entity.MenuDO;
import com.xxx.common.repository.entity.RoleDO;
import com.xxx.common.repository.entity.UserBasicDO;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.repository.query.UserQuery;
import com.xxx.common.server.service.MenuService;
import com.xxx.common.server.service.RoleService;
import com.xxx.common.server.service.UserService;
import com.xxx.common.util.exception.BusinessException;

import static com.xxx.common.web.util.ModelUtil.newModelQuerys;
import static com.xxx.common.web.util.ModelUtil.newModelParameters;


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

