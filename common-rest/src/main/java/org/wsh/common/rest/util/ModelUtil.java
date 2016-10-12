package org.wsh.common.rest.util;

import org.apache.commons.lang.StringUtils;
import org.springframework.ui.Model;
import org.wsh.common.enums.expection.Errors;
import org.wsh.common.enums.response.ModelKey;
import org.wsh.common.support.beans.OptionsResponseDO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 返回页面数据封装
 * File Name: <ModelUtil.java>
 * Comments:  <对此类的描述，可以引用系统设计中的描述>
 * JDK version used: <JDK1.6> 
 * @author wsh[wsh.ck@qq.com]
 * @since Date： 2015-6-10 下午3:29:25
 */
public final class ModelUtil {

	private ModelUtil() {

	}
	
	/**
	 * 返回数据
	 * @param model
	 * @param modelKey
	 * @param value
	 */
	public static <T> void newModelValues(Model model, ModelKey modelKey, T value) {
		model.addAttribute(modelKey.name(), value);
	}

	/**
	 * 返回数据(分页)
	 * @param model
	 * @param optionsResponseDO
	 */
	public static void newModelQuerys(Model model, HttpServletRequest request, OptionsResponseDO<?> optionsResponseDO) {
		if (!optionsResponseDO.isSuccess())
			return;
		model.addAttribute(ModelKey.total.name(), optionsResponseDO.getTotal());
		model.addAttribute(ModelKey.pageSize.name(), optionsResponseDO.getPageSize());
		model.addAttribute(ModelKey.currentPage.name(), optionsResponseDO.getCurrentPage());
		model.addAttribute(ModelKey.data.name(), optionsResponseDO.getData());
		model.addAttribute(ModelKey.pageURL.name(), getPageUrl(request));
	}

	/**
	 * 返回查询参数
	 * @param model
	 * @param parameter
	 */
	public static <T> void newModelParameters(Model model, T parameter) {
		model.addAttribute(ModelKey.parameter.name(), parameter);
	}

	/**
	 * 返回错误信息
	 * @param model
	 * @param error
	 */
	public static void newModelError(Model model, Errors error) {
		model.addAttribute(ModelKey.errorCode.name(), error.getErrorCode());
		model.addAttribute(ModelKey.errorMsg.name(), error.getErrorMsg());
	}

	/**
	 * 返回多个数据
	 * @param model
	 * @param dataMap
	 */
	public static <T> void newModelValues(Model model, Map<ModelKey, T> dataMap) {
		for (Map.Entry<ModelKey, T> entry : dataMap.entrySet()) {
			model.addAttribute(entry.getKey().name(), entry.getValue());
		}
	}
	
	/**
	 * 获取返回给页面的分页URL
	 * @param request
	 * @param currentPage 
	 * @return
	 */
	private static String getPageUrl(HttpServletRequest request) {
		StringBuffer pageURL = new StringBuffer();
		String queryString = request.getQueryString();
		if (!StringUtils.isBlank(queryString)) {
			if (!StringUtils.isBlank(request.getParameter("p"))) {
				String currentPageString = new StringBuffer("p=").append(request.getParameter("p")).toString();
				queryString = queryString.replace(currentPageString, "p=");
			}else{
				queryString = new StringBuffer(queryString).append("&p=").toString();
			}
			pageURL = request.getRequestURL().append("?").append(queryString);
		} else {
			pageURL = request.getRequestURL().append("?p=");
		}
		return pageURL.toString();
	}
}
