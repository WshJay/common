package org.wsh.common.test.sql;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

import java.io.Serializable;
import java.util.Date;

/**
* author: wsh
* JDK-version:  JDK1.8
* comments:  Userbasic模型
* since Date： 2017-01-09 16:59:37
*/
public class UserBasicDO implements Serializable{


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
	 * 头像地址
	 */
	@Setter
	@Getter
	private String faceUrl;
		
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
		
	/**
	 * 版本号(乐观锁)
	 */
	@Setter
	@Getter
	private int version;
		
	/**
	 * 备注说明
	 */
	@Setter
	@Getter
	private String description;
		
	/**
	 * 0正常,1删除
	 */
	@Setter
	@Getter
	private int isDeleted;

	@Getter
	@Setter
	private Date gmtCreated;

	@Getter
	@Setter
	private Date gmtModified;

	public UserBasicDO(String userName, String password, String salt, String realName, String faceUrl, String phone, String email, int status, int version, String description, int isDeleted, Date gmtCreated, Date gmtModified) {
		this.userName = userName;
		this.password = password;
		this.salt = salt;
		this.realName = realName;
		this.faceUrl = faceUrl;
		this.phone = phone;
		this.email = email;
		this.status = status;
		this.version = version;
		this.description = description;
		this.isDeleted = isDeleted;
		this.gmtCreated = gmtCreated;
		this.gmtModified = gmtModified;
	}
}