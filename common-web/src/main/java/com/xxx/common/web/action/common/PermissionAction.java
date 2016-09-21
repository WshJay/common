package com.xxx.common.web.action.common;

import static com.xxx.common.web.util.ModelUtil.newModelParameters;
import static com.xxx.common.web.util.ModelUtil.newModelQuerys;
import static com.xxx.common.web.util.SessionUtil.getCurrentUser;

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

import com.xxx.common.repository.data.enums.SessionKey;
import com.xxx.common.repository.data.support.ResponseDO;
import com.xxx.common.repository.entity.ModuleDO;
import com.xxx.common.repository.entity.PermissionDO;
import com.xxx.common.repository.entity.UserBasicDO;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.server.service.ModuleService;
import com.xxx.common.server.service.PermissionService;
import com.xxx.common.util.exception.BusinessException;

@Controller
@RequestMapping("/permission")
public class PermissionAction {
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private ModuleService moduleService;
	
	private final Logger log = LoggerFactory.getLogger(PermissionAction.class);

	/**
	 * 权限列表页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String getPermissionList(Model model, Pagination pagination, PermissionDO permissionDO, HttpServletRequest request){
		try {
			newModelParameters(model, permissionDO);
			newModelQuerys(model, request, permissionService.getPermissionListByParams(permissionDO, pagination));
		} catch (BusinessException e) {
			log.error("权限列表页异常",e);
		}
		return "/permission/list";
	}
	
	/**
	 * 到添加权限页面
	 * @param userBasicDO
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model,HttpServletRequest request){
		try {
			List<ModuleDO> moduleList = moduleService.getAllModule();
			model.addAttribute("moduleList", moduleList);
		} catch (BusinessException e) {
			log.error("添加权限异常",e);
		}
		return "/permission/add";
	}
	
	/**
	 * 添加权限
	 * @param userBasicDO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addPermission")
	public @ResponseBody ResponseDO addPermission(PermissionDO permissionDO,HttpServletRequest request){
		ResponseDO responseDO = null;
		if (permissionDO != null) {
			try {
				UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
				responseDO = permissionService.doAddPermission(currentUser.getId(), permissionDO.getName(), permissionDO.getCode(), permissionDO.getTarget(), permissionDO.getAllow(), permissionDO.getType(), permissionDO.getModuleId());
			} catch (BusinessException e) {
				log.error("添加权限异常",e);
			}
		}
		return responseDO;
	}
	
	/**
	 * 获得编辑权限信息
	 * @param roleDO
	 * @return
	 */
	@RequestMapping("/edit")
	public String toEdit(Model model, @RequestParam Long permissionId){
		try {
			if (permissionId > 0) {
				PermissionDO permissionDO = permissionService.getPermissionById(permissionId);
				List<ModuleDO> moduleList = moduleService.getAllModule();
				model.addAttribute("moduleList", moduleList);
				model.addAttribute("permission", permissionDO);
			}
		} catch (BusinessException e) {
			log.error("获得编辑权限信息异常",e);
		}
 		return "/permission/edit";
	}
	
	/**
	 * 修改角色的权限信息
	 * @param roleDO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updatePerm")
	public @ResponseBody ResponseDO updatePermission(Model model, PermissionDO permissionDO,HttpServletRequest request){
		ResponseDO responseDO = null;
		try {
			UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
			responseDO = permissionService.doUpdatePermission(currentUser.getId(), permissionDO.getName(), permissionDO.getCode(), permissionDO.getTarget(), permissionDO.getAllow(), permissionDO.getType(), permissionDO.getModuleId(),permissionDO.getId());
		} catch (BusinessException e) {
			log.error("修改角色的权限信息异常",e);
		}
		return responseDO;
	}
	
	/**
	 * 删除权限
	 * @param model
	 * @param roleId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delPermission")
	public @ResponseBody ResponseDO deleteRole(Model model,Long permissionId,HttpServletRequest request){
		ResponseDO responseDO = null;
		try {
			UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
			responseDO = permissionService.doDelPermission(currentUser.getId(), permissionId);
		} catch (BusinessException e) {
			log.error("删除权限异常",e);
		}
		return responseDO;
	}
	
}

