package com.xxx.common.repository.dao ;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.RoleDO;
import com.xxx.common.repository.query.RoleQuery;

public interface RoleDAO extends SqlMapper {
	
	/**
	 * 获取所有角色信息
	 */
	List<RoleDO> queryAll();
	
	/**
	 * 多条件查询角色信息(分页)
	 * @param roleDO
	 * @param rowBounds
	 * @return
	 */
	List<RoleDO> queryByParams(RoleDO roleDO, RowBounds rowBounds);
	int queryCountByParams(RoleDO roleDO);
	
	/**
	 * 插入角色信息
	 * @param roleDO
	 * @return
	 */
	int insertRole(RoleDO roleDO);
	
	/**
	 * 修改角色信息
	 * @param roleDO
	 * @return
	 */
	int updateRole(RoleDO roleDO);

	/**
	 * 根据角色ID查询角色信息
	 * @param roleId
	 * @return
	 */
	public RoleDO loadById(Long roleId);
	
	/**
	 * 根据用户ID删除角色信息
	 * @param roleId
	 */
	void deleteById(Long roleId);
	
	/**
	 * 根据用户ID查询用户角色信息
	 * @param userId
	 * @return
	 */
	List<RoleDO> queryListByUserId(Long userId);
	
}
