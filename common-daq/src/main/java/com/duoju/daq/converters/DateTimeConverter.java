package com.duoju.daq.converters;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.core.convert.converter.Converter;
/**
 * 
 * @ClassName: DatetimeConverter 
 * @Description: TODO(将参数转换成精确到秒值的时间类型) 
 * @author LCN
 * @date 2015年5月8日 下午7:12:02
 */
public class DateTimeConverter implements Converter<String, Timestamp> {

	public Timestamp convert(String source) {

		if(source != null){
			source = source.trim();
			if(source.equals("")){ 
				source = null;
			}
			if(source != null){
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				try {
					Timestamp timestamp = new Timestamp(simpleDateFormat.parse(source).getTime());
					return timestamp;
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}
			}
		}
		return null;
		
	}
}
