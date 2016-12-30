package org.wsh.common.orm.mysql.mybatis.bean;

import lombok.Getter;
import lombok.Setter;
import org.wsh.common.orm.mysql.mybatis.enums.OutPathKey;

import java.util.Map;

public class TableWapper {

	@Setter
	@Getter
	private Table table;

	@Setter
	@Getter
	private String pojoPackage;

	@Setter
	@Getter
	private String daoPackage;

	@Setter
	@Getter
	private String servicePackage;

	@Setter
	@Getter
	private String serviceImplPackage;

	@Setter
	@Getter
	private String serviceTestPackage;

	@Setter
	@Getter
	private Map<OutPathKey,String> outPathMap;

	public TableWapper(Table t) {
		table = t;
	}
}
