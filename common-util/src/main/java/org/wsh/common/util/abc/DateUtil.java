/*
 * Created on 2004-1-3
 *
 * To change the template for this generated file go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
package org.wsh.common.util.abc;
/** 
 * @author lcn
 * @version 创建时间：2014年8月8日 下午5:05:59 
 * 
 */

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 时间工具类
 * 
 * @author lcn
 * 
 */
public class DateUtil {
	/** SimpleDateFormat 格式为：“MM/dd/yyyy” */
	public static final DateFormat DATE_FORMAT = new SimpleDateFormat(
			"MM/dd/yyyy");
	/** SimpleDateFormat 格式为：“EEE, MMM d, yyyyy hh:mm:ss aa z” */
	public static final DateFormat FULL_DATE_FORMAT = new SimpleDateFormat(
			"EEE, MMM d, yyyyy hh:mm:ss aa z");
	/** SimpleDateFormat 格式为：“yyyy-MM-dd'T'HH:mm:ss.SS z” */
	public static final DateFormat ISO8601_DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd'T'HH:mm:ss.SS z");
	/** 一天的毫秒数 */
	public static final long MILLSECOND_OF_DAY = 86400000;

	/** 时间格式化对象 */
	private static SimpleDateFormat formatter;

	/** 数据查询开始时间 */
	public static final String startDate = "20100228";
	/** 图表开始日期 */
	public static final Date startFormatDate = DateUtil.getDate("2010-02-28");

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
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(aDate);
	}

	/**
	 * 取得当前日期的最后时间
	 * 
	 * @param date
	 *            日期
	 * @return Date e 日期
	 */
	public static Date getLastTime(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.HOUR_OF_DAY, 23);
		cl.set(Calendar.MINUTE, 59);
		cl.set(Calendar.SECOND, 59);
		return cl.getTime();
	}

	/**
	 * 取得当前日期的最开始时间
	 * 
	 * @param date
	 *            日期
	 * @return Date e 日期
	 */
	public static Date getFirstTime(Date date) {
		Calendar cl = Calendar.getInstance();
		cl.setTime(date);
		cl.set(Calendar.HOUR_OF_DAY, 00);
		cl.set(Calendar.MINUTE, 00);
		cl.set(Calendar.SECOND, 00);
		return cl.getTime();
	}

	/**
	 * 返回时间格式后的的字符串 格式为：“yyyMMddHHmm”
	 * 
	 * @param aDate
	 *            要格式化的时间
	 * @return String 格式化后的字符串
	 */
	public static String mailDate(Date aDate) {
		if (aDate == null)
			return "";
		formatter = new SimpleDateFormat("yyyyMMddHHmm");
		return formatter.format(aDate);
	}

	/**
	 * 返回时间格式后的的字符串 格式为：“yyyy-MM-dd HH:mm:ss”
	 * 
	 * @param aDate
	 *            要格式化的时间
	 * @return String 格式化后的字符串
	 */
	public static String longDate(Date aDate) {
		if (aDate == null)
			return "";
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(aDate);
	}
	
	
	/**
	 * 返回时间格式后的的字符串 格式为：“yyyy-MM-dd”
	 * 
	 * @param aDate
	 *            要格式化的时间
	 * @return String 格式化后的字符串
	 */
	public static String clongDate(Date aDate) {
		if (aDate == null)
			return "";
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		return formatter.format(aDate);
	}

	
	
	/**
	 * 返回时间格式后的的字符串 格式为：“yyyy'年'MM'月'dd'日'”
	 * 
	 * @param aDate
	 *            要格式化的时间
	 * @return String 格式化后的字符串
	 */
	public static String shortDateGB(Date aDate) {
		if (aDate == null)
			return "";
		formatter = new SimpleDateFormat("yyyy'年'MM'月'dd'日'");
		return formatter.format(aDate);
	}

	/**
	 * 返回时间格式后的的字符串 格式为：“yyyy-MM-dd HH:mm:ss”
	 * 
	 * @param aDate
	 *            要格式化的时间
	 * @return String 格式化后的字符串
	 */
	public static String longDateGB(Date aDate) {
		if (aDate == null)
			return "";
		formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return formatter.format(aDate);
	}

	/**
	 * 返回时间格式后的的字符串
	 * 
	 * @param aDate
	 *            要格式化的时间
	 * @param formatStr
	 *            格式化的配置字符串
	 * @return String 格式化后的字符串
	 */
	public static String formatDate(Date aDate, String formatStr) {
		if (aDate == null)
			return "";
		formatter = new SimpleDateFormat(formatStr);
		return formatter.format(aDate);

	}

	/**
	 * 通过字符串创建时间对象
	 * 
	 * @param yyyymmdd
	 *            字符串
	 * @return Date 时间对象
	 */
	public static Date getDate(String yyyymmdd) {
		if (yyyymmdd == null)
			return null;
		int year = Integer
				.parseInt(yyyymmdd.substring(0, yyyymmdd.indexOf("-")));
		int month = Integer.parseInt(yyyymmdd.substring(
				yyyymmdd.indexOf("-") + 1, yyyymmdd.lastIndexOf("-")));
		int day = Integer
				.parseInt(yyyymmdd.substring(yyyymmdd.lastIndexOf("-") + 1));
		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day);
		return cal.getTime();

	}

	/**
	 * 将字符串转化成时间对象
	 * 
	 * @param strDate
	 *            字符串
	 * @return Date 时间对象
	 */
	public static Date parser(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据字符串得到 短的时间类型
	 * 
	 * @param date
	 *            时间字符串
	 * @return Date 时间对象
	 */
	public static Date getShortDate(String date) {
		Date shortDate = null;
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			shortDate = formatter.parse(date);
		} catch (Exception e) {
			shortDate = null;
		}
		return shortDate;
	}

	/**
	 * 时间比较 相等返回true 不相等返回false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean equals(Date date1, Date date2) {
		if (date1 == null && date2 == null)
			return true;
		if (date1 == null && date2 != null)
			return false;
		if (date1 != null && date2 == null)
			return false;
		return date1.equals(date2);
	}

	/**
	 * 当前的“日期”时间滚动一天
	 * 
	 * @return Date 时间对象
	 */
	public static Date tomorrow() {
		Calendar calender = Calendar.getInstance();
		calender.roll(Calendar.DAY_OF_YEAR, true);
		return calender.getTime();
	}

	/**
	 * 当前时间加一天
	 *
	 * @param date
	 *            时间对象
	 * @return Date 时间对象
	 */
	public static Date nextDate(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.roll(Calendar.DAY_OF_YEAR, 1);
		if (isEndOfYear(date, cal.getTime())) {
			cal.roll(Calendar.YEAR, true);
			cal.roll(Calendar.DAY_OF_YEAR, 1);
		}
		return cal.getTime();
	}

	private static boolean isEndOfYear(Date curDate,
			Date rollUpDate) {
		return (curDate.compareTo(rollUpDate) >= 0);
	}

	/**
	 * 当前时间是一周当中的第几天
	 *
	 * @return Date 时间对象
	 */
	public static Date yesterday() {
		Calendar calender = Calendar.getInstance();
		calender.roll(Calendar.DAY_OF_YEAR, false);
		return calender.getTime();
	}

	private static final String getDateFormat(Date aDate) {
		if (aDate == null)
			return null;
		SimpleDateFormat formatter = new SimpleDateFormat("M/d/yyyy");
		return formatter.format(aDate);
	}

	/**
	 * 得到格式化过的时间字符串，格式为“M/d/yyyy”
	 *
	 * @param aDate
	 *            时间对象
	 * @return String 字符串
	 */
	public static String NVL(Date date) {
		if (date == null)
			return "";
		else
			return getDateFormat(date);
	}

	/**
	 * 给时间指定的时间域加时间</br> type =1 表示year</br> type= 2表示 month</br> type=3 表示 date
	 * </br> type = 4表示 hour_of_day</br> type = 5 表示minute </br> type = 6 表示
	 * second
	 * 
	 * @param 时间对象
	 * @param type
	 *            类型
	 * @param num
	 *            数量
	 * @return 时间对象
	 */
	public static Date addDate(Date baseDate, int type, int num) {
		Date lastDate = null;
		try {
			Calendar cale = Calendar.getInstance();
			cale.setTime(baseDate);
			if (type == 1) {
				cale.add(Calendar.YEAR, num);
			} else if (type == 2) {
				cale.add(Calendar.MONTH, num);
			} else if (type == 3) {
				cale.add(Calendar.DATE, num);
			} else if (type == 4) {
				cale.add(Calendar.HOUR_OF_DAY, num);
			} else if (type == 5) {
				cale.add(Calendar.MINUTE, num);
			} else if (type == 6) {
				cale.add(Calendar.SECOND, num);
			}
			lastDate = cale.getTime();
			return lastDate;
		} catch (Exception e) {
			return null;
		}

	}

	/**
	 * 返回用指定字符串格式化后的时间
	 * 
	 * @param strDate
	 *            时间字符串
	 * @param formatter
	 *            格式化字符串
	 * @return Date 返回的时间对象
	 */
	public static Date getDate(String strDate, String formatter) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatter);
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 得到系统时间
	 * 
	 * @return Date 时间对象
	 */
	public static Date getSysDate() {
		return new Date(System.currentTimeMillis());
	}

	/**
	 * 得到当前时间的一天的开始时间或结束时间</br>跟据flag=true 表示得到结束时间</br>flag = false 表示得到 开始时间
	 * 
	 * @param flag
	 *            标志
	 * @return Date 时间对象
	 */
	public static Date getCycleDate(boolean flag) {
		Calendar calendar = Calendar.getInstance();
		String year = "";
		String month = "";
		String dateStr = "";
		if (flag) {
			year = "" + calendar.get(Calendar.YEAR);
			month = "" + (calendar.get(Calendar.MONTH) + 1);
			if (month.length() < 2) {
				month = "0" + month;
			}
			dateStr = year + "-" + month + "-20 23:59:59";
		} else {
			calendar.add(Calendar.MONTH, 1);
			year = "" + calendar.get(Calendar.YEAR);
			month = "" + (calendar.get(Calendar.MONTH) + 1);
			if (month.length() < 2) {
				month = "0" + month;
			}
			dateStr = year + "-" + month + "-20 00:00:00";
		}
		return DateUtil.parser(dateStr);

	}

	/**
	 * 得到下一个月当前日期的 开始时间
	 * 
	 * @param currentDate
	 *            当前的时间
	 * @return Date 时间对象
	 */
	public static Date getNextCycleDate(Date currentDate) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentDate);

		String year = "" + calendar.get(Calendar.YEAR);
		String month = (calendar.get(Calendar.MONTH) + 2) + "";
		if (month.length() < 2) {
			month = "0" + month;
		}
		String dateStr = year + "-" + month + "-01 00:00:00";
		return DateUtil.parser(dateStr);
	}

	public static long getDaysBetweenDate(Date startDate, Date endDate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		startDate = cal.getTime();
		cal.setTime(endDate);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (cal.getTime().getTime() - startDate.getTime()) / 86400000;

	}

	public static long getDaysBetweenDate(Date startDate, Date endDate,
			int roundingMode) {
		BigDecimal bStart = new BigDecimal(startDate.getTime());
		BigDecimal bEnd = new BigDecimal(endDate.getTime());
		BigDecimal bUnit = new BigDecimal("86400000");
		return (bEnd.subtract(bStart)).divide(bUnit, roundingMode).longValue();
	}

	public static long getMonthsBetweenDate(Date startDate, Date endDate,
			boolean flag) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(startDate);
		cal2.setTime(endDate);
		if (endDate.before(startDate)) {
			cal1.setTime(endDate);
			cal2.setTime(startDate);
		}

		cal1.clear(Calendar.MILLISECOND);
		cal1.clear(Calendar.SECOND);
		cal1.clear(Calendar.MINUTE);
		cal1.clear(Calendar.HOUR_OF_DAY);

		cal2.clear(Calendar.MILLISECOND);
		cal2.clear(Calendar.SECOND);
		cal2.clear(Calendar.MINUTE);
		cal2.clear(Calendar.HOUR_OF_DAY);

		return getMonthsBetweenDate(cal1, cal2, flag);

	}

	public static long getMonthsBetweenDate(Calendar cal1, Calendar cal2,
			boolean flag) {
		long month = 0L;
		while (cal1.before(cal2)) {
			cal1.add(Calendar.MONTH, 1);
			month++;
			if (flag) {
				if ((cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH))
						&& (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))
						&& (cal1.get(Calendar.DAY_OF_MONTH) > cal2
								.get(Calendar.DAY_OF_MONTH))) {
					month--;
					break;
				}
				if ((cal1.get(Calendar.MONTH) > cal2.get(Calendar.MONTH))
						&& (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR))

				) {
					month--;
					break;
				}
			}
		}
		return month;
	}

	public static Date getMonthBeginDate(String yyyyMM) {
		return DateUtil.getDate(yyyyMM + "01", "yyyyMMdd");
	}

	public static Date getMonthEndDate(String yyyyMM) {
		Date startDate = getMonthBeginDate(yyyyMM);
		Calendar cal = Calendar.getInstance();
		cal.setTime(startDate);
		cal.add(Calendar.MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);

		return cal.getTime();
	}

	public static String getCycle(String yyyyMM, int value) {
		Date date = getMonthBeginDate(yyyyMM);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, value);
		return DateUtil.formatDate(cal.getTime(), "yyyyMM");
	}

	/**
	 * 时间格式化 格式为“yyyyMM”
	 * 
	 * @return String 格式化过的字符串
	 */
	public static String getCycle() {
		String today = new SimpleDateFormat("yyyyMMdd").format(new Date());
		today = today.substring(0, 6);
		return today;
	}

	public static Date getEndDateofCycleMonthByDate(Date begin, Date dd) {
		Date end = null;
		if (dd.before(begin))
			return null;
		boolean startmonthend = false;
		Calendar begincalendar = Calendar.getInstance();
		begincalendar.setTime(begin);
		int day = begincalendar.get(Calendar.DAY_OF_MONTH);
		if (day == begincalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
			startmonthend = true;
		}
		Calendar endcalendar = Calendar.getInstance();
		endcalendar.setTime(dd);
		int curday = endcalendar.get(Calendar.DAY_OF_MONTH);
		int max = endcalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if (startmonthend) {
			endcalendar.set(Calendar.DAY_OF_MONTH, max);
		} else {
			if (curday > day) {
				endcalendar.add(Calendar.MONTH, 1);
			}
			if (curday == day
					&& begincalendar.get(Calendar.YEAR) == endcalendar
							.get(Calendar.YEAR)
					&& begincalendar.get(Calendar.MONTH) == endcalendar
							.get(Calendar.MONTH)) {
				endcalendar.add(Calendar.MONTH, 1);
			}
			endcalendar.set(Calendar.DAY_OF_MONTH, day > max ? max : day);
		}
		endcalendar.set(Calendar.HOUR_OF_DAY,
				begincalendar.get(Calendar.HOUR_OF_DAY));
		endcalendar.set(Calendar.MINUTE, begincalendar.get(Calendar.MINUTE));
		endcalendar.set(Calendar.SECOND, begincalendar.get(Calendar.SECOND));

		end = endcalendar.getTime();
		return end;
	}

	/**
	 * 判断某个时间是不是一个月份的开始
	 * 
	 * @param dd
	 *            时间对象
	 * @return 是返回true ，不是返回false
	 */
	public static boolean beginTimeofMonth(Date dd) {
		if (null == dd)
			return false;
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dd);

		if (1 != calendar.get(Calendar.DAY_OF_MONTH))
			return false;
		if (0 != calendar.get(Calendar.HOUR_OF_DAY))
			return false;
		if (0 != calendar.get(Calendar.MINUTE))
			return false;
		if (0 != calendar.get(Calendar.SECOND))
			return false;
		return true;
	}

	/**
	 * 去掉指定时间域中的时间
	 * 
	 * @param date
	 *            时间对象
	 * @param field
	 *            时间域
	 * @return Date 时间对象
	 */
	public static Date truncDate(Date date, int field) {
		if (date == null)
			return null;
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		if (field == Calendar.YEAR) {
			cal.set(Calendar.MONTH, 0);
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else if (field == Calendar.MONTH) {
			cal.set(Calendar.DAY_OF_MONTH, 1);
		} else if (field == Calendar.DAY_OF_MONTH) {

		}
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		return cal.getTime();
	}

	/**
	 * 从开始的时间开始 计算今天是第几周
	 * 
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static int getWeek() {
		String startDate = "";
		if (new Date().getMonth() > 7) {
			startDate = (new Date().getYear() + 1900) + "0901";
		} else {
			startDate = (new Date().getYear() + 1900) + "0228";
		}

		Date d = DateUtil.getDate(startDate, "yyyyMMdd");

		Long ll = new Date().getTime() - d.getTime();

		int ss = 24 * 60 * 60 * 1000;
		Long u = ll / (new Long(ss));

		int week = (u.intValue() % 7) == 0 ? u.intValue() / 7
				: u.intValue() / 7 + 1;

		return week;
	}

	/**
	 * 获得两个时间的差值，返回的单位以秒计算(endDate大返回正的秒数,否则返回-1)
	 * 
	 * @author lte
	 */
	public static long getDifference(Date startdate, Date enddate) {
		long time = 0;
		try {
			Calendar c1 = Calendar.getInstance();
			c1.setTime(startdate);
			Calendar c2 = Calendar.getInstance();
			c2.setTime(enddate);
			long e = c2.getTimeInMillis();
			long s = c1.getTimeInMillis();
			time = ((e - s) / 1000 < 0) ? -1 : (e - s) / 1000;
		} catch (Exception e) {
			return -2;
		}
		return time;
	}

	/**
	 * 获取一月的开始时间
	 * 
	 * @author lte
	 * @param date
	 * @return
	 */
	public static Date getMonthBegin(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1, 0, 0, 0);
		return cal.getTime();
	}

	/**
	 * 获取一月的结束时间
	 * 
	 * @author lte
	 * @param date
	 * @return
	 */
	public static Date getMonthEnd(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, 1, 0, 0, 0);
		cal.set(Calendar.SECOND, cal.get(Calendar.SECOND) - 1);
		return cal.getTime();
	}

	/**
	 * 获取指定日期所在星期的开始时间（周一早上零时）
	 * 
	 * @param date
	 * @return 格式：2010-09-31 00:00:00
	 */
	public static String getWeekBegin(Date date) {
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
		return longDate(cal.getTime());
	}

	/**
	 * 获取指定日所在星期的结束时间（周日晚上零时）
	 * 
	 * @param date
	 * @return 格式：2010-09-31 23:59:59
	 */
	public static String getWeekEnd(Date date) {
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
		return longDate(cal.getTime());
	}

	/**
	 * 将日期截取为 年-月-日
	 * 
	 * @param date
	 * @return
	 */
	public static Date getNowDateTrunc(Date date) {
		if (date == null)
			return null;
		formatter = new SimpleDateFormat("yyyy-MM-dd");
		String strDate = formatter.format(date);
		Date nowDate = null;
		try {
			nowDate = formatter.parse(strDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return nowDate;
	}

	/***
	 * 获得从今天开始前30天的时间
	 * 
	 * @return
	 */
	public static String getBeforeMonth(Date date) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_MONTH, -30);
		Date datet = c.getTime();
		String d = sp.format(datet) + " 00:00:00";
		return d;
	}
	
	
	/***
	 * 获取前几天的日期
	 * 
	 * @return
	 */
	public static String getBeforeDays(Date date,int days) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, -days);
		Date datet = c.getTime();
		String d = sp.format(datet) + " 00:00:00";
		return d;
	}
	
	/***
	 * 获取前几天的日期
	 * 
	 * @return
	 */
	public static String getBeforeDays2(Date date,int days) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, -days);
		Date datet = c.getTime();
		String d = sp.format(datet);
		return d;
	}
	
	
	/***
	 * 获取前几天的日期
	 * 
	 * @return
	 */
	public static String getBeforeDays3(Date date,int days) {
		SimpleDateFormat sp = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.add(Calendar.DAY_OF_YEAR, -days);
		Date datet = c.getTime();
		String d = sp.format(datet);
		return d;
	}
	
	

	/**
	 * 返回时间格式后的的字符串 格式为：“HH:mm:ss”
	 * 
	 * @param aDate
	 *            要格式化的时间
	 * @return String 格式化后的字符串
	 */
	public static String returnHoure(Date aDate) {
		if (aDate == null)
			return "";
		formatter = new SimpleDateFormat("HH:mm:ss");
		return formatter.format(aDate);
	}

	/**
	 * 计算时间差 by wsh
	 * 
	 * @param 当前时间和某个时间
	 *            （当前时间与某个时间的时间差）
	 * 
	 * @return 天数
	 */
	public long timeDifference(Date nowDate, Date date) throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = df.parse(df.format(nowDate));
		Date dateTime = df.parse(df.format(date));
		long l = now.getTime() - dateTime.getTime();
		long day = l / (24 * 60 * 60 * 1000);

		return day;
	}

	/**
	 * 获取某段时间内的所有月份的集合 by wsh
	 * 
	 * @param start
	 * @param end
	 * @return
	 * @throws ParseException
	 */
	public List getMonthList(Date start, Date end) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM");
		String startDate = formatter.format(start);
		String startDate1 = formatter1.format(start);
		String endDate = formatter.format(end);
		DateFormat aa = DateFormat.getDateInstance();
		Date date1 = aa.parse(startDate); // 开始日期
		Date date2 = aa.parse(endDate); // 结束日期
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		List list = new ArrayList();
		list.add(startDate1);
		c1.setTime(date1);
		c2.setTime(date2);
		if (formatter1.format(c1.getTime()).equals(
				formatter1.format(c2.getTime()))) {
		} else {
			while (c1.compareTo(c2) < 0) {
				c1.add(Calendar.MONTH, 1);// 开始日期加一个月直到等于结束日期为止
				Date ss = c1.getTime();
				String str = aa.format(ss);
				str = str.substring(0, str.lastIndexOf("-"));
				if (formatter1.format(c1.getTime()).equals(
						formatter1.format(c2.getTime()))) {
					list.add(str);
					break;
				} else {
				list.add(str);
				}
			}
		}
		return list;
	}

	/**
	 * 此方法返回结果是1(2011年第1周)。如果加上一句calendar.setMinimalDaysInFirstWeek(7);返回结果是52（
	 * 2010年第52周）
	 * 
	 * @return
	 */
	public static int getWeekNumber() {
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.setTime(new Date());
		return calendar.get(Calendar.WEEK_OF_YEAR);

	}

	/**
	 * 取得指定日期所在周的第一天
	 */
	public static Date getFirstDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek()); // Monday
		return c.getTime();
	}

	/**
	 * 取得指定日期所在周的最后一天
	 */
	public Date getLastDayOfWeek(Date date) {
		Calendar c = new GregorianCalendar();
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6); // Sunday
		return c.getTime();
	}

	/**
	 * 然后我根据获取的周次和当前时间所在周的最后一天判断年份（year的值是当前年份）
	 */
	// if(weekNumber==1 && Integer.parseInt(endDate.substring(0, 4))==(year+1)){
	// year=year+1;
	// }
	
	//随机生成日期
    @SuppressWarnings("unused")
    public static String randomDate(String beginDate, String endDate) {  
        try {  
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");  
            Date start = format.parse(beginDate);// 构造开始日期  
            Date end = format.parse(endDate);// 构造结束日期  
            // getTime()表示返回自 1970 年 1 月 1 日 00:00:00 GMT 以来此 Date 对象表示的毫秒数。  
            if (start.getTime() >= end.getTime()) {  
                return null;  
            }  
            long date = random(start.getTime(), end.getTime());  
            String datestring=format.format(new Date(date));
            
            return datestring;  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;  
    }  
    
    //把日期转为 getTime() long型毫秒数来计算随机数 最后new Date(longdate)算出 日期Date
    public static long random(long begin, long end) {  
        long rtn = begin + (long) (Math.random() * (end - begin));  
        // 如果返回的是开始时间和结束时间，则递归调用本函数查找随机值  
        if (rtn == begin || rtn == end) {  
            return random(begin, end);  
        }  
        return rtn;  
    } 
    
    /**
     * 计算时间差
     * @param startTime yyyy-MM-dd HH:mm:ss
     * @param startTime yyyy-MM-dd HH:mm:ss
     * @return 秒
     */
    public static long getTimeDifference(String startTime, String endTime) throws Exception{
    	if(!checkValues(startTime, endTime)) return 0;
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date startDate = sdf.parse(startTime);
    	Date endDate = sdf.parse(endTime);
    	return (endDate.getTime() - startDate.getTime()) / 1000;
    }
    
    public static boolean checkValues(String... values) {
    	if(values == null) return false;
    	for(String value : values) {
    		if(value == null || "".equals(value)) return false;
    	}
    	return true;
    }
    
    
    
}
