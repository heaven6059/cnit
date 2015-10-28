/**
 * 文 件 名   :  DateUtils.java
 * 版    权    :  Ltd. Copyright (c) 2014 ,All rights reserved
 * 描    述    :  
 * 创建人     :  <a href="liming@cnit.com">李明</a>
 * 创建时间  :  2015-2-9 下午3:56:30
 * 修改跟踪表
 * 修改单号           修改人           修改时间          是否测试通过          修改描述
 * ——————————————————————————————————
 *                      
 */
package com.cnit.yoyo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @description 日期时间工具类
 * @detail <功能详细描述>
 * @author <a href="liming@cnit.com">李明</a>
 * @version 1.0.0
 */
public class DateUtils {
	public static final String NORMAL_DATE_PATTERN = "yyyy-MM-dd";

	public static final String ZHCN_DATE_PATTERN = "yyyy年MM月dd日";

	public static final String ZHCN_DATE_TIME_PATTERN = "yyyy年MM月dd日 HH:mm:ss";

	public static final String NORMAL_DATETIME_PATTERN = "yyyyMMddHHmmss";

	public static final String NORMALSS_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 格式化日期
	 * 
	 * @param strDate
	 *            符合格式的字符串
	 * @return 格式后的日期（yyyy-MM-dd HH:mm:ss）
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
	 * 格式化日期
	 * 
	 * @param strDate
	 *            符合格式的字符串
	 * @return 格式后的日期（yyyy-MM-dd HH:mm:ss）
	 */
	public static Date parser(String strDate, String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		try {
			return sdf.parse(strDate);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @description <b>获取日期之间相差月份</b>
	 * @author 王鹏
	 * @version 1.0.0
	 * @data 2015-6-25
	 * @param @param
	 *            date
	 * @param @return
	 * @return int
	 */
	public static int monthSpace(Date date) {
		try {
			int result, year = 0;
			Calendar c1 = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c1.setTime(date);
			c2.setTime(new Date());
			year = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
			result = c2.get(Calendar.MONDAY) - c1.get(Calendar.MONTH);
			return year * 12 + result;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	/**
	 * 
	 * @Title: parserStr2Str @Description: 把字符串“yyyy-MM-dd HH:mm:ss”
	 *         转化为字符串“yyyyMMddHHmmss” @param @param str @param @return
	 *         设定文件 @return String 返回类型 @throws
	 */
	public static String parserStr2Str(String str) {
		try {

			return DateUtils.dateToString(DateUtils.parser(str), DateUtils.NORMAL_DATETIME_PATTERN);

		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @Description:判断<firstDate>时间点是否在<secondDate>时间点之前 如果此 firstDate 的时间在参数
	 *                                                   <secondDate>表示的时间之前，
	 *                                                   则返回小于 0 的值
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @return
	 * @ReturnType boolean
	 * @author:
	 * @Created 2012 2012-9-20上午08:40:33
	 */
	public static boolean isBefore(Date firstDate, Date secondDate) {
		return compare(firstDate, secondDate) < 0 ? true : false;
	}

	/**
	 * @Description:判断<firstDate>时间点是否在<secondDate>时间点之后 如果此 firstDate 的时间在参数
	 *                                                   <secondDate>表示的时间之后，
	 *                                                   则返回大于 0 的值
	 * 
	 * @param firstDate
	 * @param secondDate
	 * @ReturnType boolean
	 * @author:
	 * @Created 2012 2012-9-20上午08:38:48
	 */
	public static boolean isAfter(Date firstDate, Date secondDate) {

		return compare(firstDate, secondDate) > 0 ? true : false;
	}

	/**
	 * @Description:比较两个时间点是否相等
	 * @param firstDate
	 * @param secondDate
	 * @ReturnType boolean
	 * @author:
	 * @Created 2012 2012-9-20上午08:37:30
	 */
	public static boolean isEqual(Date firstDate, Date secondDate) {

		return compare(firstDate, secondDate) == 0 ? true : false;
	}

	/**
	 * @Description:比较两个时间点大小</br>
	 *		firstDate等于secondDate返回 0</br> 
	 *		firstDate小于secondDate返回小于 0 的值</br>
	 *		firstDate大于secondDate返回大于 0 的值</br>
	 * @param firstDate
	 * @param secondDate
	 * @ReturnType int
	 * @author:
	 * @Created 2012 2012-9-20上午08:34:33
	 */
	public static int compare(Date firstDate, Date secondDate) {

		Calendar firstCalendar = null;
		/** 使用给定的 Date 设置此 Calendar 的时间。 **/
		if (firstDate != null) {
			firstCalendar = Calendar.getInstance();
			firstCalendar.setTime(firstDate);
		}

		Calendar secondCalendar = null;
		/** 使用给定的 Date 设置此 Calendar 的时间。 **/
		if (firstDate != null) {
			secondCalendar = Calendar.getInstance();
			secondCalendar.setTime(secondDate);
		}

		try {
			/**
			 * 比较两个 Calendar 对象表示的时间值（从历元至现在的毫秒偏移量）。 如果参数表示的时间等于此 Calendar
			 * 表示的时间，则返回 0 值； 如果此 Calendar 的时间在参数表示的时间之前，则返回小于 0 的值； 如果此
			 * Calendar 的时间在参数表示的时间之后，则返回大于 0 的值
			 **/
			return firstCalendar.compareTo(secondCalendar);
		} catch (NullPointerException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException(e);
		}
	}

	/**
	 * @Description:比较两个时间点 如果secondDate表示的时间等于此 firstDate 表示的时间，则返回 0 值； 如果此
	 *                      firstDate 的时间在参数<secondDate>表示的时间之前，则返回小于 0 的值； 如果此
	 *                      firstDate 的时间在参数<secondDate>表示的时间之后，则返回大于 0 的值
	 * @param firstDate
	 * @param secondDate
	 * @ReturnType int
	 * @author:
	 * @Created 2012 2012-9-20上午08:34:33
	 */
	public static int compare(String firstDate, String secondDate) {
		Date first = getDate(firstDate, DateUtils.NORMAL_DATETIME_PATTERN);
		Date second = getDate(secondDate, DateUtils.NORMAL_DATETIME_PATTERN);
		return compare(first, second);
	}

	/**
	 * @Description:比较两个时间点 如果当前表示的时间等于此 date 表示的时间，则返回 0 值； 如果此 firstDate
	 *                      的时间在参数现在表示的时间之前，则返回小于 0 的值； 如果此 firstDate
	 *                      的时间在参数现在表示的时间之后，则返回大于 0 的值
	 * @param firstDate
	 * @param secondDate
	 * @ReturnType int
	 * @author:
	 * @Created 2012 2012-9-20上午08:34:33
	 */
	public static int compareNow(String date) {
		Date first = getDate(date, DateUtils.NORMAL_DATETIME_PATTERN);
		Date second = getCurrentlyDate();
		return compare(first, second);
	}

	/**
	 * 将Date型转换为String型
	 * 
	 * @param dtDate
	 *            Date 要转换的时间
	 * @param strPattern
	 *            String 转换的格式
	 * @return String 转换后的时间 说明：格式可以为 yyyy-MM-dd HH:mm:ss等，可任意组合，如 yyyy年MM月dd日
	 *         yyyy代表年 MM代表月 dd代表日 HH hh kk代表小时 mm代表分钟 ss代表秒钟 format
	 */
	public static String dateToString(Date dtDate, String strPattern) {
		SimpleDateFormat lsdfDate = new SimpleDateFormat(strPattern);
		return lsdfDate.format(dtDate);
	}

	/**
	 * @Description:将符合相应格式的字符串转化为日期 <格式自定义>
	 * @param dateStr
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @ReturnType Date 日期字串为空或者不符合日期格式时返回null
	 * @author:
	 * @Created 2012 2012-9-20上午09:06:00
	 */
	public static Date getDate(String dateStr, String pattern) {

		return getDate(dateStr, pattern, null);
	}

	/**
	 * 将符合相应格式的字符串转化为日期 格式自定义
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param pattern
	 *            日期格式
	 * @param defaultDate
	 *            默认日期
	 * @return 日期字串为空或者不符合日期格式时返回defaultDate
	 */
	public static Date getDate(String dateStr, String pattern, Date defaultDate) {

		if (dateStr != null && pattern != null) {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			try {
				return sdf.parse(dateStr);
			} catch (ParseException e) {

			}
		}
		return defaultDate;
	}

	/**
	 * @Description:将某种日期转换成指定格式的日期数据 <获取相应格式的Date>
	 * @param date
	 *            需要格式的日期参数
	 * @param pattern
	 *            日期格式
	 * @ReturnType Date
	 * @author:
	 * @Created 2012 2012-9-20上午09:08:24
	 */
	public static Date getFormatDate(Date date, String pattern) {

		if (date == null) {
			throw new IllegalArgumentException("date can not be null!");
		}

		if (pattern == null) {
			pattern = NORMAL_DATETIME_PATTERN;
		}

		SimpleDateFormat sdf = new SimpleDateFormat(pattern);

		String dateStr = sdf.format(date);
		try {
			return sdf.parse(dateStr);
		} catch (ParseException e) {

		}
		return date;
	}

	/**
	 * 获取前/后半年的开始时间
	 * 
	 * @return
	 */
	public static Date getHalfYearStartTime() {
		Calendar c = Calendar.getInstance();
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 0);
			} else if (currentMonth >= 7 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 6);
			}
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获取前/后半年的结束时间
	 * 
	 * @return
	 */
	public static Date getHalfYearEndTime() {
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c = Calendar.getInstance();
		int currentMonth = c.get(Calendar.MONTH) + 1;
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 6) {
				c.set(Calendar.MONTH, 5);
				c.set(Calendar.DATE, 30);
			} else if (currentMonth >= 7 && currentMonth <= 12) {
				c.set(Calendar.MONTH, 11);
				c.set(Calendar.DATE, 31);
			}
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 23:59:59");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * @Description:将符合相应格式的字符串转化为日期 格式 yyyy-MM-dd
	 * @param dateStr
	 * @return 日期字串为空或者不符合日期格式时返回null
	 * @ReturnType Date
	 * @author:
	 * @Created 2012 2012-9-20上午09:04:28
	 */
	public static Date getDate(String dateStr) {
		return getDate(dateStr, DateUtils.NORMAL_DATE_PATTERN);
	}

	/**
	 * @Description:将当前日期转换成字符串 格式 yyyy-MM-dd
	 * @ReturnType String
	 * @author:
	 * @Created 2012 2012-9-19下午05:54:42
	 */
	public static String datetoStr() {
		SimpleDateFormat sdf = new SimpleDateFormat(NORMAL_DATE_PATTERN);
		String curDate = sdf.format(new Date());
		return curDate;
	}

	/**
	 * @Description: 获取当天日期,日期格式为YYYYMMDD
	 * @ReturnType String
	 * @author:
	 * @Created 2012 2012-9-20上午09:17:19
	 */
	public static String getIntraday() {
		Calendar date = Calendar.getInstance();// 获得当前日期
		int year = date.get(Calendar.YEAR);
		int month = date.get(Calendar.MONTH) + 1;
		int day = date.get(Calendar.DAY_OF_MONTH);
		String updateFileDate = new String(new Integer(year).toString() + new Integer(month).toString() + new Integer(day).toString());
		return updateFileDate;
	}

	/**
	 * @Description:获取当天日期，日期格式为YYYY-MM-DD HH:mm:ss
	 * @return
	 * @ReturnType Date
	 * @author:
	 * @Created 2012 2012-9-20上午09:58:36
	 */
	public static Date getCurrentlyDate() {
		Date currentDate = new Date();
		return getFormatDate(currentDate, "yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * @Description:获取当天日期，日期格式为yyyyMMddHHmmss
	 * @return
	 * @ReturnType String
	 * @author:
	 * @Created 2012 2012-9-20上午09:58:36
	 */
	public static String getCurrentDate(String pattern) {
		if (pattern == null || pattern.trim() == "") {
			pattern = DateUtils.NORMAL_DATETIME_PATTERN;
		}
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(currentDate);
	}

	/**
	 * @Description: 获取时间的小时数（24小时制）
	 * @param date
	 * @return
	 * @ReturnType int
	 * @author:
	 * @Created 2012 2012-9-25下午08:33:44
	 */
	public static int getTime24Hour() {

		Calendar calender = Calendar.getInstance();
		calender.setTime(getCurrentlyDate());
		return calender.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * @Description:获取前一日
	 * @param date
	 * @return
	 * @ReturnType Date
	 * @author:
	 * @Created 2012 2012-9-25下午08:32:00
	 */
	public static Date getBeforeDate(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(calender.DATE, -1);
		return calender.getTime();
	}

	/**
	 * @Description:取得当前日期的下一日
	 * @ReturnType String
	 * @author:
	 * @Created 2012 2012-9-20上午09:12:06
	 */
	public static String getTomorrowDate() {
		Date myDate = new Date();
		Calendar calender = Calendar.getInstance();
		calender.setTime(myDate);
		calender.add(calender.DATE, 1);
		return dateToString(calender.getTime(), "yyyy-MM-dd");
	}

	/**
	 * @Description:取当前日期后的第二日
	 * @return
	 * @ReturnType String
	 * @author:
	 * @Created 2012 2012-9-20上午10:37:47
	 */
	public static String getNextTomorrowDate() {

		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");

		Date myDate = new Date();

		long myTime = (myDate.getTime() / 1000) - 60 * 60 * 24 * 365;

		myDate.setTime(myTime * 1000);

		String mDate = formatter.format(myDate);

		myTime = (myDate.getTime() / 1000) + 60 * 60 * 24;

		myDate.setTime(myTime * 1000);

		mDate = formatter.format(myDate);

		return mDate;
	}

	/**
	 * @Description:获取当前星期在当前月份中的第几个星期
	 * @param date
	 * @return
	 * @ReturnType int
	 * @author:
	 * @Created 2012 2012-10-29上午11:45:13
	 */
	public static int getTimeWeekOfMonth(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		return calender.get(Calendar.WEEK_OF_MONTH);
	}

	/**
	 * 功能描述：获取时间在当前星期的第几天
	 * 
	 * @author
	 * 		<p>
	 *         创建日期 ：2012 2012-10-29上午11:45:52
	 *         </p>
	 * @param date
	 * @return 返回星期数,其中: 1表示星期一, ...7表示星期天
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public static int getTimeDateOfWeek(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		int week = calender.get(Calendar.DAY_OF_WEEK) - 1;
		if (week == 0)
			week = 7;
		return week;
	}

	/**
	 * 功能描述：获取时间在当前星期的第几天
	 * 
	 * @author
	 * 		<p>
	 *         创建日期 ：2012 2012-10-29上午11:45:52
	 *         </p>
	 * @param date
	 * @return 返回星期数,其中: 7表示星期六..1表示星期天
	 *         <p>
	 *         修改历史 ：(修改人，修改时间，修改原因/内容)
	 *         </p>
	 */
	public static int getQuartzTimeDateOfWeek(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		int week = calender.get(Calendar.DAY_OF_WEEK);
		return week;
	}

	/**
	 * @Description:某个时间与当前时间进行求差
	 * @param date
	 * @return
	 * @ReturnType long
	 * @author:
	 * @Created 2012 2012-12-12下午01:00:53
	 */
	public static long getAppointTimeDifference(Date startDate, Date endDate) {
		long l = endDate.getTime() - startDate.getTime();
		long day = l / (24 * 60 * 60 * 1000);
		return day;
	}

	/**
	 * @Description:某个时间与当前时间进行求差
	 * @param date
	 * @return
	 * @ReturnType long
	 * @author:
	 * @Created 2012 2012-12-12下午01:00:53
	 */
	public static long getTimeDifference(Date date) {
		/** 获取当前系统时间 **/
		java.util.Date currentlyDate = DateUtils.getCurrentlyDate();

		long l = date.getTime() - currentlyDate.getTime();

		long day = l / (24 * 60 * 60 * 1000);

		long hour = (l / (60 * 60 * 1000) - day * 24);

		long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);

		long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);

		System.out.println("" + day + "天" + hour + "小时" + min + "分" + s + "秒");
		return day;
	}

	/**
	 * @Description:获取时间的分数
	 * @param date
	 * @ReturnType int
	 * @author:
	 * @Created 2012 2012-10-29上午11:45:13
	 */
	public static int getTimeMinute(Date date) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		return calender.get(Calendar.MINUTE);
	}

	/**
	 * 给出时间分格式转换时间单位
	 * 
	 * @param time
	 *            String ; 12:20:30
	 * @return String ;12h20m30s
	 */
	public static String getTimeUnit(String time) {
		time = time.replaceFirst(":", "h");
		time = time.replaceFirst(":", "m") + "s";
		return time;
	}

	/**
	 * 根据当前日期及增加天数得到相应日期
	 * 
	 * @param s
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static String addDay(String s, int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cd = Calendar.getInstance();
		try {
			cd.setTime(sdf.parse(s));
			cd.add(Calendar.DATE, n);
		} catch (Exception e) {
		}
		return sdf.format(cd.getTime());
	}

	/**
	 * 根据当前日期及增加天数得到相应日期
	 * 
	 * @param s
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static String addDay(Date s, int n) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cd = Calendar.getInstance();
		try {
			cd.setTime(s);
			cd.add(Calendar.DATE, n);
		} catch (Exception e) {
		}
		return sdf.format(cd.getTime());
	}

	/**
	 * 根据当前日期及增加月数得到相应日期
	 * 
	 * @param s
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static Date addMonth(Date current, int month) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, month);
		return c.getTime();
	}

	/**
	 * 根据当前日期及增加年数得到相应日期
	 * 
	 * @param s
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static Date addYear(Date current, int year) {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, year);
		return c.getTime();
	}

	/**
	 * 根据当前日期及增加天数得到相应日期
	 * 
	 * @param s
	 * @param n
	 * @return
	 * @throws Exception
	 */
	public static String addDay(String date, int n, String pattern) {
		if (pattern == null || pattern.trim() == "") {
			pattern = DateUtils.NORMAL_DATETIME_PATTERN;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		Calendar cd = Calendar.getInstance();
		try {
			cd.setTime(sdf.parse(date));
			cd.add(Calendar.DATE, n);
		} catch (Exception e) {
		}
		return sdf.format(cd.getTime());
	}

	/**
	 * 功能：得到当前年份年初 格式为：xxxx-yy-zz (eg: 2015-01-01)<br>
	 * 
	 * @return String
	 */
	public static Date getCurYearBegin() {
		Calendar c = Calendar.getInstance();
		return getDate(c.get(Calendar.YEAR) + "-01" + "-01");
	}

	/**
	 * 功能：得到当前年份年底 格式为：xxxx-yy-zz (eg: 2015-12-31)<br>
	 * 
	 * @return String
	 */
	public static Date getCurYearEnd() {
		Calendar c = Calendar.getInstance();
		return getDate(c.get(Calendar.YEAR) + "-12" + "-31");
	}

	/**
	 * 根据原来的时间（Date）获得相对偏移 N 天的时间（Date）
	 * 
	 * @param protoDate
	 *            原来的时间（java.util.Date）
	 * 
	 * @param dateOffset
	 *            （向前移正数，向后移负数）
	 * 
	 * @return 时间（java.util.Date）
	 */
	public static Date getOffsetDayDate(Date protoDate, int dateOffset) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(protoDate);
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH) - dateOffset);
		return cal.getTime();
	}

}
