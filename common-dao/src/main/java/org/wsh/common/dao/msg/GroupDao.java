package org.wsh.common.dao.msg;

import org.wsh.common.model.msg.GroupDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupDao {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public GroupDO loadById(int id);
	
	/**
	 * 插入信息
	 * @param groupDO 
	 * @return
	 */
	int insert(GroupDO groupDO);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(int id);
	
	/**
	 * 批量添加信息
	 * @param List<GroupDO>
	 */
	void insertList(List<GroupDO> groupDOList);
	
}
