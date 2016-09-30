package org.wsh.common.test.model.vo;

import java.util.List;

import org.wsh.common.test.model.dto.RoleDO;

import lombok.Getter;
import lombok.Setter;

public class UserBasicVO {

	/**
	 * ID
	 */
	@Setter
	@Getter
	private int id;
	
	/**
	 * 用户名
	 */
	@Setter
	@Getter
	private String userName;
		
	/**
	 * 密码
	 */
	@Setter
	@Getter
	private String password;
		
	/**
	 * 密钥
	 */
	@Setter
	@Getter
	private String salt;
		
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
	 * 用户所有角色
	 */
	@Setter
	@Getter
	private List<RoleDO> roleList;
}

