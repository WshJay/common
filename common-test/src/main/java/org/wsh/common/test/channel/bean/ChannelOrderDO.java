package org.wsh.common.test.channel.bean;

import java.io.Serializable;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class ChannelOrderDO implements Serializable{

	private static final long serialVersionUID = -1932450907706412960L;

	/**
	 * 通道ID
	 */
	@Setter
	@Getter
	public int channelId;
	
	/**
	 * 系统订单ID
	 */
	@Setter
	@Getter
	public String systemOrderId;
	
	/**
	 * 通道系统订单号
	 */
	@Setter
	@Getter
	public String channelOrderId; 
	
	/**
	 * 提交面值
	 */
	@Setter
	@Getter
	public Double orderValue;
	
	/**
	 * 成功面值
	 */
	@Setter
	@Getter
	public Double successValue;
	
	/**
	 * 扣款金额
	 */
	@Setter
	@Getter
	public Double successPrice;
	
	/**
	 * 订单状态
	 */
	@Setter
	@Getter
	public int orderStatus;
	
	/**
	 * 充值账号
	 */
	@Setter
	@Getter
	public String prepaidNumber;
	
	/**
	 * 订单日期
	 */
	@Setter
	@Getter
	public Date orderDate;
	
	/**
	 * 是否已对账(1代表未对账，2代表已对账)
	 */
	@Setter
	@Getter
	public int ischeck = 1;
	

}