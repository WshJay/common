package org.wsh.common.support.beans;

import org.wsh.common.support.response.ResponseDO;

import java.util.HashMap;
import java.util.Map;

/**
 * 对页面需要分页数据进行封装
 * File Name: <OptionsResponseDO.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-6-10 下午3:11:15
 */
public class OptionsResponseDO<T> extends ResponseDO<T> {

	private static final long serialVersionUID = -6585808252151007022L;

	/**
	 * 一共查询到多少条数据
	 */
	private int total;

	/**
	 * 每页现实多少条，默认为10条
	 */
	private int pageSize = 10;

	/**
	 * 当前页码
	 */
	private int currentPage;

	private Map<String, Object> extrasMap;

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalPageNum() {
		return total / pageSize + (total % pageSize == 0 ? 0 : 1);
	}

	public Map<String, Object> getExtrasMap() {
		return extrasMap;
	}

	public void putExtras(String key, Object value) {
		if (extrasMap == null) {
			extrasMap = new HashMap<String, Object>();
		}
		extrasMap.put(key, value);
	}

	/**
	 * bindingResponseData
	 * 
	 * @param data
	 * @param total
	 */
	public void bindingResponseData(T data, int total) {
		setData(data);
		setTotal(total);
	}

}
