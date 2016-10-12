package org.wsh.common.rest.action.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wsh.common.enums.SessionKey;
import org.wsh.common.model.basic.ModuleDO;
import org.wsh.common.model.basic.UserBasicDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.service.api.ModuleService;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import javax.servlet.http.HttpServletRequest;

import static org.wsh.common.rest.util.ModelUtil.newModelParameters;
import static org.wsh.common.rest.util.ModelUtil.newModelQuerys;
import static org.wsh.common.rest.util.SessionUtil.getCurrentUser;


@Controller
@RequestMapping("/module")
public class ModuelAction {

	@Autowired
	private ModuleService moduleService;
	
	private final Logger log = LoggerFactory.getLogger(ModuelAction.class);

	/**
	 * 多条件查询模块信息
	 * @param model
	 * @param pagination
	 * @param moduleQuery
	 * @param request
	 * @return
	 */
	@RequestMapping("/list")
	public String getUserList(Model model, Pagination pagination, ModuleDO moduleDO, HttpServletRequest request){
		
		try {
			newModelParameters(model, moduleDO);
			newModelQuerys(model, request, moduleService.getModuleListByParams(moduleDO, pagination));
		} catch (BusinessException e) {
			log.error("模块列表页异常",e);
		}
		return "/module/list";
	}
	
	/**
	 * 添加模块
	 * @param moduleDO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/addModule")
	public @ResponseBody
	ResponseDO addModule(ModuleDO moduleDO, HttpServletRequest request){
		ResponseDO responseDO = null;
		if (moduleDO != null) {
			try {
				UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
				responseDO = moduleService.doAddModule(currentUser.getId(), moduleDO.getName());
			} catch (BusinessException e) {
				log.error("添加模块异常",e);
			}
		}
		return responseDO;
	}
	
	/**
	 * 删除模块
	 * @param moduleDO
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/delModule")
	public @ResponseBody ResponseDO deleteModule(Long moduleId, HttpServletRequest request){
		ResponseDO responseDO = null;
		if (moduleId > 0) {
			try {
				UserBasicDO currentUser = getCurrentUser(request, SessionKey.user);
				responseDO = moduleService.doDeleteModule(currentUser.getId(), moduleId);
			} catch (BusinessException e) {
				log.error("删除模块异常",e);
			}
		}
		return responseDO;
	}
}

