package com.xxx.common.server.service.impl;

import static com.xxx.common.server.util.BeansUtil.newOptionsResponseDO;
import static com.xxx.common.server.util.BeansUtil.newStaticOptionsResponseDO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xxx.common.repository.dao.ModuleDAO;
import com.xxx.common.repository.data.beans.OptionsResponseDO;
import com.xxx.common.repository.data.support.ResponseDO;
import com.xxx.common.repository.entity.ModuleDO;
import com.xxx.common.repository.pagination.Pagination;
import com.xxx.common.server.service.ModuleService;
import com.xxx.common.util.exception.BusinessException;
@Service("moduleService")
public class ModuleServiceImpl implements ModuleService{
	
	@Autowired
	private ModuleDAO moduleDAO;

	@Override
	public List<ModuleDO> getAllModule() throws BusinessException {
		try {
			List<ModuleDO> moduleList = moduleDAO.queryAll();
			return moduleList;
		} catch (Exception e) {
			throw new BusinessException("获取所有模块信息异常",e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public OptionsResponseDO<List<ModuleDO>> getModuleListByParams(
			ModuleDO moduleDO, Pagination pagination) throws BusinessException {
		try {
			int totalCount = moduleDAO.queryCountByParams(moduleDO);
			if (totalCount <= 0) {
				return newStaticOptionsResponseDO();
			}
			pagination.setTotalCount(totalCount);
			List<ModuleDO> moduleList = moduleDAO.queryByParams(moduleDO, pagination.getRowBounds());
			return newOptionsResponseDO(totalCount, moduleList, pagination.getPageSize(),pagination.getPP());
		} catch (Exception e) {
			throw new BusinessException("多条件查询模块信息异常",e);
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doAddModule(Long currentUserId, String moduleName)
			throws BusinessException {
		try {
			// TODO validata
			ModuleDO moduleDO = new ModuleDO();
			moduleDO.setCreateUserId(currentUserId);
			moduleDO.setName(moduleName);
			moduleDAO.insertModule(moduleDO);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("添加模块信息异常，参数currentUserId:[%s],name:[%s]", currentUserId, moduleName));
		}
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ResponseDO doDeleteModule(Long currentUserId, Long moduleId)
			throws BusinessException {
		try {
			// TODO validata
			// TODO 修改权限中的模块信息
			moduleDAO.deleteById(moduleId);
			return newStaticOptionsResponseDO();
		} catch (Exception e) {
			throw new BusinessException(String.format("删除模块信息异常，参数currentUserId:[%s],moduleId:[%s]", currentUserId, moduleId));
		}
	}

	
}

