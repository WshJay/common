package com.xxx.common.repository.entity.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class TjButtonClick{

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
	 * 目标ID
	 */
	@Setter
	@Getter
	private int targetId;
		
	/**
	 *  按钮名称
	 */
	@Setter
	@Getter
	private String buttonName;
		
	/**
	 * 1:活动 2：精选 3: 验证码登录 4:密码登录 5:第三方登录 6:首页左上角”城市”按钮点击 7:选择城市按钮点击 8:关闭按钮点击 9:搜索页面中，点击日历中具体日期进行搜索的点击量 10筛选条中，本周、下周、两周三个点，每个点的点击量 11:筛选条中，右侧的活动分类点击量 12:活动分类里每个分类的点击量
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