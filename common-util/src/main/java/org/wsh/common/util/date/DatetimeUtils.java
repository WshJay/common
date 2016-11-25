/**
 * File Name: com.woyo.travel.hotel.util.DatetimeUtils.java
 * Encoding UTF-8
 * Version: 1.0
 * Date: 2010-5-19
 * History: 
 */
package org.wsh.common.util.date;

import org.apache.commons.lang.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期时间工具类
 * 
 * @author jianghao
 */
public class DatetimeUtils {

	private static final String ZERO_PREFIX = "0";

	private static final String COLON = ":";

	// 月份
	public static final String[] strMonths = { "JAN", "FEB", "MAR", "APR",
			"MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC" };

	public static final String YYYYMMDDHHmmss = "yyyy-MM-dd HH:mm:ss";
	/**
	 * 禁止外部实例化
	 * 
	 */
	private DatetimeUtils() {

	}

	/**
	 * 获取当前时间指定格式的字符串
	 * 
	 * @param format
	 * @return
	 */
	public static String getCurDate(String format) {
		return formatDate(new Date(), format);
	}

	/**
	 * 获取当前时间的“yyyy-MM-dd”格式字符串
	 * 
	 * @return
	 */
	public static String getCurDate() {
		return getCurDate("yyyy-MM-dd");
	}

	/**
	 * 返回时间格式后的字符串 格式为：“yyyy-MM-dd”
	 * 
	 * @param aDate
	 * 
	 * @return String 格式化后的字符串
	 */
	public static String shortDate(Date aDate) {
		if (aDate == null)
			return "";
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(aDate);
	}

