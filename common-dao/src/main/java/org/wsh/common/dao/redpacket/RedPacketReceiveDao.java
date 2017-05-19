package org.wsh.common.dao.redpacket;

import org.wsh.common.model.redpacket.RedPacketReceiveDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  RedPacketReceive持久层
* since Date： 2017-05-10 10:18:17
*/
@Repository
public interface RedPacketReceiveDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return RedPacketReceiveDO
	 */
	RedPacketReceiveDO selectById(Long id);

	/**
	* 多条件查询表信息
	* @param redPacketReceiveDO RedPacketReceiveDO
	* @return RedPacketReceiveDO
	*/
	RedPacketReceiveDO selectByParams(RedPacketReceiveDO redPacketReceiveDO);

	/**
	* 多条件查询表信息
	* @param redPacketReceiveDO RedPacketReceiveDO
	* @param rowBounds RowBounds
	* @return List<RedPacketReceiveDO>
	*/
	List<RedPacketReceiveDO> selectListByParams(RedPacketReceiveDO redPacketReceiveDO, RowBounds rowBounds);
	int selectCountByParams(RedPacketReceiveDO redPacketReceiveDO);
	
	/**
	 * 插入信息
	 * @param redPacketReceiveDO RedPacketReceiveDO
	 * @return int
	 */
	int insert(RedPacketReceiveDO redPacketReceiveDO);

	/**
	* 根据ID更新信息
	* @param redPacketReceiveDO RedPacketReceiveDO
	* @return int
	*/
	int updateById(RedPacketReceiveDO redPacketReceiveDO);

	/**
	* 根据ID更新删除状态
	* @param redPacketReceiveDO RedPacketReceiveDO
	* @return int
	*/
	int updateIsDeleteById(RedPacketReceiveDO redPacketReceiveDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param redPacketReceiveDOList List<RedPacketReceiveDO>
	 */
	void insertList(List<RedPacketReceiveDO> redPacketReceiveDOList);
	
}
