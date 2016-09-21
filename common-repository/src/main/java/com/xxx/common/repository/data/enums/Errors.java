package com.xxx.common.repository.data.enums;

/**
 * Errors
 * 
 * 系统运行过程中的所有错误定义,包含errorCode以及对应的errorMsg
 * 
 * Created with IntelliJ IDEA. User: caden Date: 4/30/13 Time: 5:41 PM
 * 
 * To change this template use File | Settings | File Templates.
 */
public enum Errors {

	/**
	 * 默认错误，系统异常，主要在发生异常的情况下，默认返回 ,以下为公共错误代码
	 */
	DEFAULT_ERROR("0000", "服务调用异常"),

	CSRF_TOKEN_IS_ERROR("0001", "抱歉，你提交的数据已经过期，请刷新页面重新提交"),

	USER_DOES_NOT_EXIST("0002", "抱歉，该用户不存在"),

	USER_DOES_NOT_DISPLAY("0003", "抱歉，该用户已经被禁用"),

	USER_PASSWORD_IS_ERROR("0004", "抱歉，密码错误"),

	USER_DOES_NOT_IN_BOSS("0005", "抱歉，您没有权限访问"),

	USER_IS_EXIST("0006", "抱歉，该用户名已经被占用"),
	
	PARAMETER_IS_ERROR("0008", "抱歉，参数传异常，请刷新页面重试!"),

	PASSWORD_LENGTH_ERROR("0011", "抱歉，密码号长度错误");

	/**
	 * errorCode,错误编码
	 */
	private String errorCode;

	/**
	 * errorMsg,错误信息描述
	 */
	private String errorMsg;

	/**
	 * Errors
	 * 
	 * @param errorCode
	 * @param errorMsg
	 */
	private Errors(String errorCode, String errorMsg) {
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	/**
	 * getErrorCode
	 * 
	 * @return
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * getErrorMsg
	 * 
	 * @return
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
}
