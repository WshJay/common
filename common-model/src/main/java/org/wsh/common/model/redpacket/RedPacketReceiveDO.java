package org.wsh.common.model.redpacket;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

import java.math.BigDecimal;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Redpacketreceive模型
* since Date： 2017-05-10 11:35:36
*/
public class RedPacketReceiveDO extends BaseDO{

	
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
	 * 发放红包编号
	 */
	@Setter
	@Getter
	private Long sendId;
		
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
	 * 金额
	 */
	@Setter
	@Getter
	private BigDecimal amount;
		
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
	
	public RedPacketReceiveDO() {
	}

	public RedPacketReceiveDO(String userId, String accountId, Long sendId, String billNumber, String businessNumber, BigDecimal amount, String sign) {
		this.userId = userId;
		this.accountId = accountId;
		this.sendId = sendId;
		this.billNumber = billNumber;
		this.businessNumber = businessNumber;
		this.amount = amount;
		this.sign = sign;
	}
}