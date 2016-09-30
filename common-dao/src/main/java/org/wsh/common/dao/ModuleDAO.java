package org.wsh.common.dao;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import org.wsh.common.model.basic.ModuleDO;

import java.util.List;

@Repository
public interface ModuleDAO{
	
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
