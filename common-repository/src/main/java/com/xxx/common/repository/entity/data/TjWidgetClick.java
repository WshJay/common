package com.xxx.common.repository.entity.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class TjWidgetClick {

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
	 * widgetID
	 */
	@Setter
	@Getter
	private int widgetId;
		
	/**
	 * widget名称
	 */
	@Setter
	@Getter
	private String widgetName;
		
	/**
	 * 当前目标对象ID
	 */
	@Setter
	@Getter
	private int targetId;
		
	/**
	 * 类型 1:精选 2:推广视频 3:专题
	 */
	@Setter
	@Getter
	private int type;
		
	/**
	 * 专题位置
	 */
	@Setter
	@Getter
	private int position;
		
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