package org.wsh.common.dao.msg;

import org.wsh.common.model.msg.UserGroupDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserGroupDao {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public UserGroupDO loadById(int id);
	
	/**
	 * 插入信息
	 * @param userGroupDO 
	 * @return
	 */
	int insert(UserGroupDO userGroupDO);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param List<UserGroupDO>
	 */
	void insertList(List<UserGroupDO> userGroupDOList);
	
}
