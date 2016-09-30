package org.wsh.common.util.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * Project:     <common-util>
 * File Name:   <DateUtil.java>
 * Module ID:   <(模块)类编号，可以引用系统设计中的类编号>
 * Comments:  <日期工具类>
 * JDK version used:      <JDK1.6> 
 * @author wsh(王树辉) [wsh.ck@qq.com]
 * @since Date： 2016年1月13日 下午11:39:03
 */
public class DateUtil {
	
	private static final Logger log = LoggerFactory.getLogger(DateUtil.class);
	
	public static String YYYY = "yyyy";
	
	public static String MM = "MM";
	
	public static String DD = "MM";
	
	public static String YYYY_MM_DD = "yyyy-MM-dd";
	
	public static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	public static String YYYYMMDD = "yyyyMMdd";
	
	public static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
	
	/**
	 * 生成随机时间
	 * @param beginDate 开始日期
	 * @param endDate 结束日期
	 * @param simpleDateFormat 日期格式
	 * @return
	 */
	public static Date randomDate(String beginDate, String endDate, String simpleDateFormat) {

		try {
			SimpleDateFormat format = new SimpleDateFormat(simpleDateFormat);
			Date start = format.parse(beginDate);// 构造开始日期
			Date end = format.parse(endDate);// 构造结束日期
			// getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。
			if (start.getTime() >= end.getTime()) {
				return null;
			}
			long date = random(start.getTime(), end.getTime());
			return new Date(date);
		} catch (Exception e) {
			log.info("获取随机日期异常",e);
		}
		return null;
	}

	public static long random(long begin, long end) {

		long rtn = begin + (long) (Math.random() * (end - begin));
		// 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值
		if (rtn == begin || rtn == end) {
			return random(begin, end);
		}
		return rtn;
	}
	
