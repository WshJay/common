package org.wsh.common.service.api;

import org.wsh.common.model.basic.ModuleDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.exception.BusinessException;
import org.wsh.common.support.response.ResponseDO;

import java.util.List;


public interface ModuleService {
	
	/**
	 * 多条件查询模块信息
	 * @param moduleDO
	 * @param pagination
	 * @return
	 */
	public OptionsResponseDO<List<ModuleDO>> getModuleListByParams(ModuleDO moduleDO, Pagination pagination) throws BusinessException;

	/**
	 * 添加模块信息
	 * @param currentUserId
	 * @param moduleName
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doAddModule(Long currentUserId, String moduleName)throws BusinessException;
	
	/**
	 * 根据模块ID删除模块信息
	 * @param currentUserId
	 * @param moduleId
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public ResponseDO doDeleteModule(Long currentUserId, Long moduleId)throws BusinessException;
	
	/**
	 * 获取所有模块信息
	 * @return
	 * @throws BusinessException
	 */
	public List<ModuleDO> getAllModule()throws BusinessException;
	
}

