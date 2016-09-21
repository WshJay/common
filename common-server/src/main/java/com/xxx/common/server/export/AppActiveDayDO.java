package com.xxx.common.server.export;

import lombok.Getter;
import lombok.Setter;
import com.xxx.common.util.export.base.ExportBase;

@SuppressWarnings("serial")
public class AppActiveDayDO extends ExportBase{
	
	@Override
	public String[] getTitles() {
		return new String[]{"时间日期","活跃用户数","当日新增用户数","当日新增注册用户数","当日所有用户总启动次数","当日参加活动次数"};
	}
	
	@Override
	public String getName() {
		return "日活跃用户归总表";
	}
	
	/**
	 * 时间日期
	 */
	@Setter
	@Getter
	private String date;

	/**
	 * 活跃用户数
	 */
	@Setter
	@Getter
	private String activeNum;
		
	/**
	 * 当日新增用户数
	 */
	@Setter
	@Getter
	private String newUserNum;
		
	/**
	 * 当日新增注册用户数
	 */
	@Setter
	@Getter
	private String registNum;
		
	/**
	 * 当日所有用户总启动次数
	 */
	@Setter
	@Getter
	private String startupTimes;
		
	/**
	 * 当日参加活动次数
	 */
	@Setter
	@Getter
	private String joinPartyTimes;

}

