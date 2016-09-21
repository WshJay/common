package com.xxx.common.web.action.common;

import static com.xxx.common.web.util.ModelUtil.newModelParameters;
import static com.xxx.common.web.util.ModelUtil.newModelQuerys;
import static com.xxx.common.web.util.SessionUtil.getCurrentUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.xxx.common.repository.data.enums.SessionKey;
import com.xxx.common.repository.data.support.ResponseDO;
import com.xxx.common.repository.entity.ModuleDO;
import com.xxx.common.repository.entity.PermissionDO;
import com.xxx.common.repository.entity.RoleDO;
import com.xxx.common.repository.entity.UserBasicDO;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.repository.query.RoleQuery;
import com.xxx.common.server.service.PermissionService;
import com.xxx.common.server.service.RoleService;
import com.xxx.common.util.exception.BusinessException;

@Controller
@RequestMapping("/role")
public class RoleAction {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	private final Logger log = LoggerFactory.getLogger(RoleAction.class);

	/**
	 * 角色列表页
	 * @param model
	 * @param pagination
	 * @param roleQuery
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String getUserList(Model model, Pagination pagination, RoleDO role, HttpServletRequest request){
		try {
			newModelParameters(model, role);
			newModelQuerys(model, request, roleService.getRoleListByParams(role, pagination));
		} catch (BusinessException e) {
			log.error("角色列表页异常",e);
		}
		return "/role/list";
	}
	
	/**
	 * 添加角色
	 * @param roleDO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addRole")
	public @ResponseBody ResponseDO addUser(RoleDO roleDO,HttpServletRequest request){
		ResponseDO responseDO = null;
		if (roleDO != null) {
			try {
				UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
				responseDO = roleService.doAddRole(currentUser.getId(),roleDO.getRoleName(),roleDO.getRoleCode());
			} catch (BusinessException e) {
				log.error("添加角色异常",e);
			}
		}
		return responseDO;
	}
	
	/**
	 * 角色编辑页面
	 * @param model
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/edit")
	public String toEdit(Model model,@RequestParam Long roleId){
		if (roleId > 0) {
			try {
				RoleDO roleDO = roleService.getRoleById(roleId);
				newModelParameters(model, roleDO);
			} catch (BusinessException e) {
				log.error("角色编辑页面异常",e);
			}
		}
		return "role/edit";
	}
	
	/**
	 * 修改角色信息
	 * @param model
	 * @param userId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateRole")
	public @ResponseBody ResponseDO editUser(RoleDO roleDO, HttpServletRequest request){
		ResponseDO responseDO = null;
		if (roleDO != null) {
			try {
				UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
				responseDO = roleService.doUpdateRole(currentUser.getId(), roleDO.getId(), roleDO.getRoleName(), roleDO.getRoleCode());
			} catch (BusinessException e) {
				log.warn("修改角色信息异常", e);
			}
		}
		return responseDO;
	}
	
	/**
	 * 删除角色
	 * @param model
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delRole")
	public @ResponseBody ResponseDO deleteRole(Model model,Long roleId){
		ResponseDO responseDO = null;
		try {
			responseDO = roleService.doDelRoleById(roleId);
		} catch (BusinessException e) {
			log.error("删除角色异常",e);
		}
		return responseDO;
	}
	
	/**
	 * 编辑角色的权限信息
	 * @param roleDO
	 * @return
	 */
	@RequestMapping("/perms")
	public String getPermissionList(Model model, @RequestParam Long roleId){
		if (roleId > 0) {
			List<ModuleDO> moduleDOList = roleService.getModuleAndPermsByRoleId(roleId);
//			RoleDO roleDO = roleService.getRoleAndPermsByRoleId(roleId);
			List<PermissionDO> permsList = permissionService.getAllPermission();
			model.addAttribute("roleId", roleId);
			model.addAttribute("permsList", permsList);
			model.addAttribute("moduleList", moduleDOList);
		}
 		return "/role/perms";
	}
	
	/**
	 * 给角色添加权限
	 * @param roleId
	 * @param permissionIds
	 * @return
	 */
	@RequestMapping("/addPermission")
	public @ResponseBody JSONObject addPermissionForRole(Long roleId, String permissionIds){
		JSONObject obj = new JSONObject();
		if (roleId > 0 && !StringUtils.isBlank(permissionIds)) {
			Long createUserId = 1l;
			boolean isSuccess = roleService.addPermsForRole(roleId, permissionIds, createUserId);
			if (isSuccess) {
				obj.put("success", isSuccess);
			}
		}
		return obj;
	}
}

