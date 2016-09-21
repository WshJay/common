package com.xxx.common.server.util;

import com.xxx.common.repository.data.beans.OptionsResponseDO;
import com.xxx.common.repository.data.enums.Errors;
import com.xxx.common.repository.data.support.ResponseDO;

@SuppressWarnings("rawtypes")
public final class BeansUtil {

	/**
	 * 静态的唯一的responseDO
	 */
	private static final ResponseDO STATIC_RESPONSE_DO = new ResponseDO();

	/**
	 * 静态的唯一的OptionsResponseDO
	 */
	private static final OptionsResponseDO STATIC_OPTIONS_RESPONSE_DO = new OptionsResponseDO();

	/**
	 * newResponsDO
	 * 
	 * @return
	 */
	public static ResponseDO newStaticResponseDO() {
		return STATIC_RESPONSE_DO;
	}
	

	/**
	 * newStaticOptionsResponseDO
	 * 
	 * @return
	 */
	public static OptionsResponseDO newStaticOptionsResponseDO() {
		return STATIC_OPTIONS_RESPONSE_DO;
	}

	/**
	 * newResponseDO
	 * 
	 * @param errors
	 * @return
	 */
	public static ResponseDO newResponseDO(Errors errors) {
		ResponseDO responseDO = new ResponseDO();
		responseDO.invokeFailed(errors);
		return responseDO;
	}

	/**
	 * newResponseDO
	 * 
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> ResponseDO<T> newResponseDO(T data) {
		ResponseDO<T> responseDO = new ResponseDO<T>();
		responseDO.setData(data);
		return responseDO;
	}

	/**
	 * newOptionsResponseDO
	 * 
	 * @param total
	 * @param data
	 * @param <T>
	 * @return
	 */
	public static <T> OptionsResponseDO<T> newOptionsResponseDO(int total,
			T data, int currentPage) {
		OptionsResponseDO<T> optionsResponseDO = new OptionsResponseDO<T>();
		optionsResponseDO.bindingResponseData(data, total);
		optionsResponseDO.setCurrentPage(currentPage);
		return optionsResponseDO;
	}

	/**
	 * newOptionsResponseDO
	 * 
	 * @param total
	 * @param data
	 * @param pageSize
	 * @param <T>
	 * @return
	 */
	public static <T> OptionsResponseDO<T> newOptionsResponseDO(int total,
			T data, int pageSize, int currentPage) {
		OptionsResponseDO<T> optionsResponseDO = new OptionsResponseDO<T>();
		optionsResponseDO.bindingResponseData(data, total);
		optionsResponseDO.setPageSize(pageSize);
		optionsResponseDO.setCurrentPage(currentPage);
		return optionsResponseDO;
	}

}
