package org.wsh.common.dao.redpacket;

import org.wsh.common.model.redpacket.RedPacketSendDO;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  RedPacketSend持久层
* since Date： 2017-05-10 10:18:17
*/
@Repository
public interface RedPacketSendDao {

	/**
	 * 根据ID查询表数据
	 * @param id Long
	 * @return RedPacketSendDO
	 */
	RedPacketSendDO selectById(Long id);

	/**
	 * 查询信息(悲观锁)
	 * @param redPacketSendDO RedPacketSendDO
	 * @return RedPacketSendDO
     */
	RedPacketSendDO selectForCalculate(RedPacketSendDO redPacketSendDO);

	/**
	* 多条件查询表信息
	* @param redPacketSendDO RedPacketSendDO
	* @return RedPacketSendDO
	*/
	RedPacketSendDO selectByParams(RedPacketSendDO redPacketSendDO);

	/**
	* 多条件查询表信息
	* @param redPacketSendDO RedPacketSendDO
	* @param rowBounds RowBounds
	* @return List<RedPacketSendDO>
	*/
	List<RedPacketSendDO> selectListByParams(RedPacketSendDO redPacketSendDO, RowBounds rowBounds);
	int selectCountByParams(RedPacketSendDO redPacketSendDO);
	
	/**
	 * 插入信息
	 * @param redPacketSendDO RedPacketSendDO
	 * @return int
	 */
	int insert(RedPacketSendDO redPacketSendDO);

	/**
	* 根据ID更新信息
	* @param redPacketSendDO RedPacketSendDO
	* @return int
	*/
	int updateById(RedPacketSendDO redPacketSendDO);

	/**
	* 根据ID更新删除状态
	* @param redPacketSendDO RedPacketSendDO
	* @return int
	*/
	int updateIsDeleteById(RedPacketSendDO redPacketSendDO);
	
	/**
	 * 根据ID删除信息
	 * @param id Long
	 * @return int
	 */
	int deleteById(Long id);
	
	/**
	 * 批量添加信息
	 * @param redPacketSendDOList List<RedPacketSendDO>
	 */
	void insertList(List<RedPacketSendDO> redPacketSendDOList);

	/**
	 * 根据ID更新余量信息
	 * @param redPacketSendDO RedPacketSendDO
	 * @return int
	 */
	int updateRemainById(RedPacketSendDO redPacketSendDO);
	
}
