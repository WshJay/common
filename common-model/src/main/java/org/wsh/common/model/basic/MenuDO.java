package org.wsh.common.model.basic;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.model.base.BaseDO;

import java.util.List;

public class MenuDO extends BaseDO {
	
	private static final long serialVersionUID = 95137035969351744L;

	/**
	 * 栏目名称
	 */
	@Setter
	@Getter
	private String name;
		
	/**
	 * 资源ID
	 */
	@Setter
	@Getter
	private Long resId;
		
	/**
	 * 父栏目ID
	 */
	@Setter
	@Getter
	private Long fatherId;
		
	/**
	 * 状态(0表示展示，1表示不展示)
	 */
	@Setter
	@Getter
	private int status;
	
	
	/**
	 * 当前栏
	 */
	@Setter
	@Getter
	private boolean current;
	
	/**
	 * 子栏目
	 */
	@Setter
	@Getter
	private List<MenuDO> children;
	
	/**
	 * 地址
	 */
	@Setter
	@Getter
	private String target;
	
	/**
	 * 是否拦截(0表示不拦截，1表示拦截)
	 */
	@Setter
	@Getter
	private int allow;
	
}