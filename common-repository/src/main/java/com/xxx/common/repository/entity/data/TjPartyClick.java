package com.xxx.common.repository.entity.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class TjPartyClick {

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
	 * 模版类型 1:通用版 2:音频版 3:视频版 4:小海报 5:大海报 
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
	
	
	
	/**
	 * 发生城市
	 */
	@Setter
	@Getter
	private String destCityName;
	
	
	/**
	 * 类目标签
	 */
	@Setter
	@Getter
	private String tagName;
	
	/**
	 * PV
	 */
	@Setter
	@Getter
	private int pv;
	
	/**
	 * UV
	 */
	@Setter
	@Getter
	private int uv;
	
	/**
	 * 参加活动次数（点击报名或者报名咨询）
	 */
	@Setter
	@Getter
	private int cpv;
	
}