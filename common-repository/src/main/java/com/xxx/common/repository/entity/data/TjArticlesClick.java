package com.xxx.common.repository.entity.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class TjArticlesClick{

	/**
	 * 主键ID
	 */
	@Getter
	@Setter
	private int id;
	
	/**
	 * APP用户ID
	 */
	@Setter
	@Getter
	private int userId;
		
	/**
	 * 精选ID
	 */
	@Setter
	@Getter
	private int articlesId;
		
	/**
	 * 精选名称
	 */
	@Setter
	@Getter
	private String articlesName;
		
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
	 * 城市
	 */
	@Setter
	@Getter
	private String cityName;
	
	
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
	
}