package org.wsh.common.web.action.common.ajax;

import static org.wsh.common.web.util.SessionUtil.getCurrentUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wsh.common.enums.SessionKey;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.service.api.RoleService;
import org.wsh.common.service.api.UserService;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

@Controller
@RequestMapping("/ajax/user")
public class User {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private HttpServletRequest request;
	
	private static Logger log = LoggerFactory.getLogger(User.class);

	/**
	 * 到添加用户页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/add")
	public String toAdd(Model model){
		try {
			List<RoleDO> roleList = roleService.getAllRole();
			model.addAttribute("roleList", roleList);
		} catch (BusinessException e) {
			log.error("查询所有角色信息异常",e);
		}
		return "user/add";
	}
	
	/**
	 * 添加用户
	 * @param userBasicDO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addUser")
	public @ResponseBody
	ResponseDO doAddUser(UserBasicDO userBasicDO){
		ResponseDO responseDO = null;
		if (userBasicDO != null) {
			try {
				UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
				responseDO = userService.doAddUser(currentUser.getId(),userBasicDO.getUserName(), userBasicDO.getPassword(), userBasicDO.getRealName(), userBasicDO.getPhone(), userBasicDO.getEmail(), userBasicDO.getRoleId());
			} catch (BusinessException e) {
				log.error("添加用户异常",e);
			}
		}
		return responseDO;
	}
	
	/**
	 * 查看用戶詳情
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/detail")
	public String showDetail(Model model,@RequestParam Long userId){
		if (userId > 0) {
			try {
				UserBasicDO user = userService.getUserByUserId(userId);
				model.addAttribute("user", user);
			} catch (BusinessException e) {
				log.error("查看用戶詳情异常",e);
			}
		}
		return "user/detail";
	}
	
	/**
	 * 用户编辑页面
	 * @param model
	 * @param userId
	 * @return
	 */
	@RequestMapping("/edit")
	public String toEdit(Model model,@RequestParam Long userId){
		if (userId > 0) {
			try {
				UserBasicDO user = userService.getUserRoleByUserId(userId);
				model.addAttribute("user", user);
				List<RoleDO> roleList = roleService.getAllRole();
				model.addAttribute("roleList", roleList);
			} catch (BusinessException e) {
				log.error("用户编辑页面异常",e);
			}
		}
		return "user/edit";
	}
	
	/**
	 * 修改用户信息
	 * @param model
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateUser")
	public @ResponseBody ResponseDO editUser(UserBasicDO user, Long roleId){
		ResponseDO responseDO = null;
		if (user != null) {
			try {
				UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
				responseDO = userService.doUpdateUser(currentUser.getId(),user.getId(), user.getRealName(), user.getPhone(), user.getEmail(),roleId);
			} catch (BusinessException e) {
				log.warn("修改用户信息异常", e);
			}
		}
		return responseDO;
	}
	
	/**
	 * 删除用户
	 * @param model
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delUser")
	public @ResponseBody ResponseDO delUser(Model model,Long userId){
		ResponseDO responseDO = null;
		try {
			responseDO = userService.doDelUserById(userId);
		} catch (BusinessException e) {
			log.error("删除用户异常",e);
		}
		return responseDO;
	}
}

