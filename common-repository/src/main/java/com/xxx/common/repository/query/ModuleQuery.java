package com.xxx.common.repository.query;

import lombok.Getter;
import lombok.Setter;

import com.xxx.common.repository.query.base.Query;

public class ModuleQuery extends Query{
	
	/**
	 * 模块名称
	 */
	@Setter
	@Getter
	private String name;
}

