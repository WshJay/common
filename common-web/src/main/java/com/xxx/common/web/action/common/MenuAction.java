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
import com.xxx.common.repository.entity.MenuDO;
import com.xxx.common.repository.entity.ModuleDO;
import com.xxx.common.repository.entity.PermissionDO;
import com.xxx.common.repository.entity.UserBasicDO;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.repository.query.MenuQuery;
import com.xxx.common.server.service.MenuService;
import com.xxx.common.server.service.PermissionService;
import com.xxx.common.util.exception.BusinessException;

@Controller
@RequestMapping("/menu")
public class MenuAction {

	@Autowired
	private MenuService menuService;
	
	@Autowired
	private PermissionService permissionService;
	
	private final Logger log = LoggerFactory.getLogger(MenuAction.class);

	/**
	 * 栏目列表页
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String getUserList(Model model, Pagination pagination, MenuDO menuDO, HttpServletRequest request){
		try {
			newModelParameters(model, menuDO);
			newModelQuerys(model, request, menuService.getMenuListByParams(menuDO, pagination));
		} catch (BusinessException e) {
			log.error("权限列表页异常",e);
		}
		return "/menu/list";
	}
	
	/**
	 * 添加栏目页
	 * @param menuDO
	 * @return
	 */
	@RequestMapping("/add")
	public String add(Model model){
		List<PermissionDO> permList = permissionService.getAllPermission();
		List<MenuDO> rootMenuList = menuService.getAllRootMenu();
		model.addAttribute("permList", permList);
		model.addAttribute("rootMenuList", rootMenuList);
		return "/menu/add";
	}
	
	/**
	 * 添加栏目
	 * @param menuDO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addMenu")
	public @ResponseBody ResponseDO addMenu(MenuDO menuDO, HttpServletRequest request){
		ResponseDO responseDO = null;
		if (menuDO != null) {
			try {
				UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
				responseDO = menuService.doAddMenu(currentUser.getId(), menuDO.getName(), menuDO.getResId(), menuDO.getFatherId());
			} catch (BusinessException e) {
				log.error("添加栏目异常",e);
			}
		}
		return responseDO;
	}
	
	/**
	 * 获得编辑栏目信息
	 * @param menuId
	 * @return
	 */
	@RequestMapping("/edit")
	public String toEdit(Model model, @RequestParam Long menuId){
		try {
			if (menuId > 0) {
				MenuDO menuDO = menuService.getMenuById(menuId);
				List<PermissionDO> permList = permissionService.getAllPermission();
				List<MenuDO> rootMenuList = menuService.getAllRootMenu();
				newModelParameters(model, menuDO);
				model.addAttribute("permList", permList);
				model.addAttribute("rootMenuList", rootMenuList);
			}
		} catch (BusinessException e) {
			log.error("获得编辑栏目信息异常",e);
		}
 		return "/menu/edit";
	}
	
	/**
	 * 修改栏目信息
	 * @param menuDO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/updateMenu")
	public @ResponseBody ResponseDO update(Model model, MenuDO menuDO,HttpServletRequest request){
		ResponseDO responseDO = null;
		try {
			UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
			responseDO = menuService.doUpdateMenu(currentUser.getId(), menuDO.getId(), menuDO.getName(), menuDO.getResId(), menuDO.getFatherId());
		} catch (BusinessException e) {
			log.error("修改栏目信息异常",e);
		}
		return responseDO;
	}
	
	/**
	 * 删除栏目
	 * @param model
	 * @param menuId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delMenu")
	public @ResponseBody ResponseDO delete(Model model,Long menuId,HttpServletRequest request){
		ResponseDO responseDO = null;
		try {
			UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
			responseDO = menuService.doDelMenu(currentUser.getId(), menuId);
		} catch (BusinessException e) {
			log.error("删除栏目异常",e);
		}
		return responseDO;
	}
}

