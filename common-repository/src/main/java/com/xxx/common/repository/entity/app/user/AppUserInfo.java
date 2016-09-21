package com.xxx.common.repository.entity.app.user;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

public class AppUserInfo{

	@Getter
	@Setter
	private int id;
	
	
	/**
	 * 帐号
	 */
	@Setter
	@Getter
	private String account;
		
	/**
	 * 密码
	 */
	@Setter
	@Getter
	private String password;
		
	/**
	 * 多聚号
	 */
	@Setter
	@Getter
	private String duojuNo;
		
	/**
	 * 头像地址
	 */
	@Setter
	@Getter
	private String faceUrl;
		
	/**
	 * 性别 1：男 2：女
	 */
	@Setter
	@Getter
	private int sex;
		
	/**
	 * 生日
	 */
	@Setter
	@Getter
	private Date birthday;
		
	/**
	 * 注册时间
	 */
	@Setter
	@Getter
	private Date registTime;
		
	/**
	 * 昵称
	 */
	@Setter
	@Getter
	private String nickName;
		
	/**
	 * 手机号
	 */
	@Setter
	@Getter
	private String mobile;
		
	/**
	 * 微信号
	 */
	@Setter
	@Getter
	private String weixinNo;
		
	/**
	 * 是否在个人首页公开 1：公开 2：不公开
	 */
	@Setter
	@Getter
	private int isOpen;
		
	/**
	 * 创建时间
	 */
	@Setter
	@Getter
	private Date createTime;
		
	/**
	 * 更新时间
	 */
	@Setter
	@Getter
	private Date updateTime;
		
	/**
	 * 是否删除 1:不删除 2：删除
	 */
	@Setter
	@Getter
	private int isDelete;
		
	/**
	 * 省份
	 */
	@Setter
	@Getter
	private String provinceName;
		
	/**
	 * 城市
	 */
	@Setter
	@Getter
	private String cityName;
		
	/**
	 * 地址名称
	 */
	@Setter
	@Getter
	private String areaName;
		
	/**
	 * 当前所在城市
	 */
	@Setter
	@Getter
	private int appBasePartyCityId;
		
	/**
	 * 积分
	 */
	@Setter
	@Getter
	private int scores;
		
	/**
	 * 用户主页背景
	 */
	@Setter
	@Getter
	private String bgUrl;
		
	/**
	 * 个人说明，签名
	 */
	@Setter
	@Getter
	private String signature;
		
	/**
	 * 是否是达人  1：普通用户 2：达人(暂时没用)
	 */
	@Setter
	@Getter
	private int isGeek;
		
	/**
	 * 是否是模拟数据 1：真实用户 2：模拟用户
	 */
	@Setter
	@Getter
	private int isMoke;
		
	/**
	 * 最后登录时间
	 */
	@Setter
	@Getter
	private Date lastTime;
		
	/**
	 * 注册设备号
	 */
	@Setter
	@Getter
	private String deviceNo;
		
	/**
	 * 邀请码
	 */
	@Setter
	@Getter
	private String inviteCode;
		
	/**
	 * token号
	 */
	@Setter
	@Getter
	private String token;
		
	/**
	 * 邮箱
	 */
	@Setter
	@Getter
	private String email;
		
	/**
	 * 环信uuid
	 */
	@Setter
	@Getter
	private String mobUuid;
		
	/**
	 * 是否参加过活动 1：是 2：否
	 */
	@Setter
	@Getter
	private int isRegistParty;
		
	/**
	 * 是否发布过活动 1：是 2：否
	 */
	@Setter
	@Getter
	private int isReleaseParty;
		
	/**
	 * 是否商家 1：是 2：否
	 */
	@Setter
	@Getter
	private int isBusiness;
		
	/**
	 * 注册来源 1：APP 2：后台添加
	 */
	@Setter
	@Getter
	private int registSource;
		
	/**
	 * 后台创建人
	 */
	@Setter
	@Getter
	private int createUserId;
		
	/**
	 * 注册方式  1：手机号 2:QQ 3：新浪微博  4:腾讯微博 5:微信 6:验证码注册_v12
	 */
	@Setter
	@Getter
	private int registWay;
		
	/**
	 * 置顶(推荐)时间
	 */
	@Setter
	@Getter
	private Date recommendTime;
		
	/**
	 * 用户真实姓名_12
	 */
	@Setter
	@Getter
	private String lastRealName;
		
	/**
	 *  年龄（比如00后等）_12
	 */
	@Setter
	@Getter
	private String ages;
		
	/**
	 * 星座_12
	 */
	@Setter
	@Getter
	private String starSigns;
		
	/**
	 * 情感状况_12
	 */
	@Setter
	@Getter
	private String marriage;
	
	/**
	 * 开始时间
	 */
	@Setter
	@Getter
	private String beginTime;
	
	/**
	 * 结束时间
	 */
	@Setter
	@Getter
	private String endTime;
	
}