package com.xxx.common.repository.entity.app.user;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class AppUserLoginRecord {

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Setter
	@Getter
	private int id;
	
	/**
	 * 用户id
	 */
	@Setter
	@Getter
	private int userId;
	
	/**
	 * 手机号_v12
	 */
	@Setter
	@Getter
	private String phone;
		
	/**
	 * 登录时间
	 */
	@Setter
	@Getter
	private Date loginTime;
	
	/**
	 * 设备号
	 */
	@Setter
	@Getter
	private String deviceNo;
	
	/**
	 * 设备类型：ios,android
	 */
	@Setter
	@Getter
	private String deviceType;
	/**
	 * 系统版本号
	 */
	@Setter
	@Getter
	private String systemNo;
	/**
	 * app版本号
	 */
	@Setter
	@Getter
	private String appNo;
	
	/**
	 *登录方式: QQ:1,新浪微博:2,腾讯微博:3,微信:4,未知:9,邮箱登录:11,手机号码登录:12,验证码登录:13;
	 */
	@Setter
	@Getter
	private Integer loginType;
	
	
	/**
	 * 三方帐号_v12
	 */
	@Setter
	@Getter
	private String openId;
	
	
}