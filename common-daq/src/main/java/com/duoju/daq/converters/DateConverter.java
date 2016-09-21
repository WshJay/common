package com.duoju.daq.converters;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebBindingInitializer;
import org.springframework.web.context.request.WebRequest;

/**
 * 
 * @ClassName: DateConverter
 * @Description: TODO(将参数转换成精确到天数的时间类型)
 * @author LCN
 * @date 2015年5月8日 下午7:13:29
 */
public class DateConverter implements WebBindingInitializer {

	@Override
	public void initBinder(WebDataBinder binder, WebRequest request) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		dateFormat.setLenient(false);

		SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		datetimeFormat.setLenient(false);

		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

		//binder.registerCustomEditor(java.sql.Timestamp.class,new CustomTimestampEditor(datetimeFormat, true));

		binder.registerCustomEditor(java.sql.Timestamp.class, new CustomDateEditor(datetimeFormat, true));
	}

}
