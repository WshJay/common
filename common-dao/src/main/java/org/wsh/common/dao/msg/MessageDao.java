package org.wsh.common.dao.msg;

import org.wsh.common.model.msg.MessageDO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageDao {
	
	/**
	 * 根据ID查询表数据
	 * @param id
	 * @return
	 */
	public MessageDO loadById(Long id);
	
	/**
	 * 插入信息
	 * @param messageDO 
	 * @return
	 */
	int insert(MessageDO messageDO);
	
	/**
	 * 根据ID删除信息
	 * @param id
	 */
	void deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param List<MessageDO>
	 */
	void insertList(List<MessageDO> messageDOList);
	
}