	/**
	 * 获取昨天 00:00:00
	 * @return yyyy-MM-dd 00:00:00
	 */
	public static Date getYesterday0(){
		// 得到当前时间的前一天
		Date d = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 1);
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		String yesterday = sp.format(d) + " 00:00:00";// 获取昨天日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = null;
		try {
			beginDate = sf.parse(yesterday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return beginDate;
	}
	
	/**
	 * 获取昨天时间   23:59:59
	 * @return yyyy-MM-dd 23:59:59
	 */
	public static Date getYesterday9(){
		// 得到当前时间的前一天
		Date d = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 1);
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		String yesterday = sp.format(d) + " 23:59:59";// 获取昨天日期
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endDate = null;
		try {
			endDate = sf.parse(yesterday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}
	
	public static Date getDate0(String date){
		String dateStr = date + " 00:00:00";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date beginDate = null;
		try {
			beginDate = sf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return beginDate;
	}
	
	public static String getDateFormat0(String date){
		String dateStr = date + " 00:00:00";
		return dateStr;
	}
	
	public static String getDateFormat9(String date){
		String dateStr = date + " 23:59:59";
		return dateStr;
	}
	
	public static Date getDate9(String date){
		String dateStr = date + " 23:59:59";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date endDate = null;
		try {
			endDate = sf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return endDate;
	}
	
	/**
	 * 几天后
	 * @param date
	 * @return
	 */
	public static String addDays(Date date, int num){
		// 得到当前时间的前一天
		Date d = new Date(date.getTime() + 1000 * 60 * 60 * 24 * num);
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		return sp.format(d);
	}
	
	/**
	 * 日期转换(String转为Date)
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date parseDate(String dateStr, String format) {
		if (StringUtils.isBlank(dateStr))
			return null;
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = dateFormat.parse(dateStr);
		} catch (ParseException e) {
			log.warn("日期转换(String转为Date)出错：", e);
		}
		return date;
	}
	
	/**
	 * 日期转换(Date转为Date)
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static Date DateFormatDate(Date date, String format) {
		if (date == null)
			return null;
		DateFormat dateFormat = new SimpleDateFormat(format);
		Date resultDate = null;
		try {
			resultDate = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			log.warn("日期转换(Date转为Date)出错：", e);
		}
		return resultDate;
	}
	
	/**
	 * 日期转换(Date转为String)
	 * @param dateStr
	 * @param format
	 * @return
	 */
	public static String parseDate(Date date, String format) {
		if (StringUtils.isBlank(format) || date == null)
			return null;
		DateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	public static void main(String[] args) {
//		System.out.println(getYesterday0());
//		System.out.println(getYesterday9());
//		// 得到当前时间的前一天
//		Date d = new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 24 * 30);
//		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
//		String yesterday = sp.format(d) + " 00:00:00";// 获取昨天日期
//		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		Date beginDate = null;
//		try {
//			beginDate = sf.parse(yesterday);
//			System.out.println(beginDate);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		String date = "2014-08-03";
//		System.out.println(getDate0(date));;
		System.out.println(parseDate(new Date(),"yyyy-MM-dd HH:mm:ss"));
//		
//		System.out.println(parseDate("2014-8-7 0:00:05", "yyyy-MM-dd HH:mm:ss"));
//		System.out.println(addDays(new Date(), 25));
	}
	
	
	/**
	 * 获取指定日期所在月份的开始时间（一号零时）
	 * @param date
	 * @return date
	 */
	public static Date getMonthBegin(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
        cal.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天 
		return cal.getTime();
	}

	/**
	 * 获取指定日所在月的结束时间（当前月最后时间）
	 * @param date
	 * @return 格式：2010-09-31 23:59:59
	 */
	public static Date getMonthEnd(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, 1);
		cal.roll(Calendar.DATE, -1);
		return cal.getTime();
	}
	
	/**
	 * 获取指定日期所在星期的开始时间（周一早上零时）
	 * 
	 * @param date
	 * @return 格式：2010-09-31 00:00:00
	 */
	public static Date getWeekBegin(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		// 代表今天是星期几，周日---周六 返回 1---7
		int today = cal.get(Calendar.DAY_OF_WEEK);
		// 这里一个星期的开始是周一而非周日
		if (1 == today) {
			cal.add(Calendar.DATE, -6);
		} else {
			cal.add(Calendar.DATE, -today + 2);
		}
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE), 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 获取指定日所在星期的结束时间（周日晚上零时）
	 * 
	 * @param date
	 * @return 格式：2010-09-31 23:59:59
	 */
	public static Date getWeekEnd(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		// 代表今天是星期几，周日---周六 返回 1---7
		int today = cal.get(Calendar.DAY_OF_WEEK);
		// 这里一个星期的开始是周一而非周日
		if (1 == today) {
			cal.add(Calendar.DATE, 1);
		} else {
			cal.add(Calendar.DATE, -today + 9);
		}
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
				cal.get(Calendar.DATE), 0, 0, -1);
		return cal.getTime();
	}
	
	/**
	 * 日期计算（天）
	 * @param date 传入日期
	 * @param num 加减天数
	 * @return
	 */
	public static Date calculateDays(Date date, int num){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) + num);
		return cal.getTime();
	}
	
	/**
	 * 开始加时间一直到大于结束时间为止
	 * @param strartTime
	 * @param endTime
	 * @return
	 */
	public static List<Date> addMinute(Date strartTime, Date endTime, int minuteNum){
		
		List<Date> list = new ArrayList<Date>();
		list.add(strartTime);
		Date date = calculateDays(strartTime,minuteNum);
		while (date.before(endTime)) {
			list.add(date);
			date = calculateMinute(date,minuteNum);
		}
		return list;
	}
	
	/**
	 * 日期计算（分钟）
	 * @param date 传入日期
	 * @param num 加减分钟
	 * @return
	 */
	public static Date calculateMinute(Date date, int num){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, num);
		return cal.getTime();
	}
	
	/**
	 * 日期计算（秒）
	 * @param date 传入日期
	 * @param secondNum 加减秒
	 * @return
	 */
	public static Date calculateSecond(Date date, int secondNum){
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		cal.add(Calendar.SECOND, secondNum);
		return cal.getTime();
	}
	
	/**
	 * 将日期转换为cron表达式
	 * @param date 时间
	 * @return
	 */
	public static String formatDateForCron(Date date){  
        SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");  
        String formatTimeStr = null;  
        if (date != null) {  
            formatTimeStr = sdf.format(date);  
        }  
        return formatTimeStr;  
     }  
	
}

