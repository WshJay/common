package com.xxx.common.server.export;

import lombok.Getter;
import lombok.Setter;

import com.xxx.common.util.export.base.ExportBase;

@SuppressWarnings("serial")
public class AppPartyPvUvWeekDO extends ExportBase{
	
	@Override
	public String[] getTitles() {
		return new String[]{"日期","活动ID","活动名称","城市","类目标签","PV","UV","参加活动次数（点击报名或者报名咨询）"};
	}
	
	@Override
	public String getName() {
		return "活动PV-UV表";
	}
	
	/**
	 * 日期
	 */
	@Setter
	@Getter
	private String date;

	/**
	 * 活动ID
	 */
	@Setter
	@Getter
	private String partyId;
		
	/**
	 * 活动名称
	 */
	@Setter
	@Getter
	private String partyName;
		
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
	private String PV;
	
	/**
	 * UV
	 */
	@Setter
	@Getter
	private String UV;
	
	/**
	 * 参加活动次数
	 */
	@Setter
	@Getter
	private String joinPartyTimes;

}

