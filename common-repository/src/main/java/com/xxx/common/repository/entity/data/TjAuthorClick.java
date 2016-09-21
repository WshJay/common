package com.xxx.common.repository.entity.data;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class TjAuthorClick {

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
	 * 精选ID
	 */
	@Setter
	@Getter
	private int articlesId;
		
	/**
	 * 作者ID
	 */
	@Setter
	@Getter
	private int authorId;
		
	/**
	 * 作者名称
	 */
	@Setter
	@Getter
	private String authorName;
		
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