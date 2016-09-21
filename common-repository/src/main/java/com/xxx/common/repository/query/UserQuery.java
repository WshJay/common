package com.xxx.common.repository.query;

import com.xxx.common.repository.query.base.Query;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户查询参数
 * File Name: <UserQuery.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-6-5 下午4:15:37
 */
public class UserQuery extends Query{
	
	/**
	 * 用户名
	 */
	@Setter
	@Getter
	private String userName;
		
	/**
	 * 真实姓名
	 */
	@Setter
	@Getter
	private String realName;
		
	/**
	 * 手机号码
	 */
	@Setter
	@Getter
	private String phone;
		
	/**
	 * 邮箱
	 */
	@Setter
	@Getter
	private String email;
		
	/**
	 * 用户状态
	 */
	@Setter
	@Getter
	private int status;
}

