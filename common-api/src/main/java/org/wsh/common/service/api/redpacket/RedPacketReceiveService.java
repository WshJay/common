package org.wsh.common.service.api.redpacket;

import org.springframework.stereotype.Service;
import org.wsh.common.model.redpacket.RedPacketReceiveDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  RedPacketReceive服务层接口
* since Date： 2017-05-10 10:15:39
*/
@Service
public interface RedPacketReceiveService {

	/**
	* 多条件查询(分页)
	* @param redPacketReceiveDO RedPacketReceiveDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<RedPacketReceiveDO>>
    */
    public OptionsResponseDO<List<RedPacketReceiveDO>> queryRedPacketReceiveDOListForPage(RedPacketReceiveDO redPacketReceiveDO, Pagination pagination);

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<RedPacketReceiveDO>
    */
    public ResponseDO<RedPacketReceiveDO> getRedPacketReceiveDOById(Long id);

    /**
    * 多条件查询信息
    * @param redPacketReceiveDO RedPacketReceiveDO
    * @return ResponseDO<RedPacketReceiveDO>
    */
    public ResponseDO<RedPacketReceiveDO> getRedPacketReceiveDOByParams(RedPacketReceiveDO redPacketReceiveDO);

    /**
    * 添加
    * @param redPacketReceiveDO RedPacketReceiveDO
    * @return ResponseDO<RedPacketReceiveDO>
    */
    public ResponseDO<RedPacketReceiveDO> addRedPacketReceiveDO(RedPacketReceiveDO redPacketReceiveDO);

    /**
    * 修改
    * @param redPacketReceiveDO RedPacketReceiveDO
	* @return ResponseDO<RedPacketReceiveDO>
    */
    public ResponseDO<RedPacketReceiveDO> modifyRedPacketReceiveDO(RedPacketReceiveDO redPacketReceiveDO);

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<RedPacketReceiveDO>
    */
    public ResponseDO<RedPacketReceiveDO> delRedPacketReceiveDO(Long id);

    /**
     * 领取红包
     * @param redPacketReceiveDO RedPacketReceiveDO sendId必传
     * @return ResponseDO<RedPacketReceiveDO>
     */
    public ResponseDO<RedPacketReceiveDO> doReceiveRedPacket(RedPacketReceiveDO redPacketReceiveDO);
}
