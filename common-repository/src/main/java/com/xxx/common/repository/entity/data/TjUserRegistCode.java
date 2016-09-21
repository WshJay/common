package com.xxx.common.repository.entity.data;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class TjUserRegistCode{

	/**
	 * ID
	 */
	@Setter
	@Getter
	private Integer id;
	
	/**
	 * 按钮名称
	 */
	@Setter
	@Getter
	private String buttonName;
		
	/**
	 * 设备号
	 */
	@Setter
	@Getter
	private String deviceNo;
		
	/**
	 * 1：短信验证码 2：语音验证码
	 */
	@Setter
	@Getter
	private Integer type;
		
	/**
	 * 手机号
	 */
	@Setter
	@Getter
	private String mobile;
		
	/**
	 * 创建时间 
	 */
	@Setter
	@Getter
	private Date createTime;
		
	/**
	 * 设备类型 android,ios,h5
	 */
	@Setter
	@Getter
	private String deviceType;
	
}