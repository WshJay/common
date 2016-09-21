package com.xxx.common.server.export;

import lombok.Getter;
import lombok.Setter;

import com.xxx.common.util.export.base.ExportBase;

@SuppressWarnings("serial")
public class AppUserInfoDO extends ExportBase{
	
	@Override
	public String[] getTitles() {
		return new String[]{"设备ID","注册时间","最后登录城市","最后登录时间","性别","手机号","购买票数","30天内启动天数"};
	}
	
	@Override
	public String getName() {
		return "全量用户行为表";
	}
	
	/**
	 * 设备ID
	 */
	@Setter
	@Getter
	private String deviceNo;
	
	/**
	 * 注册时间
	 */
	@Setter
	@Getter
	private String registTime;
	
	/**
	 * 最后登录城市
	 */
	@Setter
	@Getter
	private String cityName;
	
	/**
	 * 最后登录时间
	 */
	@Setter
	@Getter
	private String lastLoginTime;

	/**
	 * 性别
	 */
	@Setter
	@Getter
	private String sex;
		
	/**
	 * 手机号
	 */
	@Setter
	@Getter
	private String mobile;
		
	/**
	 * 购买票数
	 */
	@Setter
	@Getter
	private String buyNum;
	
	/**
	 * 30天内启动天数
	 */
	@Setter
	@Getter
	private String startupDayNum;

}

