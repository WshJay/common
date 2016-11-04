package org.wsh.common.model.msg;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

public class UserGroupDO extends BaseDO {

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Setter
	@Getter
	private int id;
	
	/**
	 * 用户ID
	 */
	@Setter
	@Getter
	private Long userId;
		
	/**
	 * 组ID
	 */
	@Setter
	@Getter
	private Long groupId;
	
}