package org.wsh.common.query;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.pager.base.Query;

public class ModuleQuery extends Query {
	
	/**
	 * 模块名称
	 */
	@Setter
	@Getter
	private String name;
}

