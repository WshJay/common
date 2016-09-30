package org.wsh.common.pager.base;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;

public class Query {
	
	public String toString() {
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
	
	/**
	 * 构造url中的queryString
	 * 
	 * @param query
	 */
	@SuppressWarnings({"rawtypes" })
	public String buildQueryString(Query query) {
		
		StringBuffer buffer = new StringBuffer();
		BeanMap beanMap = new BeanMap(query);
		Iterator iter = beanMap.keyIterator();
		while (iter.hasNext()) {
			String key = (String) iter.next();
			Object value = beanMap.get(key);

			// 去除key = class 的属性，去除value = null的属性或为""的字符串
			if (!StringUtils.equalsIgnoreCase(key, "class") && value != null
					&& !StringUtils.isBlank(value.toString())) {

				String encodeValue = null;

				try {
					encodeValue = URLEncoder.encode(value.toString(), "utf-8");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				buffer.append("&").append(key).append("=").append(encodeValue);
			}
		}
		return buffer.append("&p=").toString();
	}
}
