package com.xxx.common.server.export;

import com.xxx.common.util.export.base.ExportBase;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
public class AppStartupDO extends ExportBase{
	
	@Override
	public String[] getTitles() {
		return new String[]{"启动日期","用户ID","设备号","渠道号","当日启动次数","当日参加活动次数"};
	}
	
	@Override
	public String getName() {
		return "日活跃用户行为表";
	}
	
	/**
	 * 启动时间
	 */
	@Setter
	@Getter
	private String startupTime;

	/**
	 * 用户ID
	 */
	@Setter
	@Getter
	private String userId;
		
	/**
	 * 设备号
	 */
	@Setter
	@Getter
	private String deviceNo;
		
	/**
	 * 渠道名称
	 */
	@Setter
	@Getter
	private String channel;
		
	/**
	 * 启动次数
	 */
	@Setter
	@Getter
	private String startupTimes;
		
	/**
	 * 参加活动数量
	 */
	@Setter
	@Getter
	private String joinPartyTimes;
	
		
}

