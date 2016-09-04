/**
 * 
 */
package com.tuniu.zhangliping.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

/**
 * @author zhangliping
 *
 */
public class DateUtil {

	  private static final String YYYY_MM_DD_HHMMSS = "yyyy-MM-dd HH:mm:ss";

	    private static final String YYYY_MM_DD = "yyyy-MM-dd";

	    private static final String HHMM = "HH:mm";

	    /**
	     * 1秒钟的毫秒数
	     */
	    public static final long MILLIS_PER_SECOND = 1000;

	    /**
	     * 1分钟的毫秒数
	     */
	    public static final long MILLIS_PER_MINUTE = 60 * MILLIS_PER_SECOND;

	    /**
	     * 1小时的毫秒数
	     */
	    public static final long MILLIS_PER_HOUR = 60 * MILLIS_PER_MINUTE;

	    /**
	     * 1天的毫秒数
	     */
	    public static final long MILLIS_PER_DAY = 24 * MILLIS_PER_HOUR;
	    
	    private static final Object lockObj = new Object();

	    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<String, ThreadLocal<SimpleDateFormat>>();

	    private static SimpleDateFormat getSdf(final String pattern) {
	        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

	        if (tl == null) {
	            synchronized (lockObj) {
	                tl = sdfMap.get(pattern);
	                if (tl == null) {
	                    tl = new ThreadLocal<SimpleDateFormat>() {
	                        @Override
	                        protected SimpleDateFormat initialValue() {
	                            return new SimpleDateFormat(pattern);
	                        }
	                    };
	                    sdfMap.put(pattern, tl);
	                }
	            }
	        }

	        return tl.get();
	    }


	    /**
	     * 获取当前月的起始时间，如：2012-09-01 00:00:00.000
	     * 
	     * @return 当前月的起始时间
	     */
	    public static Date getStartOfMonth() {
	        return getStartOfMonth(0);
	    }

