package org.wsh.common.model.msg;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

public class GroupDO extends BaseDO{

	private static final long serialVersionUID = 1L;
	
	/**
	 * ID
	 */
	@Setter
	@Getter
	private int id;
	
	/**
	 * 组名称
	 */
	@Setter
	@Getter
	private String name;
		
	/**
	 * 封面地址
	 */
	@Setter
	@Getter
	private String coverUrl;
		
	/**
	 * 状态[ON_LINE,OFF_LINE]
	 */
	@Setter
	@Getter
	private String status;
		
	/**
	 * 创建用户ID
	 */
	@Setter
	@Getter
	private Long createUserId;
	
}