package com.xxx.common.repository.entity.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class TjUserBehavior{

	/**
	 * 主键ID
	 */
	@Getter
	@Setter
	private int id;
	
	/**
	 * 手机号
	 */
	@Setter
	@Getter
	private String mobile;
		
	/**
	 * 目标ID
	 */
	@Setter
	@Getter
	private int userId;
		
	/**
	 * 1:注册页 2：完善信息弹框页 3:填写完善信息页
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
	 * 1：无 2：有
	 */
	@Setter
	@Getter
	private int status;
		
	/**
	 * 创建时间
	 */
	@Setter
	@Getter
	private Date createTime;
	
}