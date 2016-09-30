package org.wsh.common.web.action.common;

import static org.wsh.common.web.util.ModelUtil.newModelParameters;
import static org.wsh.common.web.util.ModelUtil.newModelQuerys;
import static org.wsh.common.web.util.SessionUtil.getCurrentUser;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import org.wsh.common.enums.SessionKey;
import org.wsh.common.model.basic.ModuleDO;
import org.wsh.common.model.basic.PermissionDO;
import org.wsh.common.model.basic.RoleDO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.service.api.PermissionService;
import org.wsh.common.service.api.RoleService;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

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
	 * @param role
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
	public @ResponseBody
	ResponseDO addUser(RoleDO roleDO, HttpServletRequest request){
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
	 * @param roleDO
	 * @param request
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

