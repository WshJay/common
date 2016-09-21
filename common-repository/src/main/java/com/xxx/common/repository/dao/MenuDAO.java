package com.xxx.common.repository.dao ;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.MenuDO;
import com.xxx.common.repository.query.MenuQuery;

public interface MenuDAO extends SqlMapper {
	
	/**
	 * 多条件查询栏目信息
	 * @param menuQuery
	 * @param rowBounds
	 * @return
	 */
	public List<MenuDO> queryByParams(MenuDO menuDO, RowBounds rowBounds);
	public int queryCountByParams(MenuDO menuDO);
	
	/**
	 * 添加栏目信息
	 * @param menuDO
	 * @return
	 */
	public int insertMenu(MenuDO menuDO);
	
	/**
	 * 修改栏目信息
	 * @param menuDO
	 * @return
	 */
	public int updateMenu(MenuDO menuDO);
	
	/**
	 * 删除栏目信息
	 * @param menuId
	 * @return
	 */
	public int deleteById(Long menuId);
	
	/**
	 * 查询所有一级栏目信息
	 * @return
	 */
	List<MenuDO> queryAllFatherMenu();
	
	/**
	 * 查询所有非一级栏目信息
	 * @return
	 */
	List<MenuDO> queryAllChildrenMenu();
	
	/**
	 * 根据栏目ID获取栏目信息
	 * @param menuId
	 * @return
	 */
	public MenuDO queryById(Long menuId);
	
}
