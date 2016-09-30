package org.wsh.common.util.export.base;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings("serial")
public abstract class ExportBase implements Serializable{

	@Getter
	@Setter
	private String name = "数据";
	
	@Getter
	private String[] titles;

}

