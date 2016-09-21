package com.xxx.common.repository.entity.data;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

public class TjAppStartup{

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
	 * 城市ID
	 */
	@Setter
	@Getter
	private int cityId;
		
	/**
	 * 城市名称
	 */
	@Setter
	@Getter
	private String cityName;
		
	/**
	 * 创建时间
	 */
	@Setter
	@Getter
	private Date createTime;
	
	/**
	 * 开始时间
	 */
	@Setter
	@Getter
	private String beginTime;
	
	/**
	 * 结束时间
	 */
	@Setter
	@Getter
	private String endTime;
	
	/**
	 * 是否是新
	 */
	@Setter
	@Getter
	private Integer isNew;
	
}