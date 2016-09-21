package com.xxx.common.repository.entity.app.system;

import java.util.Date;
import lombok.Getter;
import lombok.Setter;

public class AppBannerClick{

	/**
	 * ID
	 */
	@Setter
	@Getter
	private int id;
	
	/**
	 * 
	 */
	@Setter
	@Getter
	private int appUserInfoId;
		
	/**
	 * 
	 */
	@Setter
	@Getter
	private int targetId;
		
	/**
	 * 设备号
	 */
	@Setter
	@Getter
	private String deviceNo;
		
	/**
	 * H5类型  1：首页banner，2：活动详情，3：精选详情，4：达人
	 */
	@Setter
	@Getter
	private int type;
		
	/**
	 * 
	 */
	@Setter
	@Getter
	private Date createTime;
	
	/**
	 * h5页面按钮名
	 */
	@Setter
	@Getter
	private String subType;
	
	/**
	 * 设备类型 android,ios,h5
	 */
	@Setter
	@Getter
	private String deviceType;
	
}