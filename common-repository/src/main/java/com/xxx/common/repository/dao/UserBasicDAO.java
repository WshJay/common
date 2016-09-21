package com.xxx.common.repository.dao ;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.xxx.common.repository.base.SqlMapper;
import com.xxx.common.repository.entity.UserBasicDO;

public interface UserBasicDAO extends SqlMapper {
	
	/**
	 * 获取所有用户信息
	 */
	public List<UserBasicDO> queryALL();
	
	/**
	 * 多条件查询用户信息(分页)
	 * @param userBasicDO
	 * @param rowBounds
	 * @return
	 */
//	List<UserBasicDO> queryByParams(UserBasicDO userBasicDO, RowBounds rowBounds);
//	int queryCountByParams(UserBasicDO userBasicDO);
	List<UserBasicDO> queryByParams(UserBasicDO userBasicDO, RowBounds rowBounds);
	int queryCountByParams(UserBasicDO userBasicDO);
	
	/**
	 * 插入用户基本信息
	 * @param userBasicDO 
	 * @return
	 */
	int insertUserBasic(UserBasicDO userBasicDO);
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName 用户名
	 * @return
	 */
	UserBasicDO queryByUserName(String userName);
	
	/**
	 * 根据用户ID查询用户基本信息
	 * @param userId 用户ID
	 * @return
	 */
	UserBasicDO queryById(Long userId);
	
	/**
	 * 修改用户信息
	 * @param userBasicDO
	 */
	void updateUserBasic(UserBasicDO userBasicDO);
	
	/**
	 * 根据用户ID删除用户基本信息
	 * @param userId
	 */
	void deleteById(Long userId);
}
