package org.wsh.common.dao.message ;

import org.wsh.common.model.message.MessageDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Message持久层
* since Date： 2016-11-23 14:56:02
*/
@Repository
public interface MessageDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return MessageDO
	 */
	MessageDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param messageDO MessageDO
	* @param rowBounds RowBounds
	* @return List<MessageDO>
	*/
	List<MessageDO> selectListByParams(MessageDO messageDO, RowBounds rowBounds);
	int selectCountByParams(MessageDO messageDO);
	
	/**
	 * 插入信息
	 * @param messageDO MessageDO
	 * @return int
	 */
	int insert(MessageDO messageDO);

	/**
	* 根据ID更新信息
	* @param messageDO MessageDO
	* @return int
	*/
	int updateById(MessageDO messageDO);

	/**
	* 根据ID更新删除状态
	* @param messageDO MessageDO
	* @return int
	*/
	int updateIsDeleteById(MessageDO messageDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param messageDOList List<MessageDO>
	 */
	void insertList(List<MessageDO> messageDOList);
	
}