	    /**
	     * 获取当前月距amount个月的起始时间，如：2012-09月前一个月（amount = -1）的起始日期：2012-08-01
	     * 00:00:00.000
	     * 
	     * @param amount 间隔月份
	     * @return 当前月之前amount个月的起始时间
	     */
	    public static Date getStartOfMonth(int amount) {
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.MONTH, amount);
	        cal.set(Calendar.HOUR_OF_DAY, cal.getMinimum(Calendar.HOUR_OF_DAY));
	        cal.set(Calendar.MINUTE, cal.getMinimum(Calendar.MINUTE));
	        cal.set(Calendar.SECOND, cal.getMinimum(Calendar.SECOND));
	        cal.set(Calendar.MILLISECOND, cal.getMinimum(Calendar.MILLISECOND));
	        cal.set(Calendar.DAY_OF_MONTH, 1);
	        return cal.getTime();
	    }

	    /**
	     * 获取当前月的结束时间，如：2012-09-30 23:59:59.999
	     * 
	     * @return 当前月的结束时间
	     */
	    public static Date getEndOfMonth() {
	        return getEndOfMonth(0);
	    }

	    /**
	     * 获取距当前月amount个月的结束时间，如：2012-09月前一个月（amount = 1）的起始日期：2012-08-31
	     * 23:59:59.999
	     * 
	     * @param amount 间隔月份
	     * @return 距当前月amount个月的结束时间
	     */
	    public static Date getEndOfMonth(int amount) {
	        Calendar cal = Calendar.getInstance();
	        cal.add(Calendar.MONTH, amount + 1);
	        cal.set(Calendar.HOUR_OF_DAY, cal.getMaximum(Calendar.HOUR_OF_DAY));
	        cal.set(Calendar.MINUTE, cal.getMaximum(Calendar.MINUTE));
	        cal.set(Calendar.SECOND, cal.getMaximum(Calendar.SECOND));
	        cal.set(Calendar.MILLISECOND, cal.getMaximum(Calendar.MILLISECOND));
	        cal.set(Calendar.DAY_OF_MONTH, 1);
	        cal.add(Calendar.DAY_OF_MONTH, -1);
	        return cal.getTime();
	    }

	    /**
	     * 时间字符串转化为日期类型，格式为yyyy-MM-dd HH:mm:ss
	     * 
	     * @param dateTime 时间字符串，格式为yyyy-MM-dd HH:mm:ss
	     * @return Date
	     */
	    public static Date parseDateTime(String dateTime) {
	        try {
	            return getSdf(YYYY_MM_DD_HHMMSS).parse(dateTime);
	        } catch (ParseException e) {
	            return null;
	        }
	    }

	    /**
	     * 时间字符串转化为日期类型，格式为yyyy-MM-dd
	     * 
	     * @param dateTime 时间字符串，格式为yyyy-MM-dd
	     * @return Date
	     */
	    public static Date parseDate(String date) {
	        try {
	            return getSdf(YYYY_MM_DD).parse(date);
	        } catch (ParseException e) {
	            return null;
	        }
	    }

	    /**
	     * 时间字符串转化为日期类型，格式为HH:mm
	     * 
	     * @param dateTime 时间字符串，格式为HH:mm
	     * @return Date
	     */
	    public static Date parseTime(String date) {
	        try {
	            return getSdf(HHMM).parse(date);
	        } catch (ParseException e) {
	            return null;
	        }
	    }

	    /**
	     * 格式化日期类型为字符串，格式为yyyy-MM-dd HH:mm:ss
	     * 
	     * @param dateTime 日期类型
	     * @return 时间字符串，格式为yyyy-MM-dd HH:mm:ss
	     */
	    public static String formatDateTime(Date dateTime) {
	        if (dateTime == null) {
	            return null;
	        }
	        return getSdf(YYYY_MM_DD_HHMMSS).format(dateTime);
	    }

	    /**
	     * 格式化日期类型为字符串，格式为yyyy-MM-dd
	     * 
	     * @param dateTime 日期类型
	     * @return 时间字符串，格式为yyyy-MM-dd
	     */
	    public static String formatDate(Date date) {
	        if (date == null) {
	            return null;
	        }
	        return getSdf(YYYY_MM_DD).format(date);
	    }

	    /**
	     * 
	     * @return 当前时间
	     */
	    public static Date getCurrentTime() {
	        return Calendar.getInstance().getTime();
	    }

	    /**
	     * 
	     * @param dayAmount
	     * @return
	     */
	    public static Date afterNowOfDay(int dayAmount) {
	        return addDays(Calendar.getInstance().getTime(), dayAmount);
	    }

	    /**
	     * 获取两个日期之间的天数
	     * 
	     * @param startDate
	     * @param endDate
	     * @return
	     */
	    public static Long getBetweenDays(Date startDate, Date endDate) {
	        if (startDate == null || endDate == null) {
	            return null;
	        }
	        Long day = (endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000);
	        return day;
	    }

	    // -----------------------------------------------------------------------
	    /**
	     * <p>
	     * Checks if two date objects are on the same day ignoring time.
	     * </p>
	     * 
	     * <p>
	     * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true. 28 Mar 2002
	     * 13:45 and 12 Mar 2002 13:45 would return false.
	     * </p>
	     * 
	     * @param date1 the first date, not altered, not null
	     * @param date2 the second date, not altered, not null
	     * @return true if they represent the same day
	     * @throws IllegalArgumentException if either date is <code>null</code>
	     * @since 2.1
	     */
	    public static boolean isSameDay(Date date1, Date date2) {
	        if (date1 == null || date2 == null) {
	            throw new IllegalArgumentException("The date must not be null");
	        }
	        Calendar cal1 = Calendar.getInstance();
	        cal1.setTime(date1);
	        Calendar cal2 = Calendar.getInstance();
	        cal2.setTime(date2);
	        return isSameDay(cal1, cal2);
	    }

	    /**
	     * <p>
	     * Checks if two calendar objects are on the same day ignoring time.
	     * </p>
	     * 
	     * <p>
	     * 28 Mar 2002 13:45 and 28 Mar 2002 06:01 would return true. 28 Mar 2002
	     * 13:45 and 12 Mar 2002 13:45 would return false.
	     * </p>
	     * 
	     * @param cal1 the first calendar, not altered, not null
	     * @param cal2 the second calendar, not altered, not null
	     * @return true if they represent the same day
	     * @throws IllegalArgumentException if either calendar is <code>null</code>
	     * @since 2.1
	     */
	    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
	        if (cal1 == null || cal2 == null) {
	            throw new IllegalArgumentException("The date must not be null");
	        }
	        return (cal1.get(Calendar.ERA) == cal2.get(Calendar.ERA) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1
	                .get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR));
	    }

	    /**
	     * Adds a number of months to a date returning a new object. The original
	     * date object is unchanged.
	     * 
	     * @param date the date, not null
	     * @param amount the amount to add, may be negative
	     * @return the new date object with the amount added
	     * @throws IllegalArgumentException if the date is null
	     */
	    public static Date addMonths(Date date, int amount) {
	        return add(date, Calendar.MONTH, amount);
	    }

	    /**
	     * Adds a number of days to a date returning a new object. The original date
	     * object is unchanged.
	     * 
	     * @param date the date, not null
	     * @param amount the amount to add, may be negative
	     * @return the new date object with the amount added
	     * @throws IllegalArgumentException if the date is null
	     */
	    public static Date addDays(Date date, int amount) {
	        return add(date, Calendar.DAY_OF_MONTH, amount);
	    }

	    /**
	     * Adds to a date returning a new object. The original date object is
	     * unchanged.
	     * 
	     * @param date the date, not null
	     * @param calendarField the calendar field to add to
	     * @param amount the amount to add, may be negative
	     * @return the new date object with the amount added
	     * @throws IllegalArgumentException if the date is null
	     */
	    public static Date add(Date date, int calendarField, int amount) {
	        if (date == null) {
	            throw new IllegalArgumentException("The date must not be null");
	        }
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.add(calendarField, amount);
	        return c.getTime();
	    }

	    public static String now() {
	        SimpleDateFormat sdf = getSdf("yyyyMMdd");
	        return sdf.format(Calendar.getInstance().getTime());
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 加减指定的日期的年份。
	     * @param date --指定的日期
	     * @param amount --数量可以为负数
	     * @return 计算后的结果
	     */
	    public static Date addYears(Date date, int amount) {
	        return add(date, Calendar.YEAR, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 加减指定的日期的小时数
	     * @param date --指定的日期
	     * @param amount --加减数量
	     * @return 计算后的结果
	     */
	    public static Date addHours(Date date, int amount) {
	        return add(date, Calendar.HOUR_OF_DAY, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 加减指定的日期的分钟数
	     * @param date --指定的日期
	     * @param amount --加减数量
	     * @return 计算后的结果
	     */
	    public static Date addMinutes(Date date, int amount) {
	        return add(date, Calendar.MINUTE, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 加减指定的日期的秒数
	     * @param date --指定的日期
	     * @param amount --加减数量
	     * @return 计算后的结果
	     */
	    public static Date addSeconds(Date date, int amount) {
	        return add(date, Calendar.SECOND, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 加减指定的日期的毫秒数
	     * @param date --指定的日期
	     * @param amount --加减数量
	     * @return 计算后的结果
	     */
	    public static Date addMilliseconds(Date date, int amount) {
	        return add(date, Calendar.MILLISECOND, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 设置指定日期重新的年份
	     * @param date --指定的日期
	     * @param amount --年份
	     * @return 设置后的日期
	     */
	    public static Date setYears(Date date, int amount) {
	        return set(date, Calendar.YEAR, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 设置指定日期的月份
	     * @param date --指定的日期
	     * @param amount --月份
	     * @return 设置后的日期
	     */
	    public static Date setMonths(Date date, int amount) {
	        return set(date, Calendar.MONTH, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 设置指定日期的天数
	     * @param date --指定的日期
	     * @param amount --天数
	     * @return 设置后的日期
	     */
	    public static Date setDays(Date date, int amount) {
	        return set(date, Calendar.DAY_OF_MONTH, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 设置指定日期的小时
	     * @param date --指定日期
	     * @param amount --小时数
	     * @return 设置后的日期
	     */
	    public static Date setHours(Date date, int amount) {
	        return set(date, Calendar.HOUR_OF_DAY, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 设置指定日期的分钟数
	     * @param date --指定日期
	     * @param amount --分钟数
	     * @return 设置后的日期
	     */
	    public static Date setMinutes(Date date, int amount) {
	        return set(date, Calendar.MINUTE, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 设置指定日期的秒
	     * @param date --指定日期
	     * @param amount --秒
	     * @return 设置后的日期
	     */
	    public static Date setSeconds(Date date, int amount) {
	        return set(date, Calendar.SECOND, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 设置指定日期的毫秒
	     * @param date --指定日期
	     * @param amount --毫秒
	     * @return 设置后的日期
	     */
	    public static Date setMilliSeconds(Date date, int amount) {
	        return set(date, Calendar.MILLISECOND, amount);
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 设置指定日期的各域
	     * @param date --指定日期
	     * @param calendarFiled --域包括年、月、日、时、分、秒、毫秒
	     * @param amount --数量
	     * @return 设置后日期
	     */
	    public static Date set(Date date, int calendarFiled, int amount) {
	        if (null == date) {
	            throw new IllegalArgumentException("The date must not be null");
	        } else {

	        }
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        c.set(calendarFiled, amount);
	        return c.getTime();
	    }

	    // ---------------------------------------------------------------------------------------------------
	    /**
	     * 把日期转换成日历
	     * @param date --日期格式
	     * @return 日历格式
	     */
	    public static Calendar toCalendar(Date date) {
	        Calendar c = Calendar.getInstance();
	        c.setTime(date);
	        return c;
	    }

	    // ----------------------------------------------------------------------------------------------------
	    /**
	     * 两个日期相加
	     * @param d1 --日期1
	     * @param d2 --日期2
	     * @return 相加后的日期
	     */
	    public static Date addTwo(Date d1, Date d2) {
	        if (null == d1 || null == d2) {
	            return null;
	        } else {

	        }
	        return new Date(d1.getTime() + d2.getTime());
	    }

	    // -----------------------------------------------------------------------------------------------------
	    /**
	     * 计算两个日期之间相差的年，不足一年的被舍去，d1和d2都不能为null
	     * @param d1 --日期1
	     * @param d2 --日期2
	     * @return d1和d2相差的年份
	     */
	    public static int minusToYear(Date d1, Date d2) {
	        if (null == d1 || null == d2) {
	            throw new java.lang.IllegalArgumentException("d1和d2都不能为null");
	        } else {

	        }
	        Calendar cl1 = Calendar.getInstance();
	        Calendar cl2 = Calendar.getInstance();
	        cl1.setTime(d1);
	        cl2.setTime(d2);
	        return cl1.get(Calendar.YEAR) - cl2.get(Calendar.YEAR);
	    }

	    // ----------------------------------------------------------------------------------------------------
	    /**
	     * 计算两个日期之间的月份，不足一月的被舍去，d1和d2都不能为null
	     * @param d1 --日期1
	     * @param d2 --日期2
	     * @return d1和d2之间相差月份
	     */
	    public static int minusToMonth(Date d1, Date d2) {

	        if (null == d1 || null == d2) {
	            throw new java.lang.IllegalArgumentException("d1和d2都不能为null");
	        } else {

	        }
	        Calendar cl1 = Calendar.getInstance();
	        Calendar cl2 = Calendar.getInstance();
	        cl1.setTime(d1);
	        cl2.setTime(d2);
	        return (cl1.get(Calendar.YEAR) - cl2.get(Calendar.YEAR)) * 12 + cl1.get(Calendar.MONTH)
	                - cl2.get(Calendar.MONTH);
	    }

	    // ----------------------------------------------------------------------------------------------------
	    /**
	     * 计算两个日期之间的天数，不足一天的被舍去，d1和d2都不能为null
	     * @param d1 --日期1
	     * @param d2 --日期2
	     * @return d1和d2之间相差的天数
	     */
	    public static int minusToDay(Date d1, Date d2) {

	        return (int) (minusToMilliSecond(d1, d2) / MILLIS_PER_DAY);
	    }

	    // ----------------------------------------------------------------------------------------------------
	    /**
	     * 计算两个日期之间的小时数，不足一小时的被舍去，d1和d2都不能为null
	     * @param d1 --日期1
	     * @param d2 --日期2
	     * @return d1和d2之间相差的小时数
	     */
	    public static int minusToHours(Date d1, Date d2) {

	        return (int) (minusToMilliSecond(d1, d2) / MILLIS_PER_HOUR);
	    }

	    // ----------------------------------------------------------------------------------------------------
	    /**
	     * 计算两个日期之间的分钟数不足一分钟的被舍去，d1和d2都不能为null
	     * @param d1 --日期1
	     * @param d2 --日期2
	     * @return d1和d2之间相差的分钟数
	     */
	    public static long minusToMinutes(Date d1, Date d2) {

	        return minusToMilliSecond(d1, d2) / MILLIS_PER_MINUTE;
	    }

	    // ----------------------------------------------------------------------------------------------------
	    /**
	     * 计算两个日期之间的秒数不足以秒的被舍去，d1和d2都不能为null
	     * @param d1 --日期1
	     * @param d2 --日期2
	     * @return d1和d2之间相差的秒数
	     */
	    public static long minusToSeconds(Date d1, Date d2) {

	        return minusToMilliSecond(d1, d2) / MILLIS_PER_SECOND;
	    }

	    // -----------------------------------------------------------------------------------------------------
	    /**
	     * 计算两个日期之间的毫秒数，d1和d2都不能为null
	     * @param d1 --日期1
	     * @param d2 --日期2
	     * @return d1和d2之间相差的毫秒数
	     */
	    public static long minusToMilliSecond(Date d1, Date d2) {
	        if (null == d1 || null == d2) {
	            throw new java.lang.IllegalArgumentException("d1和d2都不能为null");
	        } else {

	        }
	        return d1.getTime() - d2.getTime();
	    }

	    /**
	     * 将string转换为date 入参格式like：“Wed Mar 19 13:20:02 CST 2014”
	     * @param date
	     * @return
	     */
	    public static Date formatDate(String date) {
	        if (null == date) {
	            return null;
	        } else {
	            Long longDate = Date.parse(date);
	            Date newDate = new Date(longDate - (14 * MILLIS_PER_HOUR));
	            return newDate;
	        }
	    }

	    /**
	     * 获取下天日期
	     * 
	     * @param specifiedDay
	     * @return
	     * @throws ParseException
	     */
	    public static Date getNextDay(Date specifiedDay) throws ParseException {
	        Calendar c = Calendar.getInstance();
	        c.setTime(specifiedDay);
	        int day = c.get(Calendar.DATE);
	        c.set(Calendar.DATE, day + 1);
	        String dayAfter = getSdf(YYYY_MM_DD).format(c.getTime());
	        Date nextDay = getSdf(YYYY_MM_DD).parse(dayAfter);
	        return nextDay;
	    }

		/**
	     * 获取上天日期
	     * 
	     * @param specifiedDay
	     * @return
	     * @throws ParseException
	     */
	    public static Date getAfterDay(Date specifiedDay) throws ParseException {
	        Calendar c = Calendar.getInstance();
	        c.setTime(specifiedDay);
	        int day = c.get(Calendar.DATE);
	        c.set(Calendar.DATE, day - 1);
	        String dayAfter = getSdf(YYYY_MM_DD).format(c.getTime());
	        Date nextDay = getSdf(YYYY_MM_DD).parse(dayAfter);
	        return nextDay;
	    }

	    /**
	     * 获取当前时间的n年之后的日期
	     * @param 年数
	     * @return
	     * @throws ParseException
	     */
	    public static Date getNextNyear(Date nowTime, int n) throws ParseException {
	        Calendar c = Calendar.getInstance();
	        c.setTime(nowTime);
	        int year = c.get(Calendar.YEAR);
	        c.set(Calendar.YEAR, year + n);
	        String yearAfter = getSdf(YYYY_MM_DD).format(c.getTime());
	        Date nextNyear = getSdf(YYYY_MM_DD).parse(yearAfter);
	        return nextNyear;
	    }

	    /**
	     * 获取下天日期
	     * 
	     * @param specifiedDay
	     * @return
	     * @throws ParseException
	     */
	    public static Date getNextNDay(Date specifiedDay, int n) throws ParseException {
	        Calendar c = Calendar.getInstance();
	        c.setTime(specifiedDay);
	        int day = c.get(Calendar.DATE);
	        c.set(Calendar.DATE, day + n);
	        String dayAfter = getSdf(YYYY_MM_DD_HHMMSS).format(c.getTime());
	        Date nextDay = getSdf(YYYY_MM_DD_HHMMSS).parse(dayAfter);
	        return nextDay;
	    }

	    /**
	     * 获取指定当天的23:59:59
	     * @param date
	     * @return
	     * @throws ParseException
	     */
	    public static Date getEndTimeOfDay(Date date) throws ParseException {
	        String tmpDate = getSdf(YYYY_MM_DD).format(date);
	        tmpDate = tmpDate + " 23:59:59";
	        Date endDate = getSdf(YYYY_MM_DD_HHMMSS).parse(tmpDate);
	        return endDate;
	    }
	    
	    /**
	     * 获取指定当天的00:00:00
	     * @param date
	     * @return
	     * @throws ParseException
	     */
	    public static Date getStartTimeOfDay(Date date) throws ParseException {
	        String tmpDate = getSdf(YYYY_MM_DD).format(date);
	        tmpDate = tmpDate + " 00:00:00";
	        Date endDate = getSdf(YYYY_MM_DD_HHMMSS).parse(tmpDate);
	        return endDate;
	    }
	    
	    public static Date concatDateAndTime(Date dateInput, String timeInput) {
	        if (dateInput == null) {
	            return null;
	        }
	        String dateInStr = DateFormatUtils.format(dateInput, YYYY_MM_DD);
	        timeInput = StringUtils.deleteWhitespace(timeInput);
	        dateInStr = dateInStr.concat(" ");
	        dateInStr = dateInStr.concat(timeInput);
	        try {
	            return DateUtils.parseDate(dateInStr, new String[] { YYYY_MM_DD,"yyyy-MM-dd HH:mm",YYYY_MM_DD_HHMMSS });
	        } catch (ParseException e) {
	            e.printStackTrace();
	        }
	        return dateInput;
	    }
	    
	    /**
	     * 判断是否是周末
	     * @param date
	     * @return
	     */
	    public static boolean isWeekend(Date date){
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(date);
	    	if(cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY||cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
	    	{
	    		return true;
	    	}else{
	    		return false;
	    	}
	    }
	    
	    /**
	     * 判断是否是工作时间，早8点至晚6点视为工作时间
	     * @param date
	     * @return
	     */
	    public static boolean isWorkTime(Date date) throws ParseException{
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(date);
	    	String tmpDate = getSdf(YYYY_MM_DD).format(date);
	    	Calendar calBegin = Calendar.getInstance();
	    	Calendar calEnd = Calendar.getInstance();
	    	calBegin.setTime(getSdf(YYYY_MM_DD_HHMMSS).parse(tmpDate+" 08:00:00"));
	    	calEnd.setTime(getSdf(YYYY_MM_DD_HHMMSS).parse(tmpDate+" 18:00:00"));
	    	if(cal.before(calEnd) && cal.after(calBegin)){
	    		return true;
	    	}else{
	    		return false;
	    	} 
	    }
	    
	    /**
	     * 获取昨天日期
	     * 
	     * @param specifiedDay
	     * @return
	     * @throws ParseException
	     */
	    public static Date getPrevDay(Date specifiedDay) throws ParseException {
	        Calendar c = Calendar.getInstance();
	        c.setTime(specifiedDay);
	        int day = c.get(Calendar.DATE);
	        c.set(Calendar.DATE, day - 1);
	        String dayAfter = getSdf(YYYY_MM_DD).format(c.getTime());
	        Date prevDay = getSdf(YYYY_MM_DD).parse(dayAfter);
	        return prevDay;
	    }
	    
	    /**
	     * 将string 格式类型的时间转换时间格式
	     * @param paramdate
	     * @return
	     * @throws Exception
	     */
	    public static Date getDateFromString(String paramdate) throws Exception{
	    	SimpleDateFormat sdf = getSdf(YYYY_MM_DD);
	    	if(StringUtils.isNotEmpty(paramdate)){
	    		Date retDate = sdf.parse(paramdate);
	        	return retDate;
	    	}else{
	    		return null;
	    	}
	    }
	    
	    /**
	     * 传递日期和时间
	     * @param paramDate
	     * @param paramTime
	     * @return
	     * @throws Exception
	     */
	    public static Date getDateFromString(String paramDate,String paramTime) throws Exception{
	    	StringBuffer tempTime = new StringBuffer();
	    	if(paramTime.length()<8){
	    		tempTime = new StringBuffer(paramTime).append(":00");
	    	}else{
	    		tempTime = new StringBuffer(paramTime);
	    	}
	    	String dateTime = new StringBuffer(paramDate).append(" ").append(tempTime).toString();
	    	Date retDate = parseDateTime(dateTime);
	    	return retDate;
	    }

		/**
		 * 获取dates 天后的日期，精确到日
		 * @return
		 */
		public static Date getCurrentDayTime(Integer dates) {
	         Date date = new Date();
	         SimpleDateFormat sdf = getSdf(YYYY_MM_DD);
	         String dayTime = sdf.format(date);
	         date =  parseDate(dayTime);
			 return  addDays(date,dates);
		}
		
		/**
		 * 获取时间间隔里面的小时
		 * @param timeValue
		 * @return
		 */
		public static Integer getHourValue(String timeValue){
			Integer hour  = null;
			if(StringUtils.isNotBlank(timeValue)){
				String[] times = timeValue.split(":");
				if(times.length>0){
				  hour = Integer.valueOf(times[0]);
				}
			}
			return hour;
		}
		
		/**
		 * 获取分钟
		 * @param timeValue
		 * @return
		 */
		public static Integer getMinuteValue(String timeValue){
			Integer minute  = null;
			if(StringUtils.isNotBlank(timeValue)){
				String[] times = timeValue.split(":");
				if(times.length>0){
				  minute = Integer.valueOf(times[1]);
				}
			}
			return minute;
		}
		
		/**
		 * 将两个时间段相加，获取总时段长
		 * @param time1
		 * @param time2
		 * @return
		 */
		public static String getTotalLongTime(String time1,String time2){
			int totalMinute = 0;
			Integer hour1 = (null == getHourValue(time1))?0:getHourValue(time1);
			Integer hour2 = (null == getHourValue(time2))?0:getHourValue(time2);
			Integer minute1 = (null == getMinuteValue(time1))?0:getMinuteValue(time1);
			Integer minute2 = (null == getMinuteValue(time2))?0:getMinuteValue(time2);
			if(null != hour1 && null != hour2 && null !=minute1 && null !=minute2 ){
				totalMinute = (((hour1 + hour2) * 60) + (minute1 + minute2));
			}
			int totalHour = (totalMinute/60);
	        int totalMimute = totalMinute - (totalHour * 60);
	        StringBuffer retStr = new StringBuffer("");
	        if(totalHour>=0 && totalHour <10){
	        	 retStr.append(0);
	        } 
	        retStr.append(totalHour);
	        retStr.append(":");
	        if(totalMimute >=0 && totalMimute<10){
	        	 retStr.append("0");
	        }
	        retStr.append(totalMimute);
			return retStr.toString();
		}
		 
	    /**
	     * 两个日期间的日期list
	     * @param journeyStartDate
	     * @param journeyEndDate
	     * @return
	     * @throws ParseException
	     */
	    public static List<String> afterNDay(String journeyStartDate, String journeyEndDate) throws ParseException {
	        List<String> dateList = new ArrayList<String>();
	        if (journeyStartDate != null && journeyEndDate != null && journeyStartDate.length() > 0 && journeyEndDate.length() > 0) {
	            dateList.add(journeyStartDate);
	            SimpleDateFormat sdf = getSdf(YYYY_MM_DD);
	            Date d1 = sdf.parse(journeyStartDate);
	            Date d2 = sdf.parse(journeyEndDate);
	            int minus = minusToDay(d2, d1);
	            for (int i = 0; i < minus; i++) {
	                Date date = getNextDay(d1);
	                dateList.add(sdf.format(date));
	                d1 = date;
	            }
	        }
	        return dateList;
	    }
	    public static String formatTime(Date date){
	        if(null == date){
	            return null;
	        }
	        
	        return getSdf(HHMM).format(date);
	    }
	    
	    /**
	     * 两个日期间的Map
	     * @throws ParseException 
	     */
	    public static Map<String,Date> getDateMap(Date start,Date end){
	    	SimpleDateFormat sdf = getSdf(YYYY_MM_DD);
	    	Map<String, Date> dateMap = new TreeMap<String, Date>();		
	    	try {
				List<String> temp=afterNDay(formatDate(start),formatDate(end));
				for(String d:temp){
					dateMap.put(d.substring(5), parseDate(d));		
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			return dateMap;
		}
	    
	    public static void main(String[] args) {
	    	System.out.println(DateUtil.formatDateTime(new Date()));
	    }
}
