package org.wsh.common.query;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.pager.base.Query;

public class PermissionQuery extends Query {
	/**
	 * 权限名称
	 */
	@Setter
	@Getter
	private String name;
		
	/**
	 * 权限代码
	 */
	@Setter
	@Getter
	private String code;
		
	/**
	 * 目标地址
	 */
	@Setter
	@Getter
	private String target;
		
	/**
	 * 是否验证(0表示不验证权限，1表示验证权限)
	 */
	@Setter
	@Getter
	private int allow;
		
	/**
	 * 类型
	 */
	@Setter
	@Getter
	private int type;
		
	/**
	 * 模块ID
	 */
	@Setter
	@Getter
	private Long moduleId;
}

