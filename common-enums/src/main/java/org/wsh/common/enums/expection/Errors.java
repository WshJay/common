package org.wsh.common.enums.expection;

import lombok.Getter;

/**
 * author: wsh
 * JDK-version:  JDK1.8
 * comments:  错误信息封装
 * since Date： 2017/5/9 17:49
 */
public enum Errors {

	DEFAULT_ERROR("0000", "服务调用异常!"),

	PARAMETER_ERROR("0001", "传入参数有误!"),

	REPEAT_SUBMIT("0002", "重复提交异常!");

	/**
	 * 错误编码
	 */
	@Getter
	private String errorCode;

	/**
	 * 错误信息描述
	 */
	@Getter
	private String errorMsg;

	/**
	 * Errors
	 * @param errorCode 错误编码
	 * @param errorMsg 错误信息描述
	 */
	private Errors(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}
}
