package com.xxx.common.repository.entity.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import com.xxx.common.repository.base.BaseDO;

public class TjUserStay{

	@Getter
	@Setter
	private int id;
	
	/**
	 * 用户ID
	 */
	@Setter
	@Getter
	private int userId;
		
	/**
	 * 设备号
	 */
	@Setter
	@Getter
	private String deviceNo;
		
	/**
	 * 设备类型 android,ios,h5
	 */
	@Setter
	@Getter
	private String deviceType;
		
	/**
	 * MAC地址
	 */
	@Setter
	@Getter
	private String mac;
		
	/**
	 * 版本号
	 */
	@Setter
	@Getter
	private String version;
		
	/**
	 * 渠道名称
	 */
	@Setter
	@Getter
	private String channel;
		
	/**
	 * 1:进入APP 2：首页 3：发现页 4：消息页 5：我的页 6：活动详情页 7：精选详情页 8：个人主页 9：首页banner详情页
	 */
	@Setter
	@Getter
	private int type;
		
	/**
	 * 首页Banner位置 
	 */
	@Setter
	@Getter
	private int position;
		
	/**
	 * 进入时间
	 */
	@Setter
	@Getter
	private Date intoTime;
		
	/**
	 * 离开时间
	 */
	@Setter
	@Getter
	private Date leaveTime;
		
	/**
	 * 停留时长(秒)
	 */
	@Setter
	@Getter
	private int stayLong;
	
	/**
	 * 多类型进入 1,2
	 */
	@Setter
	@Getter
	private String types;
	
}