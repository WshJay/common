package com.xxx.common.repository.entity.app.order;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;
import com.xxx.common.repository.base.BaseDO;

public class AppPartyOrder extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	
	/**
	 * 报名用户ID
	 */
	@Setter
	@Getter
	private int appUserInfoId;
		
	/**
	 * 报名活动ID
	 */
	@Setter
	@Getter
	private int appPartyInfoId;
		
	/**
	 * 套餐ID
	 */
	@Setter
	@Getter
	private int appPartyPieceId;
		
	/**
	 * 订单号
	 */
	@Setter
	@Getter
	private String orderNo;
		
	/**
	 * 下单时间
	 */
	@Setter
	@Getter
	private Date createTime;
		
	/**
	 * 购买数量
	 */
	@Setter
	@Getter
	private int buyNumber;
		
	/**
	 * 单价
	 */
	@Setter
	@Getter
	private Double price;
		
	/**
	 * 总价
	 */
	@Setter
	@Getter
	private Double totalPrice;
		
	/**
	 * 订单状态 1 未审核  2：审核通过  3:审核不通过  4:删除订单 5 取消订单 6:成功 7:异常失败 8:待付款 9:交易关闭 10:退款中 11:已退款 12:已过期 13:已使用
	 */
	@Setter
	@Getter
	private int status;
		
	/**
	 * 是否在线支付 0：无需支付 1：在线支付 2：线下支付
	 */
	@Setter
	@Getter
	private int isOnlinePay;
		
	/**
	 * 描述(活动标题+套餐标题)
	 */
	@Setter
	@Getter
	private String description;
		
	/**
	 * 交易号
	 */
	@Setter
	@Getter
	private String tradeNo;
		
	/**
	 * 交易方式 0：无(线下/免费) 1:支付宝  2：微信 
	 */
	@Setter
	@Getter
	private int tradeWay;
		
	/**
	 * 手机号
	 */
	@Setter
	@Getter
	private String mobile;
		
	/**
	 * 实际支付金额
	 */
	@Setter
	@Getter
	private Double actualPayPrice;
		
	/**
	 * 实际需要支付的金额
	 */
	@Setter
	@Getter
	private Double actualNeedPrice;
		
	/**
	 * 优惠价格
	 */
	@Setter
	@Getter
	private Double actualDiscountPrice;
		
	/**
	 * 实际积分抵扣金额
	 */
	@Setter
	@Getter
	private Double actualDeductPrice;
		
	/**
	 * 积分兑换的数量
	 */
	@Setter
	@Getter
	private int exchangeNumber;
		
	/**
	 * 已经退款的数量
	 */
	@Setter
	@Getter
	private int refundNumber;
		
	/**
	 * 退款中的数量
	 */
	@Setter
	@Getter
	private int waitNumber;
		
	/**
	 * 下单后 15分钟内不付款 自动取消订单  期间订单锁定(1:锁定，2：未锁定)  
	 */
	@Setter
	@Getter
	private int isLock;
		
	/**
	 * 是否使用 1:使用 2:未使用
	 */
	@Setter
	@Getter
	private int isUse;
		
	/**
	 * 是否评价 1:评价 2：未评价
	 */
	@Setter
	@Getter
	private int isRate;
		
	/**
	 * 不付款 15分钟后订单关闭 0：无需交易 1：正常未关闭 2：关闭
	 */
	@Setter
	@Getter
	private int isClose;
		
	/**
	 * 消费多少积分
	 */
	@Setter
	@Getter
	private int jifenCost;
		
	/**
	 * 是否可结算_v12 1： 可结算 2：不可结算
	 */
	@Setter
	@Getter
	private int isBalance;
		
	/**
	 * 活动修改_v12 1：按照套餐退款 2：全额退
	 */
	@Setter
	@Getter
	private int refundLimit;
		
	/**
	 * 最后更新时间_v12
	 */
	@Setter
	@Getter
	private Date lastModifyTime;
		
	/**
	 * 订单交易时间_v12
	 */
	@Setter
	@Getter
	private Date tradeTime;
		
	/**
	 * 下单用户真实姓名_v12
	 */
	@Setter
	@Getter
	private String realName;
		
	/**
	 * 删除状态_v12 1:未删除 2：已删除
	 */
	@Setter
	@Getter
	private int deleteStatus;
		
	/**
	 * 订单结算金额_v12
	 */
	@Setter
	@Getter
	private Double balanceMoney;
		
	/**
	 * 使用时间_V12
	 */
	@Setter
	@Getter
	private Date useTime;
	
}