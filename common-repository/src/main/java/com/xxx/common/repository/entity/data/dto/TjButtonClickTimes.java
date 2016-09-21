package com.xxx.common.repository.entity.data.dto;

import lombok.Getter;
import lombok.Setter;

public class TjButtonClickTimes {

	/**
	 * userKey(userId + deviceNo)
	 */
	@Setter
	@Getter
	private String userKey;
		
	/**
	 * 点击次数
	 */
	@Setter
	@Getter
	private int clickTimes;
}

