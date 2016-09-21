package com.xxx.common.repository.dao ;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.ModuleDO;
import com.xxx.common.repository.query.ModuleQuery;

public interface ModuleDAO extends SqlMapper {
	
	/**
	 * 获取所有模块信息
	 */
	public List<ModuleDO> queryAll();
	
	/**
	 * 根据模块ID查询模块信息
	 * @param moduleId
	 * @return
	 */
	public ModuleDO queryById(Long moduleId);
	
	/**
	 * 多条件查询模块信息
	 * @param moduleQuery
	 * @param rowBounds
	 * @return
	 */
	List<ModuleDO> queryByParams(ModuleDO moduleDO, RowBounds rowBounds);
	int queryCountByParams(ModuleDO moduleDO);
	
	/**
	 * 添加模块
	 * @param moduleDO
	 * @return
	 */
	int insertModule(ModuleDO moduleDO);
	
	/**
	 * 根据模块ID删除模块信息
	 * @param moduleId
	 */
	public void deleteById(Long moduleId);
	
}
