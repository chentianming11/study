package com.chen.study.custom.util.time;

import lombok.experimental.UtilityClass;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Java8 LocalDate 工具类
 *
 * @author 陈添明
 */
@UtilityClass
public class LocalDateUtil {

    private static final DateTimeFormatter DEFAULT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * String 转换为 LocalDateTime，使用默认格式格式化
     */
    public static LocalDate parse(String text) {
        return parse(text, DEFAULT_DATE_FORMATTER);
    }

    /**
     * String 转换为 LocalDateTime，使用自定义格式格式化
     */
    public static LocalDate parse(String text, DateTimeFormatter formatter) {
        return LocalDate.parse(text, formatter);
    }

    /**
     * String 转换为 LocalDateTime，使用自定义格式格式化
     */
    public static LocalDate parse(String text, String formatterPattern) {
        return LocalDate.parse(text, DateTimeFormatter.ofPattern(formatterPattern));
    }

    /**
     * LocalDateTime 转换为 String，使用默认格式格式化
     */
    public static String format(LocalDate localDate) {
        return format(localDate, DEFAULT_DATE_FORMATTER);
    }

    /**
     * LocalDateTime 转换为 String，使用自定义格式格式化
     */
    public static String format(LocalDate localDate, DateTimeFormatter formatter) {
        return localDate.format(formatter);
    }

    /**
     * LocalDateTime 转换为 String，使用自定义格式格式化
     */
    public static String format(LocalDate localDate, String formatterPattern) {
        return localDate.format(DateTimeFormatter.ofPattern(formatterPattern));
    }

    public static Date toDate(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate fromDate(Date date) {
        return Instant.ofEpochMilli(date.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static LocalDate fromEpochMilli(long epochMilli) {
        LocalDate localDate = Instant.ofEpochMilli(epochMilli).atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate;
    }

}