package com.eeeffff.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;

/**
 * 日期处理工具类
 */
public class DateTimeUtils {
	
	public static final String PATTERN_CLASSICAL = "yyyy-MM-dd HH:mm:ss";
	public static final String PATTERN_CLASSICAL_NORMAL = "yyyy-MM-dd HH:mm";
	public static final String PATTERN_CLASSICAL_SIMPLE = "yyyy-MM-dd";
	public static final String PATTERN_CLASSICAL_TIME = "HH:mm:ss";

    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmmss");
    public static final DateTimeFormatter MONTH_FORMATTER = DateTimeFormatter.ofPattern("yyyyMM");
    public static final DateTimeFormatter SHORT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyMMdd");
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DATE_FORMATTER_EXT = DateTimeFormatter.ofPattern("yyyyMMdd");
    public static final DateTimeFormatter SHORT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyMMddHHmmss");
    public static final DateTimeFormatter DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DATETIME_FORMATTER_NO_SEC = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DATETIME_FORMATTER_EXT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private DateTimeUtils() {
    }

    /**
     * 获取当前的日期
     *
     * @return
     */
    public static LocalDate getCurrentLocalDate() {
        return LocalDate.now();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static LocalTime getCurrentLocalTime() {
        return LocalTime.now();
    }

    /**
     * 获取当前日期时间
     *
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }

    /**
     * yyyyMMdd
     *
     * @return
     */
    public static String getCurrentDateStr() {
        return LocalDate.now().format(DATE_FORMATTER);
    }

    /**
     * yyMMdd
     *
     * @return
     */
    public static String getCurrentShortDateStr() {
        return LocalDate.now().format(SHORT_DATE_FORMATTER);
    }

    /**
     * yyyyMM
     *
     * @return
     */
    public static String getCurrentMonthStr() {
        return LocalDate.now().format(MONTH_FORMATTER);
    }

    /**
     * yyyyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentDateTimeStr() {
        return LocalDateTime.now().format(DATETIME_FORMATTER);
    }

    /**
     * yyMMddHHmmss
     *
     * @return
     */
    public static String getCurrentShortDateTimeStr() {
        return LocalDateTime.now().format(SHORT_DATETIME_FORMATTER);
    }

    /**
     * HHmmss
     *
     * @return
     */
    public static String getCurrentTimeStr() {
        return LocalTime.now().format(TIME_FORMATTER);
    }

    /**
     * 获取当前日期格式化的字符串
     *
     * @param pattern 日期格式
     * @return
     */
    public static String getCurrentDateStr(String pattern) {
        return LocalDate.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前日期时间格式化的字符串
     *
     * @param pattern 日期时间格式
     * @return
     */
    public static String getCurrentDateTimeStr(String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取当前时间格式化的字符串
     *
     * @param pattern 时间格式
     * @return
     */
    public static String getCurrentTimeStr(String pattern) {
        return LocalTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将字符串日期格式化为日期
     *
     * @param dateStr 日期字符串
     * @param pattern 日期格式
     * @return
     */
    public static LocalDate parseLocalDate(String dateStr, String pattern) {
        return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将字符串日期格式化为日期
     *
     * @param dateStr 日期字符串
     * @return
     */
    public static LocalDate parseLocalDate(String dateStr) {
        return LocalDate.parse(dateStr, DATE_FORMATTER);
    }

    /**
     * 将字符串日期时间格式化为日期时间
     *
     * @param dateTimeStr 日期时间字符串
     * @param pattern     日期时间格式
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String dateTimeStr, String pattern) {
        return LocalDateTime.parse(dateTimeStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将字符串日期时间格式化为日期时间
     *
     * @param dateTimeStr 日期时间字符串
     * @return
     */
    public static LocalDateTime parseLocalDateTime(String dateTimeStr) {
        return LocalDateTime.parse(dateTimeStr, DATETIME_FORMATTER);
    }

    /**
     * 将字符串时间格式化为时间
     *
     * @param timeStr 字符串时间
     * @param pattern 时间格式
     * @return
     */
    public static LocalTime parseLocalTime(String timeStr, String pattern) {
        return LocalTime.parse(timeStr, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 将字符串时间格式化为时间
     *
     * @param timeStr 字符串时间
     * @return
     */
    public static LocalTime parseLocalTime(String timeStr) {
        return LocalTime.parse(timeStr, TIME_FORMATTER);
    }
    
    /**
     * 根据指定格式将指定字符串解析成日期
     *
     * @param str     指定日期
     * @param pattern 指定格式
     * @return 返回解析后的日期
     */
    public static Date parse(String str, String pattern) {
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      try {
        return sdf.parse(str);
      } catch (Exception e) {
        e.printStackTrace();
      }
      return null;
    }

    /**
     * 根据默认格式将日期转格式化成字符串
     *
     * @param date 指定日期
     * @return 返回格式化后的字符串
     */
    public static String format(Date date) {
      return format(date, PATTERN_CLASSICAL);
    }

    /**
     * 根据默认格式将时间转格式化成字符串
     *
     * @param time 指定日期
     * @return 返回格式化后的字符串
     */
    public static String format(long time) {
      Date date = new Date(time);
      return format(date, PATTERN_CLASSICAL);
    }

    /**
     * 根据指定格式将指定日期格式化成字符串
     *
     * @param date    指定日期
     * @param pattern 指定格式
     * @return 返回格式化后的字符串
     */
    public static String format(Date date, String pattern) {
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      return sdf.format(date);
    }

    /**
     * 根据指定格式将指定时间格式化成字符串
     *
     * @param time    指定时间
     * @param pattern 指定格式
     * @return 返回格式化后的字符串
     */
    public static String format(long time, String pattern) {
      Date date = new Date(time);
      SimpleDateFormat sdf = new SimpleDateFormat(pattern);
      return sdf.format(date);
    }

    /**
     * 格式化传入的日期
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return
     */
    public static String formatLocalDate(LocalDate date, String pattern) {
        return Objects.isNull(date) ? "" : date.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化传入的日期
     *
     * @param date      日期
     * @param formatter 日期格式
     * @return
     */
    public static String formatLocalDate(LocalDate date, DateTimeFormatter formatter) {
        return Objects.isNull(date) ? "" : date.format(formatter);
    }

    /**
     * 格式化传入的日期
     *
     * @param date 日期
     * @return
     */
    public static String formatLocalDate(LocalDate date) {
        return Objects.isNull(date) ? "" : date.format(DATE_FORMATTER);
    }

    /**
     * 格式化传入的日期时间
     *
     * @param datetime 日期时间
     * @param pattern  日期时间格式
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime datetime, String pattern) {
        return Objects.isNull(datetime) ? "" : datetime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化传入的日期时间
     *
     * @param datetime  日期时间
     * @param formatter 日期时间格式
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime datetime, DateTimeFormatter formatter) {
        return Objects.isNull(datetime) ? "" : datetime.format(formatter);
    }

    /**
     * 格式化传入的日期时间
     *
     * @param datetime 日期时间
     * @return
     */
    public static String formatLocalDateTime(LocalDateTime datetime) {
        return Objects.isNull(datetime) ? "" : datetime.format(DATETIME_FORMATTER);
    }

    /**
     * 格式化传入的时间
     *
     * @param time    时间
     * @param pattern 时间格式
     * @return
     */
    public static String formatLocalTime(LocalTime time, String pattern) {
        return Objects.isNull(time) ? "" : time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化传入的时间
     *
     * @param time      时间
     * @param formatter 时间格式
     * @return
     */
    public static String formatLocalTime(LocalTime time, DateTimeFormatter formatter) {
        return Objects.isNull(time) ? "" : time.format(formatter);
    }

    /**
     * 格式化传入的时间
     *
     * @param time 时间
     * @return
     */
    public static String formatLocalTime(LocalTime time) {
        return Objects.isNull(time) ? "" : time.format(TIME_FORMATTER);
    }

    /**
     * 获取两个日期相隔的天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return
     */
    public static long periodDays(LocalDate startDate, LocalDate endDate) {
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    
    /**
     * 获取两个日期之间间隔的秒数
     * @param assignA
     * @param assignB
     * @return
     */
    public static Long periodSecond(LocalDateTime assignA, LocalDateTime assignB) { 
    	return Long.valueOf((new Integer(Math.abs(assignA.getNano() - assignB.getNano()) / 1000)).longValue()); 
    }

    /**
     * 获取两个日期相隔的小时
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationHours(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toHours();
    }

    /**
     * 获取两个日期相隔的分钟
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMinutes(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMinutes();
    }

    /**
     * 获取两个日期相隔的秒数
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationSeconds(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).getSeconds();
    }

    /**
     * 获取两个日期相隔的毫秒数
     *
     * @param startInclusive
     * @param endExclusive
     * @return
     */
    public static long durationMillis(Temporal startInclusive, Temporal endExclusive) {
        return Duration.between(startInclusive, endExclusive).toMillis();
    }

    /**
     * 是否当天
     *
     * @param date
     * @return
     */
    public static boolean isToday(LocalDate date) {
        return getCurrentLocalDate().equals(date);
    }

    /**
     * 获取传入日期时间的时间戳
     *
     * @param dateTime
     * @return
     */
    public static long toEpochMilli(LocalDateTime dateTime) {
        return dateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 将date转换为LocalDateTime
     *
     * @param date
     * @return
     */
    public static LocalDateTime convertDate2LocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDate转换为date
     *
     * @param localDate
     * @return
     */
    public static Date convertLocalDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDateTime转换为date
     *
     * @param localDateTime
     * @return
     */
    public static Date convertLocalDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取相隔的秒数
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static long getSecondsBetweenDate(LocalDateTime startDate, LocalDateTime endDate) {
        return Duration.between(startDate, endDate).getSeconds();
    }

    /**
     * 获取相隔的分钟
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static long getMinutesBetweenDate(LocalDateTime startDate, LocalDateTime endDate) {
        return Duration.between(startDate, endDate).toMinutes();
    }

    /**
     * 获取相隔的天数
     *
     * @param startDate 开始时间
     * @param endDate   结束时间
     * @return
     */
    public static long getDaysBetweenDate(LocalDateTime startDate, LocalDateTime endDate) {
        return Duration.between(startDate, endDate).toDays();
    }

    /**
     * 获取当前日期间隔多少天的日期
     *
     * @return
     */
    @SuppressWarnings("unchecked")
	public static <T extends Temporal> T getNextDateOffsetDate(T datetime, long amount, TemporalUnit unit) {
        return (T) datetime.plus(amount, unit);
    }

    /**
     * 时间戳转换为LocalDateTime
     *
     * @param timestamp
     * @return
     */
    public static LocalDateTime parseTimestamp(long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault());
    }
    
    /**
     * 获取时间date1与date2相差的秒数
     *
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 返回相差的秒数
     */
    public static int getOffsetSeconds(Date date1, Date date2) {
      int seconds = (int) ((date2.getTime() - date1.getTime()) / 1000);
      return seconds;
    }

    /**
     * 获取时间date1与date2相差的分钟数
     *
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 返回相差的分钟数
     */
    public static int getOffsetMinutes(Date date1, Date date2) {
      return getOffsetSeconds(date1, date2) / 60;
    }

    /**
     * 获取时间date1与date2相差的小时数
     *
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 返回相差的小时数
     */
    public static int getOffsetHours(Date date1, Date date2) {
      return getOffsetMinutes(date1, date2) / 60;
    }

    /**
     * 获取时间date1与date2相差的天数数
     *
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 返回相差的天数
     */
    public static int getOffsetDays(Date date1, Date date2) {
      return getOffsetHours(date1, date2) / 24;
    }

    /**
     * 获取时间date1与date2相差的周数
     *
     * @param date1 起始时间
     * @param date2 结束时间
     * @return 返回相差的周数
     */
    public static int getOffsetWeeks(Date date1, Date date2) {
      return getOffsetDays(date1, date2) / 7;
    }


    public static Date getDateTime(int year, int month, int day, int hour, int minute, int second, int mmmm) {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.YEAR, year);
      cal.set(Calendar.MONTH, month);
      cal.set(Calendar.DAY_OF_MONTH, day);
      cal.set(Calendar.HOUR_OF_DAY, hour);
      cal.set(Calendar.SECOND, second);
      cal.set(Calendar.MINUTE, minute);
      cal.set(Calendar.MILLISECOND, mmmm);
      return cal.getTime();
    }

    /**
     * 获取重置指定日期的时分秒后的时间
     *
     * @param date   指定日期
     * @param hour   指定小时
     * @param minute 指定分钟
     * @param second 指定秒
     * @return 返回重置时分秒后的时间
     */
    public static Date getResetTime(Date date, int hour, int minute, int second) {
      return getResetTime(date, hour, minute, second, 0);
    }

    /**
     * 获取重置指定日期的时分秒后的时间
     *
     * @param date   指定日期
     * @param hour   指定小时
     * @param minute 指定分钟
     * @param second 指定秒
     * @return 返回重置时分秒后的时间
     */
    public static Date getResetTime(Date date, int hour, int minute, int second, int mmmm) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      cal.set(Calendar.HOUR_OF_DAY, hour);
      cal.set(Calendar.SECOND, second);
      cal.set(Calendar.MINUTE, minute);
      cal.set(Calendar.MILLISECOND, mmmm);
      return cal.getTime();
    }

    /**
     * 返回指定日期的起始时间
     *
     * @param date 指定日期（例如2014-08-01）
     * @return 返回起始时间（例如2014-08-01 00:00:00）
     */
    public static Date getIntegralStartTime(Date date) {
      return getResetTime(date, 0, 0, 0);
    }

    /**
     * 返回指定日期的结束时间
     *
     * @param date 指定日期（例如2014-08-01）
     * @return 返回结束时间（例如2014-08-01 23:59:59）
     */
    public static Date getIntegralEndTime(Date date) {
      return getResetTime(date, 23, 59, 59);
    }

    /**
     * 获取指定日期累加年月日后的时间
     *
     * @param date  指定日期
     * @param year  指定年数
     * @param month 指定月数
     * @param day   指定天数
     * @return 返回累加年月日后的时间
     */
    public static Date rollDate(Date date, int year, int month, int day, int hour, int minute, int second) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      cal.add(Calendar.YEAR, year);
      cal.add(Calendar.MONTH, month);
      cal.add(Calendar.DAY_OF_MONTH, day);
      cal.add(Calendar.HOUR, hour);
      cal.add(Calendar.MINUTE, minute);
      cal.add(Calendar.SECOND, second);
      return cal.getTime();
    }

    /**
     * 获取指定日期累加指定月数后的时间
     *
     * @param date  指定日期
     * @param month 指定月数
     * @return 返回累加月数后的时间
     */
    public static Date rollMonth(Date date, int month) {
      return rollDate(date, 0, month, 0, 0, 0, 0);
    }

    /**
     * 获取指定日期累加指定天数后的时间
     *
     * @param date 指定日期
     * @param day  指定天数
     * @return 返回累加天数后的时间
     */
    public static Date rollDay(Date date, int day) {
      return rollDate(date, 0, 0, day, 0, 0, 0);
    }

    /**
     * 获取指定日期累加指定天数后的时间
     *
     * @param date 指定日期
     * @param hour 指定小时
     * @return 返回累加天数后的时间
     */
    public static Date rollHour(Date date, int hour) {
      return rollDate(date, 0, 0, 0, hour, 0, 0);
    }

    /**
     * 获取指定日期累加指定天数后的时间
     *
     * @param date   指定日期
     * @param minute 指定分钟
     * @return 返回累加天数后的时间
     */
    public static Date rollMinute(Date date, int minute) {
      return rollDate(date, 0, 0, 0, 0, minute, 0);
    }

    /**
     * 计算指定日期所在月份的天数
     *
     * @param date 指定日期
     * @return 返回所在月份的天数
     */
    public static int getDayOfMonth(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      int dayOfMonth = cal.getActualMaximum(Calendar.DATE);
      return dayOfMonth;
    }

    /**
     * 获取当月第一天的起始时间，例如2014-08-01 00:00:00
     *
     * @return 返回当月第一天的起始时间
     */
    public static Date getMonthStartTime() {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.DAY_OF_MONTH, 1);
      return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当月第一天的起始时间，例如2014-08-01 00:00:00
     *
     * @param date
     * @return 返回当月第一天的起始时间
     */
    public static Date getMonthStartTime(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      cal.set(Calendar.DAY_OF_MONTH, 1);
      return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当月最后一天的结束时间，例如2014-08-31 23:59:59
     *
     * @param date
     * @return 返回当月最后一天的结束时间
     */
    public static Date getMonthEndTime(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
      return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取当月最后一天的结束时间，例如2014-08-31 23:59:59
     *
     * @return 返回当月最后一天的结束时间
     */
    public static Date getMonthEndTime() {
      Calendar cal = Calendar.getInstance();
      cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
      return getIntegralEndTime(cal.getTime());
    }


    /**
     * 获取上个月第一天的起始时间，例如2014-07-01 00:00:00
     *
     * @param date
     * @return 返回上个月第一天的起始时间
     */
    public static Date getLastMonthStartTime(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      cal.add(Calendar.MONTH, -1);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取上个月第一天的起始时间，例如2014-07-01 00:00:00
     *
     * @return 返回上个月第一天的起始时间
     */
    public static Date getLastMonthStartTime() {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.MONTH, -1);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取上个月最后一天的结束时间，例如2014-07-31 23:59:59
     * 
     * @param date
     * @return 返回上个月最后一天的结束时间
     */
    public static Date getLastMonthEndTime(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      cal.add(Calendar.MONTH, -1);
      cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
      return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取上个月最后一天的结束时间，例如2014-07-31 23:59:59
     *
     * @return 返回上个月最后一天的结束时间
     */
    public static Date getLastMonthEndTime() {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.MONTH, -1);
      cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
      return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取下个月第一天的起始时间，例如2014-09-01 00:00:00
     *
     * @return 返回下个月第一天的起始时间
     */
    public static Date getNextMonthStartTime() {
      return getNextMonthStartTime(null);
    }

    /**
     * 
     * @param date
     * @return
     */
    public static Date getNextMonthStartTime(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      cal.add(Calendar.MONTH, 1);
      cal.set(Calendar.DAY_OF_MONTH, 1);
      return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取下个月最后一天的结束时间，例如2014-09-30 23:59:59
     *
     * @return 返回下个月最后一天的结束时间
     */
    public static Date getNextMonthEndTime() {
      Calendar cal = Calendar.getInstance();
      cal.add(Calendar.MONTH, 1);
      cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
      return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取当前季度第一天的起始时间
     *
     * @return 返回当前季度第一天的起始时间
     */
    public static Date getQuarterStartTime() {
      Calendar cal = Calendar.getInstance();
      int month = cal.get(Calendar.MONTH);
      if (month < 3) {
        cal.set(Calendar.MONTH, 0);
      } else if (month < 6) {
        cal.set(Calendar.MONTH, 3);
      } else if (month < 9) {
        cal.set(Calendar.MONTH, 6);
      } else {
        cal.set(Calendar.MONTH, 9);
      }
      cal.set(Calendar.DAY_OF_MONTH, 1);
      return getIntegralStartTime(cal.getTime());
    }

    /**
     * 获取当前季度最后一天的结束时间
     *
     * @return 返回当前季度最后一天的结束时间
     */
    public static Date getQuarterEndTime() {
      Calendar cal = Calendar.getInstance();
      int month = cal.get(Calendar.MONTH);
      if (month < 3) {
        cal.set(Calendar.MONTH, 2);
      } else if (month < 6) {
        cal.set(Calendar.MONTH, 5);
      } else if (month < 9) {
        cal.set(Calendar.MONTH, 8);
      } else {
        cal.set(Calendar.MONTH, 11);
      }
      cal.set(Calendar.DAY_OF_MONTH, getDayOfMonth(cal.getTime()));
      return getIntegralEndTime(cal.getTime());
    }

    /**
     * 获取指定日期是星期几
     *
     * @param date 指定日期
     * @return 返回星期几的描述
     */
    public static String getWeekdayDesc(Date date) {
      final String[] weeks = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四",
          "星期五", "星期六"};
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      return weeks[cal.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * 获取指定日期距离当前时间的时间差描述（如3小时前、1天前）
     *
     * @param date 指定日期
     * @return 返回时间差的描述
     */
    public static String getTimeOffsetDesc(Date date) {
      int seconds = getOffsetSeconds(date, new Date());
      if (Math.abs(seconds) < 60) {
        return Math.abs(seconds) + "秒" + (seconds > 0 ? "前" : "后");
      }
      int minutes = seconds / 60;
      if (Math.abs(minutes) < 60) {
        return Math.abs(minutes) + "分钟" + (minutes > 0 ? "前" : "后");
      }
      int hours = minutes / 60;
      if (Math.abs(hours) < 60) {
        return Math.abs(hours) + "小时" + (hours > 0 ? "前" : "后");
      }
      int days = hours / 24;
      if (Math.abs(days) < 7) {
        return Math.abs(days) + "天" + (days > 0 ? "前" : "后");
      }
      int weeks = days / 7;
      if (Math.abs(weeks) < 5) {
        return Math.abs(weeks) + "周" + (weeks > 0 ? "前" : "后");
      }
      int monthes = days / 30;
      if (Math.abs(monthes) < 12) {
        return Math.abs(monthes) + "个月" + (monthes > 0 ? "前" : "后");
      }
      int years = monthes / 12;
      return Math.abs(years) + "年" + (years > 0 ? "前" : "后");
    }

    public static Date getNextWeek(Date date, int week) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }

      cal.add(Calendar.DATE, 7);
      cal.set(Calendar.DAY_OF_WEEK, week);

      return cal.getTime();
    }

    public static Date getNextDay(Date date, int days) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DATE, days);
      return calendar.getTime();
    }

    public static Date getNextWeek(int week) {
      return getNextWeek(null, week);
    }

    public static Date getPrevWeek(Date date, int week) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }

      cal.add(Calendar.DATE, -7);
      cal.set(Calendar.DAY_OF_WEEK, week);

      return cal.getTime();
    }

    public static Date getPrevWeek(int week) {
      return getPrevWeek(null, week);
    }


    /**
     * 获取当前时间
     */
    public static Date getDateTime() {
      Date date = new Date(System.currentTimeMillis());
      return date;
    }

    /**
     * 获取当前日期 （时分秒为0）
     */
    public static Date getDate() {
      Date date = Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant());
      return date;
    }

    /**
     * 计算指定时间的年数
     *
     * @param date 指定日期
     * @return 返回时间的年数
     */
    public static int getYear(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      int year = cal.get(Calendar.YEAR);
      return year;
    }

    public static int getYear() {
      return getYear(null);
    }

    /**
     * 计算指定时间的月份
     *
     * @param date 指定日期
     * @return 返回时间的月份
     */
    public static int getMonth(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      int month = cal.get(Calendar.MONTH);
      return month + 1;
    }

    public static int getMonth() {
      return getMonth(null);
    }

    /**
     * @param date
     * @return
     */
    public static int getDay(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      int day = cal.get(Calendar.DAY_OF_MONTH);
      return day;
    }

    public static int getDay() {
      return getDay(null);
    }

    public static String getDayStartStr(Date day) {
      SimpleDateFormat formatter = new SimpleDateFormat(PATTERN_CLASSICAL_SIMPLE);
      String beginDate = formatter.format(day);
      return beginDate + " 00:00:00";
    }

    /**
     * 
     * @param date
     * @return
     */
    public static Date getHourStart(Date date) {
      Calendar c = Calendar.getInstance();
      c.setTime(date);
      c.set(12, 0);
      c.set(13, 0);
      return c.getTime();
    }

    /**
     * 计算指定时间的小时数
     *
     * @param date 指定日期
     * @return 返回时间的小时数
     */
    public static int getHours(Date date) {
      Calendar cal = Calendar.getInstance();
      if (date != null) {
        cal.setTime(date);
      }
      int hours = cal.get(Calendar.HOUR_OF_DAY);
      return hours;
    }

    /**
     * 计算当前时间的小时数
     *
     * @return 返回当前时间的小时数
     */
    public static int getHours() {
      return getHours(null);
    }


    /**
     * addOrMinusDate  添加或者减少时间.
     *
     * @author jiang chao
     * @param date
     *         [原始时间]
     * @param day
     *         [天]
     * @param hour
     *         [小时]
     * @param minute
     *         [分]
     * @param second
     *         [秒]
     * @return java.util.Date
     */
    public static Date addOrMinusDate(Date date,int day,int hour,int minute,int second) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.DATE,day);
      calendar.add(Calendar.HOUR,hour);
      calendar.add(Calendar.MINUTE,minute);
      calendar.add(Calendar.SECOND,second);
      return calendar.getTime();
    }

    /**
     * 根据日期获取当前月份的最后一天
     *
     * @param day
     * @param patern
     * @return
     * @author liuyang
     */
    public static String getLastDayOfMonth(Date day, String patern) {
      SimpleDateFormat format = new SimpleDateFormat(patern);
      // 获取前月的最后一天
      Calendar cale = Calendar.getInstance();
      cale.setTime(day);
      cale.add(Calendar.MONTH, 1);
      cale.set(Calendar.DAY_OF_MONTH, 0);// 设置为0号,当前日期既为本月最后一天
      String lastDay = format.format(cale.getTime());
      return lastDay;
    }

    /**
     * 将字符串格式的时间转化为Date类型的日期，
     *
     * @param dateString
     * @param format
     * @return
     */
    public static Date stringToDate(String dateString, String format) {
  	if(StringUtils.isEmpty(dateString)) {
  		return null;
  	}
      SimpleDateFormat formatter = new SimpleDateFormat(format);
      try {
        Date date = formatter.parse(dateString);
        return date;
      } catch (ParseException e) {
        e.printStackTrace();
      }
      return null;
    }

    /**
     * 计算两个日期的天差
     *
     * @param beforDate
     * @param afterDate
     * @return
     */
    public static Long getDayBetweenDate(Date beforDate, Date afterDate) {
  	if(beforDate == null || afterDate == null) {
      	return 0L;
      }
      //统一转换成 yyyy-MM-dd 格式
      beforDate = stringToDate(dateToString(beforDate, PATTERN_CLASSICAL_SIMPLE), PATTERN_CLASSICAL_SIMPLE);
      afterDate = stringToDate(dateToString(afterDate, PATTERN_CLASSICAL_SIMPLE), PATTERN_CLASSICAL_SIMPLE);
      if(beforDate == null || afterDate == null) {
      	return 0L;
      }
      return (afterDate.getTime() - beforDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    /**
     * 将javaDate类型的日期转化为自定义格式的字符串
     *
     * @param date
     * @param format
     * @return
     */
    public static String dateToString(Date date, String format) {

      SimpleDateFormat formatter = new SimpleDateFormat(format);
      String dateString = formatter.format(date);
      return dateString;
    }


    /**
     * 获取当前日期多少天之后的日期
     *
     * @param days
     * @return
     */
    public static Date getNextDayByCurrent(int days) {
      Calendar calendar = Calendar.getInstance();
      calendar.add(Calendar.DAY_OF_MONTH, days);
      return calendar.getTime();
    }


    /**
     * 获取某个月的第一天
     *
     * @param date
     * @return
     */
    public static Date getFirstDayOfMonth(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.set(Calendar.DAY_OF_MONTH, calendar.getMinimum(Calendar.DAY_OF_MONTH));
      return calendar.getTime();
    }

    /**
     * 获取某个月的最后一天
     *
     * @param date
     * @return
     */
    public static Date getLastDayOfMonth(Date date) {
      Calendar calendar = Calendar.getInstance();
      calendar.setTime(date);
      calendar.add(Calendar.MONTH, 1);
      calendar.set(Calendar.DAY_OF_MONTH, 0);
      return calendar.getTime();
    }


    /**
     * 获取日期的当天结束时间字符串
     *
     * @return
     */
    public static String getDayEndStr(Date day, String pattern) {
      SimpleDateFormat formatter = new SimpleDateFormat(pattern);
      String endDate = formatter.format(day);
      return endDate + " 23:59:59";
    }

    public static void main(String[] args) {

        System.out.println(DateTimeUtils.toEpochMilli(LocalDateTime.now()));

        System.out.println(DateTimeUtils.parseTimestamp(1554429466000L));

        String endTime = DateTimeUtils.formatLocalDateTime(DateTimeUtils.getNextDateOffsetDate(LocalDateTime.now(), -60, ChronoUnit.MINUTES));
        System.out.println(endTime);

        LocalDate now = LocalDate.now();
        LocalDate now1 = LocalDate.now();
        System.out.println(Objects.equals(now, now1));

    }
}
