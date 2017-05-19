package org.wsh.common.model.redpacket;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.enums.redpacket.RedPacketType;
import org.wsh.common.model.base.BaseDO;

import java.math.BigDecimal;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Redpacketsend模型
* since Date： 2017-05-10 11:35:36
*/
public class RedPacketSendDO extends BaseDO{

	
	/**
	 * [AVERAGE:均分红包,REDOM:随机红包]
	 */
	@Setter
	@Getter
	private RedPacketType type;
		
	/**
	 * 用户ID
	 */
	@Setter
	@Getter
	private String userId;
		
	/**
	 * 账户ID
	 */
	@Setter
	@Getter
	private String accountId;
		
	/**
	 * 账单编号
	 */
	@Setter
	@Getter
	private String billNumber;
		
	/**
	 * 业务订单号
	 */
	@Setter
	@Getter
	private String businessNumber;
		
	/**
	 * 总个数
	 */
	@Setter
	@Getter
	private int totalNum;

	/**
	 * 剩余个数
	 */
	@Setter
	@Getter
	private int remainNum;
		
	/**
	 * 总金额
	 */
	@Setter
	@Getter
	private BigDecimal totalAmount;
		
	/**
	 * 剩余金额
	 */
	@Setter
	@Getter
	private BigDecimal remainAmount;
		
	/**
	 * 均分金额
	 */
	@Setter
	@Getter
	private BigDecimal averageAmount;
		
	/**
	 * 签名
	 */
	@Setter
	@Getter
	private String sign;
		
	/**
	 * 备注描述
	 */
	@Setter
	@Getter
	private String description;
		
	/**
	 * 版本号,乐观锁使用
	 */
	@Setter
	@Getter
	private int version;
		
	/**
	 * 删除标记 0正常,1删除
	 */
	@Setter
	@Getter
	private boolean isDeleted;
	
	public RedPacketSendDO() {
	}

	public RedPacketSendDO(Long id) {
		super.setId(id);
	}
}