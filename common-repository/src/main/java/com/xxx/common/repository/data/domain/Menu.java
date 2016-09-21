package com.xxx.common.repository.data.domain;

import java.util.List;

import lombok.Data;

@Data
public class Menu {
	private Long id;
	private String name;
	private Long resId;
	private Long fatherId;
	private String target;
	private boolean current;
	private List<Menu> children;
}
