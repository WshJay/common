package org.wsh.common.model.basic;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

import java.util.List;
import java.util.Set;

public class UserBasicDO extends BaseDO {
	
	private static final long serialVersionUID = 7289005806169773622L;

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


	
	/**
	 * 用户对应角色ID
	 */
	@Setter
	@Getter
	private Long roleId;
	
	/**
	 * 用户所有角色
	 */
	@Setter
	@Getter
	private List<RoleDO> roleList;
	
	/**
	 * 用户所有权限
	 */
	@Setter
	@Getter
	private Set<PermissionDO> permissionSet;
	
	/**
	 * 用户所对应的角色
	 */
	@Setter
	@Getter
	private RoleDO role;
	
	public void bulidParameters(String userName,
			String realName, String phone, String email){
		this.userName = userName;
		this.realName = realName;
		this.phone = phone;
		this.email = email;
	}

	@Override
	public String toString() {
		return "UserBasicDO{" +
				"userName='" + userName + '\'' +
				", password='" + password + '\'' +
				", salt='" + salt + '\'' +
				", realName='" + realName + '\'' +
				", faceUrl='" + faceUrl + '\'' +
				", phone='" + phone + '\'' +
				", email='" + email + '\'' +
				", status=" + status +
				", version=" + version +
				", description='" + description + '\'' +
				", isDeleted=" + isDeleted +
				'}';
	}
}