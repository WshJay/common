package com.duoju.daq.converters;

import org.springframework.core.convert.converter.Converter;
/**
 * 
 * @ClassName: StringTrimConverter 
 * @Description: TODO(参数去空) 
 * @author LCN
 * @date 2015年5月8日 下午7:11:14
 */
public class StringTrimConverter implements Converter<String, String> {

	public String convert(String source) {
		//如果源字符串不为空则进行转换
		if(source != null){
			//去除源字符串前后空格
			source = source.trim();
			if(source.equals("")){ 
				source = null;
			}
		}
		return source;
	}
}