	/**
	 * 获取每月第一天的“yyyy-MM-dd”格式字符串
	 * 
	 * @return
	 */
	public static String getFirstDayOfMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return formatDate(cal.getTime());
	}

	/**
	 * 获取每年第一天的“yyyy-MM-dd”格式字符串
	 * 
	 * @return
	 */
	public static String getFirstDayOfYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return formatDate(cal.getTime());
	}

	/**
	 * 获取每周第一天的“yyyy-MM-dd”格式字符串（以星期一作为一周的开始）
	 * 
	 * @return
	 */
	public static String getFirstDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY); // 以星期一作为一周的开始
		return formatDate(cal.getTime());
	}

	/**
	 * 获取本周第一天
	 * 
	 * @return
	 */
	public static Date get1stDayOfWeek() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_YEAR, 7); // 以星期一作为一周的开始
		return cal.getTime();
	}

	/**
	 * 获取本月第一天
	 * 
	 * @return
	 */
	public static Date get1stDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_YEAR, 30);
		return calendar.getTime();
	}

	/**
	 * 获取每周最后一天的“yyyy-MM-dd”格式字符串（以星期天作为一周的结束）
	 * 
	 * @return
	 */
	public static String getLastDayOfLastWeek() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);
		return formatDate(cal.getTime());
	}

	/**
	 * 获取上个月第一天的“yyyy-MM-dd”格式字符串
	 * 
	 * @return
	 */
	public static String getFirstDayOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		return formatDate(cal.getTime());
	}

	/**
	 * 获取上个月最后一天的“yyyy-MM-dd”格式字符串
	 * 
	 * @return
	 */
	public static String getLastDayOfLastMonth() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return formatDate(cal.getTime());
	}

	/**
	 * 获取去年第一天的“yyyy-MM-dd”格式字符串
	 * 
	 * @return
	 */
	public static String getFirstDayOfLastYear() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return formatDate(cal.getTime());
	}

	/**
	 * 获取去年最后一天的“yyyy-MM-dd”格式字符串
	 * 
	 * @return
	 */
	public static String getLastDayOfLastYear() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return formatDate(cal.getTime());
	}

	/**
	 * 取得前n年第一天的“yyyy-MM-dd”格式字符串。<br>
	 * 如果n值过大，导致取得的时间在公元1年以前，那么会导致结果不准确，所以会抛出 IllegalArgumentException异常。
	 * 
	 * @param n
	 * @return
	 */
	public static String getFirstDayOfPastYear(int n) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		if (year - n <= 0)
			throw new IllegalArgumentException("parameter is too large");

		cal.add(Calendar.YEAR, (0 - n));
		cal.set(Calendar.DAY_OF_YEAR, 1);
		return formatDate(cal.getTime());
	}

	/**
	 * 取得前n年最后一天的“yyyy-MM-dd”格式字符串。<br>
	 * 如果n值过大，导致取得的时间在公元1年以前，那么会导致结果不准确，所以会抛出 IllegalArgumentException异常。
	 * 
	 * @param n
	 * @return
	 */
	public static String getLastDayOfPastYear(int n) {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		if (year - n <= 0)
			throw new IllegalArgumentException("parameter is too large");

		cal.add(Calendar.YEAR, (1 - n));
		cal.set(Calendar.DAY_OF_YEAR, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		return formatDate(cal.getTime());
	}

	/**
	 * 以指定格式格式化时间
	 * 
	 * @param date
	 * @param formart
	 * @return
	 */
	public static String formatDate(Date date, String format) {
		if (date == null)
			return StringUtils.EMPTY;

		if (format == null || format.trim().equals(""))
			format = "yyyy-MM-dd";

		SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
		return dateFormatter.format(date);
	}
	
	/**
	 * 以指定格式格式化时间
	 * 
	 * @param date
	 * @param formart yyyyMMdd
	 * @return
	 */
	public static String formatDate2(Date date, String format) {
		if (date == null)
			return StringUtils.EMPTY;

		if (format == null || format.trim().equals(""))
			format = "yyyyMMdd";

		SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
		return dateFormatter.format(date);
	}

	/**
	 * 以“yyyy-MM-dd”格式化时间
	 * 
	 * @param date
	 * @param formart
	 * @return
	 */
	public static String formatDate(Date date) {
		return formatDate(date, null);
	}

	/**
	 * 根据传入的日期以及月份数计算并返回日期
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMonth(Date date, int months) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, months);

		return cal.getTime();
	}

	/**
	 * 根据传入的日期以及天数计算并返回日期
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addDate(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);

		return cal.getTime();
	}

	/**
	 * 根据传入的日期以及分钟数计算并返回日期
	 * 
	 * @param date
	 * @param months
	 * @return
	 */
	public static Date addMinute(Date date, int minutes) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, minutes);

		return cal.getTime();
	}

	/**
	 * 根据传入的日期以及小时数计算并返回日期
	 * 
	 * @param date
	 * @param hours
	 * @return
	 */
	public static Date addHour(Date date, int hours) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);

		return cal.getTime();
	}

	/**
	 * 根据传入的日期以及年数计算并返回日期
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addYear(Date date, int years) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, years);

		return cal.getTime();
	}

	/**
	 * 根据传入的日期以及星期数计算并返回日期
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	public static Date addWeek(Date date, int weeks) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.WEEK_OF_MONTH, weeks);

		return cal.getTime();
	}
	
	/**
	 * 将String类型日期转换为Date类型的日期
	 * @param strDate
	 * @param formatter
	 * @return
	 */
	public static Date parseDateByStr(String strDate,String formatter) {
		if(formatter == null || formatter.trim().equals("")){
			formatter = "yyyy-MM-dd";
		}
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat(formatter);
		//必须捕获异常
		try{ 
		   Date date=simpleDateFormat.parse(strDate);
		   return date;
		}catch(ParseException ex){
		   ex.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据传入的日期时间字符串生成日期对象 传入的日期字符串格式为 YYYY-MM-DD hh:mm:ss
	 * 如果hh:mm:ss没有设置，默认为00:00:00
	 * 
	 * @param str
	 * @return
	 */
	public static Date parseDateByStr(String str) {
		Calendar cal = Calendar.getInstance();
		if (str == null) {
			return null;
		}

		String dateArray[] = str.split("-");
		if (dateArray.length != 3) {
			return null;
		}

		int year = Integer.parseInt(dateArray[0]);
		int month = Integer.parseInt(dateArray[1]);
		int date = 1;
		int hour = 0;
		int minute = 0;
		int second = 0;
		if (dateArray[2].indexOf(":") > 0) {
			int i = dateArray[2].indexOf(" ");
			date = Integer.parseInt(dateArray[2].substring(0, i));
			if (i > 0) {
				String hmStr = dateArray[2].substring(i + 1);
				String[] hmArray = hmStr.split(":");
				if (hmArray.length >= 2) {
					hour = Integer.parseInt(hmArray[0]);
					minute = Integer.parseInt(hmArray[1]);
					if (hmArray.length == 3) {
						second = Integer.parseInt(hmArray[2]);
					}
				}
			}
		} else {
			date = Integer.parseInt(dateArray[2]);
		}

		cal.set(year, month - 1, date, hour, minute, second);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	/**
	 * 取得指定日期时间的指定field（比如，年、月、日）的值
	 * 
	 * @param cal
	 * @param field
	 *            the given calendar field
	 * @return
	 */
	public static int get(Calendar cal, int field) {
		return cal.get(field);
	}
	
	/**
	 * @param startDate 开始时间
	 * @param endDate   结束时间--
	 * @param everyDay 是否是每天-true为每天!
	 * @param dayType   天的类型 ---数据参照RouteWeekEnum该枚举类	 * 
	 * @return
	 */
	public static List<Date> getListDate(Date startDate, Date endDate ,boolean everyDay,int [] dayType){
		/**
		 * 得到两个日期的时间差-包含开始日期和结束日期
		 * */
		List<Date> listDate = new ArrayList<Date>();
		int dayNum = getBetweenDayNum(startDate, endDate);
		for (int i = 0; i < dayNum; i++) {
			int typeNum = dayType.length;
			Date  date = addDate(startDate, i);
			int weekNum = getWeekDayNumber(date);
			if(everyDay){
				listDate.add(date);	
			}else{
				for (int j = 0; j < typeNum; j++) {
					int type = dayType[j];
					if (type == weekNum ){
						listDate.add(date);
					}
				}
			}
			
		}
		return listDate;
	}
	/***
	 * 根据开始日期、结束日期、班期（145:表示周一、周四、周五）
	 * 得到满足条件的日期集合
	 * 
	 */
	public static List<Date> getDateWeek(Date startDate, Date endDate, String weekDay){
		List<Date> liatDate = new ArrayList<Date>();
		/****
		 * 算出该日期区间的时间差
		 */
		int num = getBetweenDayNum(startDate, endDate);
		for (int i = 0; i < num; i++) {
			Date newDate = addDate(startDate, i);
			int weekIndex =  getWeekIndex(newDate);
			/**
			 * 判断是否包含日期
			 */
			if(weekDay.indexOf(String.valueOf(weekIndex)) != -1){
				liatDate.add(newDate);
			}
		}
		return liatDate;
	}
	/**
	 * 取得当前日期时间的指定field（比如，年、月、日）的值
	 * 
	 * @param field
	 * @return
	 * @see #get(Calendar, int)
	 */
	public static int get(int field) {
		return get(Calendar.getInstance(), field);
	}

	/**
	 * 取得当前年份
	 * 
	 * @return
	 */
	public static int getCurYear() {
		return get(Calendar.YEAR);
	}

	/**
	 * 取得两个时间相隔的天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getIntervalDays(Date startDate, Date endDate) {
		long start = startDate.getTime();
		long end = endDate.getTime();

		int residue =  (int)(Math.abs(end - start) % (24 * 60 * 60 * 1000));
		if(residue > 0) {
			return ((int)(Math.abs(end - start) / (24 * 60 * 60 * 1000))) + 1;
		}else {
			return ((int)(Math.abs(end - start) / (24 * 60 * 60 * 1000)));
		}
	}
	
	/**
	 * 取得两个时间总的天数，包含头尾
	 * 
	 * @param startDate 只支持yyyy-MM-dd格式的日期
	 * @param endDate 只支持yyyy-MM-dd格式的日期
	 * @return
	 */
	public static int getBetweenDayNum(Date startDate, Date endDate) {
		long start = startDate.getTime();
		long end = endDate.getTime();

		return (int)(Math.abs(end - start) / (24 * 60 * 60 * 1000)) + 1;
	}

	/**
	 * 取得两个时间相隔的间夜数 如：2010-01-01到2010-01-03,相隔2晚
	 * 
	 * @param startDate 只支持yyyy-MM-dd格式的日期
	 * @param endDate 只支持yyyy-MM-dd格式的日期
	 * @return
	 */
	public static int getIntervalNights(Date startDate, Date endDate) {
		long start = startDate.getTime();
		long end = endDate.getTime();

		return (int) (Math.abs(end - start) / (24 * 60 * 60 * 1000));
	}

	/**
	 * 取得指定日期是星期几
	 * 
	 * @return 星期一，星期二，...
	 */
	public static String getWeekDay(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return getWeekDayDesc(cal.get(Calendar.DAY_OF_WEEK));
	}

	/**
	 * <p>
	 * Description: 取得指定日期是星期几的数字表示，星期天为0
	 * </p>
	 * 
	 * @param 输入日期
	 * @return 0,1,2,...
	 * @author LeoLiang
	 * @Created 2010-11-11
	 */
	public static int getWeekDayNumber(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal.get(Calendar.DAY_OF_WEEK) - 1;
	}
	
	/**
	 * 星期天返回7
	 * 
	 * @param date
	 * @return
	 */
	public static int getWeekIndex(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int index = cal.get(Calendar.DAY_OF_WEEK) - 1;
		
		//周日
		if(index == 0) {
			return 7;
		}
		
		return index;
	}

	/**
	 * 
	 * 
	 * @param seconds
	 * @return
	 */
	public static String getPeriodString(long seconds) {
		long hour = seconds / (60 * 60);
		seconds = seconds % (60 * 60);
		return addZeroPrefix(hour) + COLON + addZeroPrefix((seconds / 60))
				+ COLON + addZeroPrefix((seconds % 60));
	}

	// 倒计时
	public static String countdown(Date date, String format, long subjectId,
			short subjectStatus) {
		if (date == null)
			return null;
		StringBuilder sb = new StringBuilder();
		if (format.equals("天时")) {
			// 过期时间
			long validTime = date.getTime();

			long now = System.currentTimeMillis();

			long dur = validTime - now;

			if (dur < 0) {
				return " 00天 00时";
			}
			System.out.println(dur);
			// 天
			int day = (int) (dur / (60 * 60 * 24 * 1000));
			System.out.println(day);
			if (day < 10)
				sb.append(0);
			sb.append(day);
			sb.append(" 天");

			// 小时
			int hours = (int) ((dur - (long) day * (60 * 60 * 24 * 1000)) / (60 * 60 * 1000));
			if (hours < 10)
				sb.append(0);
			sb.append(hours);
			sb.append(" 时");
			System.out.println(hours);
		} else if (format.equals("天时分秒")) {
			// 过期时间
			long validTime = date.getTime();

			long now = System.currentTimeMillis();

			long dur = validTime - now;
			if (dur < 0) {
				return " 00天 00时 00分 00秒";
			}
			// 天
			int day = (int) (dur / (60 * 60 * 24 * 1000));
			if (day < 10)
				sb.append(0);
			sb.append(day);
			sb.append(" 天");
			// System.out.println(day);

			// 小时
			int hours = (int) ((dur - (long) day * (60 * 60 * 24 * 1000)) / (60 * 60 * 1000));
			if (hours < 10)
				sb.append(0);
			sb.append(hours);
			sb.append(" 时");
			// System.out.println(hours);

			// 分钟
			int min = (int) ((dur - (long) day * (60 * 60 * 24 * 1000) - (long) hours
					* (60 * 60 * 1000)) / (60 * 1000));
			if (min < 10)
				sb.append(0);
			sb.append(min);
			sb.append(" 分");
			// System.out.println(min);

			// 秒
			int seconds = (int) ((dur - (long) day * (60 * 60 * 24 * 1000)
					- (long) hours * (60 * 60 * 1000) - min * (60 * 1000)) / 1000);
			if (seconds < 10)
				sb.append(0);
			sb.append(seconds);
			sb.append(" 秒");
			System.out.println(seconds);
		}

		return sb.toString();
	}

	/**
	 * <p>
	 * Description: 计算时间间隔
	 * </p>
	 * 
	 * @param 输入开始时间
	 *            结束是时间
	 * @return 格式化好的日期
	 * @author terry.sha
	 * @Created 2010-6-9 上午09:56:28
	 */
	public static String dateInterval(Date beginDate, Date endDate) {
		long d1 = beginDate.getTime();
		long d2 = endDate.getTime();

		long millisecond = d2 - d1;
		;
		// 判断是否超过一个小时
		float interv = Math.abs((float) millisecond / (1000 * 60 * 60));
		if (interv >= 1.0) { // 大于一个小时
			SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日  HH:mm");
			return sdf.format(beginDate);

		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("mm分");
			return sdf.format(new Date(millisecond));
		}
	}

	private static String getWeekDayDesc(int weekDay) {
		switch (weekDay) {
		case Calendar.SUNDAY:
			return "星期日";

		case Calendar.MONDAY:
			return "星期一";

		case Calendar.TUESDAY:
			return "星期二";

		case Calendar.WEDNESDAY:
			return "星期三";

		case Calendar.THURSDAY:
			return "星期四";

		case Calendar.FRIDAY:
			return "星期五";

		case Calendar.SATURDAY:
			return "星期六";

		default:
			return "";
		}
	}

	private static String addZeroPrefix(long num) {
		if (num < 0) {
			throw new IllegalArgumentException("parameter must not be negative");
		}

		if (num < 10) {
			return ZERO_PREFIX + num;
		}

		return num + "";
	}

	/**
	 * <p>
	 * Description: 计算时间间隔 如几点几分之前
	 * </p>
	 * 
	 * @param 输入开始时间
	 *            结束是时间
	 * @return 格式化好的日期
	 * @author terry.sha
	 * @Created 2010-6-9 上午09:56:28
	 */
	public static String timeInterval(Date beginDate, Date endDate) {
		long d1 = beginDate.getTime();
		long d2 = endDate.getTime();
		long millisecond = d2 - d1;
		;
		// 判断是否超过一个小时
		float interv = Math.abs((float) millisecond / (1000 * 60 * 60));
		if (interv >= 1.0) { // 大于一个小时
			SimpleDateFormat sdf = new SimpleDateFormat("MM月dd日  HH:mm");
			return sdf.format(beginDate);

		} else {
			SimpleDateFormat sdf = new SimpleDateFormat("m分钟前");
			String ret = sdf.format(new Date(millisecond));
			if (ret.equals("0分钟前")) {
				ret = "1分钟前";
			}
			return ret;
		}
	}

	public static void main(String[] args) {
		// System.out.println(getFirstDayOfPastYear(10));
		// System.out.println(getLastDayOfPastYear(10));
		// //Calendar cal = Calendar.getInstance();
		// System.out.println(formatDate(new Date(), "yyyy年MM月dd日星期E"));
		// //cal.add(Calendar.YEAR, -200);
		// //System.out.println(cal.get(Calendar.YEAR));
		// System.out.println(formatDate(addWeek(new Date(), 52)));

//		System.out.println(getPeriodString(58));
//		System.out.println(getPeriodString(60 * 45));
//		System.out.println(getPeriodString(60 * 45 + 32));
//		System.out.println(getPeriodString(60 * 60 * 3 + 60 * 24 + 23));
		//getChrMonthDate(new Date());
		//System.out.println(getWeekDayNumber(new Date()));
		
		Date date = DatetimeUtils.parseDateByStr("2010-12-28 10:10:10");
		System.out.println(date.getTime());
		Date date2  = DatetimeUtils.parseDateByStr("2010-12-29 10:10:10", "yyyy-MM-dd HH:mm:ss");
		System.out.println(DatetimeUtils.getIntervalDays(date, date2));
		
	}

	/**
	 * 计算两个日期间相隔的周数
	 * 
	 * @param startDate
	 *            开始日期
	 * @param endDate
	 *            结束日期
	 * @return 0:表示两个日期在一周之内
	 */
	public static int computeWeeks(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		int s = cal.get(Calendar.WEEK_OF_YEAR);
		cal.setTime(endDate);
		int e = cal.get(Calendar.WEEK_OF_YEAR);
		return (e - s);
	}
  
	/**
	 * 得到如"29OCT10"的字母月的日期格式
	 * 
	 * @param date
	 * @return String
	 */
	public static String getChrMonthDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeZone(TimeZone.getTimeZone("GMT+8"));
		cal.setTime(date);
		String year = String.valueOf(cal.get(Calendar.YEAR));
		year = year.substring(year.length() - 2, year.length());
		int month = cal.get(Calendar.MONTH);
		String day = String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
		if (Integer.parseInt(day) < 10) {
			day= ZERO_PREFIX + day;
		}
		return day+"!"+strMonths[month]+"!"+year;
	}
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @param includeStart
	 * @param includeEnd
	 * @return
	 */
	public static List<Date> getBetweenDays(Date start, Date end, boolean includeStart, boolean includeEnd) {
		List<Date> dateList = new LinkedList<Date>();
		int dayNum = getBetweenDayNum(start, end);
		for(int i = (includeStart ? 0 : 1); i < (includeEnd ? dayNum : dayNum - 1); i++) {
			dateList.add(addDate(start, i));
		}
		
		return dateList;
	}
	
	/**
	 * 
	 * @param start
	 * @param end
	 * @param includeStart
	 * @param includeEnd
	 * @param formatPattern
	 * @return
	 */
	public static List<String> getBetweenDayStrList(Date start, Date end, boolean includeStart, boolean includeEnd, String formatPattern) {
		List<String> dateList = new LinkedList<String>();
		int dayNum = getBetweenDayNum(start, end);
		for(int i = (includeStart ? 0 : 1); i < (includeEnd ? dayNum : dayNum - 1); i++) {
			dateList.add(formatDate(addDate(start, i), formatPattern));
		}
		
		return dateList;
	}
	/****
	 * 酒店日历
	 * 构建日历选择框
	 */
	public static List<String> getHotelCheckDate(){
		List<String> list = new ArrayList<String>();
		for (int i = 8; i < 24; i++) {
			if(i < 10){
				StringBuffer hour = new StringBuffer();
				hour.append("0"+i+":00");
				list.add(hour.toString());
				StringBuffer halfHour = new StringBuffer();
				halfHour.append("0"+i+":30");
				list.add(halfHour.toString());
			}else{
				StringBuffer hour = new StringBuffer();
				hour.append(i+":00");
				list.add(hour.toString());
				StringBuffer halfHour = new StringBuffer();
				halfHour.append(i+":30");
				list.add(halfHour.toString());
			}
		}
		list.add("23.59");
		return list;
	}
	
	/****
	 * 酒店日历
	 * 构建日历选择框
	 */
	public static List<String> getSystemDate(){
		List<String> list = new ArrayList<String>();
		int currhour = new Date().getHours();
		for (int i = currhour; i < 24; i++) {
			if(i < 10){
				StringBuffer hour = new StringBuffer();
				hour.append("0"+i+":00");
				list.add(hour.toString());
				StringBuffer halfHour = new StringBuffer();
				halfHour.append("0"+i+":30");
				list.add(halfHour.toString());
			}else{
				StringBuffer hour = new StringBuffer();
				hour.append(i+":00");
				list.add(hour.toString());
				StringBuffer halfHour = new StringBuffer();
				halfHour.append(i+":30");
				list.add(halfHour.toString());
			}
		}
		list.add("23:59");
		return list;
	}
}
