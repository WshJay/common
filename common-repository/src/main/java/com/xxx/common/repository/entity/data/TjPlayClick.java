package com.xxx.common.repository.entity.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class TjPlayClick{

	/**
	 * 主键ID
	 */
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
	 * 活动ID
	 */
	@Setter
	@Getter
	private int partyId;
		
	/**
	 * 活动名称
	 */
	@Setter
	@Getter
	private String partyName;
		
	/**
	 * 模版ID
	 */
	@Setter
	@Getter
	private int templateId;
		
	/**
	 * 1：首页 2：活动详情页
	 */
	@Setter
	@Getter
	private int pageType;
		
	/**
	 * 1:音频播放键 2：视频播放键
	 */
	@Setter
	@Getter
	private int type;
		
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
	 * 创建时间
	 */
	@Setter
	@Getter
	private Date createTime;
	
}