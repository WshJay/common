package com.xxx.common.server.service;

import java.util.List;

import com.xxx.common.repository.data.beans.OptionsResponseDO;
import com.xxx.common.repository.data.support.ResponseDO;
import com.xxx.common.repository.entity.ModuleDO;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.util.exception.BusinessException;

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

