package com.chen.study.custom.util.time;

import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Date;

/**
 * Java8 LocalDateTime 工具类
 *
 * @author 陈添明
 */
@UtilityClass
public class LocalDateTimeUtil {

    private static final DateTimeFormatter DEFAULT_DATETIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static final String DATE_TIME_PATTERN_WITH_MILSEC_LINE = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String DATE_TIME_PATTERN_WITH_MILSEC_SLASH = "yyyy/MM/dd HH:mm:ss.SSS";
    public static final String DATE_TIME_PATTERN_WITH_MILSEC_DOUBLE_SLASH = "yyyy\\MM\\dd HH:mm:ss.SSS";
    public static final String DATE_TIME_PATTERN_WITH_MILSEC_NONE = "yyyyMMdd HH:mm:ss.SSS";
    public static final String DATE_TIME_PATTERN_LINE = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_PATTERN_SLASH = "yyyy/MM/dd HH:mm:ss";
    public static final String DATE_TIME_PATTERN_DOUBLE_SLASH = "yyyy\\\\MM\\\\dd HH:mm:ss";
    public static final String DATE_TIME_PATTERN_NONE = "yyyyMMdd HH:mm:ss";
    public static final String DATE_PATTERN_LINE = "yyyy-MM-dd";
    public static final String DATE_PATTERN_SLASH = "yyyy/MM/dd";
    public static final String DATE_PATTERN_DOUBLE_SLASH = "yyyy\\\\MM\\\\dd";
    public static final String DATE_PATTERN_NONE = "yyyyMMdd";
    public static final String TIME_PATTERN_COLON = "HH:mm:ss";
    public static final String TIME_PATTERN_NONE = "HHmmss";

    // 获取当前时间的LocalDateTime对象
    // LocalDateTime.now();

    // 根据年月日构建LocalDateTime
    // LocalDateTime.of();

    // 比较日期先后
    // LocalDateTime.now().isBefore();
    // LocalDateTime.now().isAfter();

    /**
     * String 转换为 LocalDateTime，使用默认格式格式化
     */
    public static LocalDateTime parse(String text) {
        return LocalDateTime.parse(text, DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * String 转换为 LocalDateTime，使用自定义格式格式化
     */
    public static LocalDateTime parse(String text, DateTimeFormatter formatter) {
        return LocalDateTime.parse(text, formatter);
    }

    /**
     * String 转换为 LocalDateTime，使用自定义格式格式化
     */
    public static LocalDateTime parse(String text, String formatterPattern) {
        return LocalDateTime.parse(text, DateTimeFormatter.ofPattern(formatterPattern));
    }

    /**
     * LocalDateTime 转换为 String，使用默认格式格式化
     */
    public static String format(LocalDateTime localDateTime) {
        return localDateTime.format(DEFAULT_DATETIME_FORMATTER);
    }

    /**
     * LocalDateTime 转换为 String，使用自定义格式格式化
     */
    public static String format(LocalDateTime localDateTime, DateTimeFormatter formatter) {
        return localDateTime.format(formatter);
    }

    /**
     * LocalDateTime 转换为 String，使用自定义格式格式化
     */
    public static String format(LocalDateTime localDateTime, String formatterPattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(formatterPattern));
    }

    public static LocalDateTime fromLong(Long longDate) {
        return fromDate(new Date(longDate));
    }

    public static Long toLong(LocalDateTime localDateTime) {
        return toDate(localDateTime).getTime();
    }

    // Date 转换为 LocalDateTime
    public static LocalDateTime fromDate(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    // LocalDateTime 转换为 Date
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static Instant toInstant(Date date) {
        return Instant.ofEpochSecond(date.getTime());
    }

    // 获取指定日期的毫秒
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // 获取指定日期的秒
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    // 获取当前时间的指定格式
    public static String formatNow() {
        return format(LocalDateTime.now());
    }

    // 获取当前时间的指定格式
    public static String formatNow(DateTimeFormatter formatter) {
        return format(LocalDateTime.now(), formatter);
    }

    // 获取当前时间的指定格式
    public static String formatNow(String formatterPattern) {
        return format(LocalDateTime.now(), formatterPattern);
    }

    // 日期加上一个数，根据 field 不同加不同值，field 参数为 ChronoUnit.*
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    // 日期减去一个数，根据 field 不同减不同值，field 参数为 ChronoUnit.*
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }

    /**
     * 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field     单位(年月日时分秒)
     * @return
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    public static int getMinsDiff(LocalDateTime time1, LocalDateTime time2) {
        Duration duration = Duration.between(time1, time2);
        return (int) duration.toMinutes();
    }

    public static long getMillisDiff(LocalDateTime time1, LocalDateTime time2) {
        Duration duration = Duration.between(time1, time2);
        return duration.toMillis();
    }

    // 获取一天的开始时间，2017,7,22 00:00
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }

    // 获取一天的结束时间，2017,7,22 23:59:59.999999999
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time.withHour(23)
                .withMinute(59)
                .withSecond(59)
                .withNano(999999999);
    }

}