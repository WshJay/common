package com.xxx.common.repository.data.support;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.xxx.common.repository.data.enums.Errors;

/**
 * ResponseDO
 * 
 * Created with IntelliJ IDEA. User: caden Date: 4/30/13 Time: 5:33 PM
 * 
 * To change this template use File | Settings | File Templates.
 */
public class ResponseDO<T> implements Serializable {

	/**
	 * success调用是否成功， 1.如果调用成功，那么返回true, 同时error对象为空
	 * 2.如果失败返回false,同时包含对应的errorCode和errorMsg信息 默认返回成功
	 */
	private boolean success = Boolean.TRUE;

	/**
	 * error,调用错误代码,当调用发生异常的时候， 返回错误代码和错误信息描述,默认没有错误,error=null
	 */
	@Getter
	@Setter
	private Errors error;

	/**
	 * 错误编码,默认为空
	 */
	@Getter
	@Setter
	private String errorCode = StringUtils.EMPTY;

	/**
	 * 错误详细信息,默认为空
	 */
	@Getter
	@Setter
	private String errorMsg = StringUtils.EMPTY;

	/**
	 * 其他相关返回的数据信息
	 */
	@Getter
	@Setter
	private T data;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	/**
	 * invokeFailed
	 * 
	 * @param error
	 */
	public void invokeFailed(Errors error) {
		setError(error);
		setErrorCode(error.getErrorCode());
		setErrorMsg(error.getErrorMsg());
		setSuccess(Boolean.FALSE);
	}
	
	/**
	 * 转换为JSON数据
	 * @return
	 */
	public JSONObject toJson(){
		JSONObject obj = new JSONObject();
		obj.put("isSuccess", success);
		if (success) {
			obj.put("data", data);
		}else{
			obj.put("errorCode", errorCode);
			obj.put("errorMsg", errorMsg);
		}
		return obj;
	}

}
