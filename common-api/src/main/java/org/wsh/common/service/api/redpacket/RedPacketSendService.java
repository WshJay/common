package org.wsh.common.service.api.redpacket;

import org.springframework.stereotype.Service;
import org.wsh.common.model.redpacket.RedPacketSendDO;
import org.wsh.common.pager.pagination.Pagination;
import org.wsh.common.support.beans.OptionsResponseDO;
import org.wsh.common.support.response.ResponseDO;
import java.util.List;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  RedPacketSend服务层接口
* since Date： 2017-05-10 10:15:39
*/
@Service
public interface RedPacketSendService {

	/**
	* 多条件查询(分页)
	* @param redPacketSendDO RedPacketSendDO
	* @param pagination  Pagination
	* @return OptionsResponseDO<List<RedPacketSendDO>>
    */
    public OptionsResponseDO<List<RedPacketSendDO>> queryRedPacketSendDOListForPage(RedPacketSendDO redPacketSendDO, Pagination pagination);

    /**
    * 根据用户ID查询
    * @param id Long
    * @return ResponseDO<RedPacketSendDO>
    */
    public ResponseDO<RedPacketSendDO> getRedPacketSendDOById(Long id);

    /**
    * 多条件查询信息
    * @param redPacketSendDO RedPacketSendDO
    * @return ResponseDO<RedPacketSendDO>
    */
    public ResponseDO<RedPacketSendDO> getRedPacketSendDOByParams(RedPacketSendDO redPacketSendDO);

    /**
    * 添加
    * @param redPacketSendDO RedPacketSendDO
    * @return ResponseDO<RedPacketSendDO>
    */
    public ResponseDO<RedPacketSendDO> addRedPacketSendDO(RedPacketSendDO redPacketSendDO);

    /**
    * 修改
    * @param redPacketSendDO RedPacketSendDO
	* @return ResponseDO<RedPacketSendDO>
    */
    public ResponseDO<RedPacketSendDO> modifyRedPacketSendDO(RedPacketSendDO redPacketSendDO);

    /**
    * 删除(逻辑删除)
    * @param id Long
    * @return ResponseDO<RedPacketSendDO>
    */
    public ResponseDO<RedPacketSendDO> delRedPacketSendDO(Long id);
}
