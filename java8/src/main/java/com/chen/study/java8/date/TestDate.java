package com.chen.study.java8.date;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * Created by chen on 2018/2/23.
 */
public class TestDate {

    /**
     * LocalDate代表一个IOS格式(yyyy-MM-dd)的日期
     * LocalDate可以指定特定的日期，调用of或parse方法返回该实例
     */
    @Test
    public void testLocalDate() {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        System.out.println(localDate.plusDays(1));
        System.out.println(localDate.plus(1, ChronoUnit.YEARS));

        System.out.println("-----------");
        LocalDate date = LocalDate.of(2009, 9, 9);
        System.out.println(date);
        System.out.println(LocalDate.parse("2017-08-01"));

        System.out.println("下面写两个例子，分别解析日期 2017-07-20，获取每周中的星期和每月中的日：");
        DayOfWeek week = LocalDate.parse("2017-07-20").getDayOfWeek();
        System.out.println("星期：" + week);
        System.out.println(LocalDate.parse("2017-07-20").getDayOfMonth());
        // 判断是不是闰年
        System.out.println(LocalDate.parse("2017-07-20").isLeapYear());

        // 判断是否在日期之前或之后:
        System.out.println(localDate.isAfter(LocalDate.parse("2017-07-20")));

        // 获取这个月的第一天:
        System.out.println(localDate.withDayOfMonth(1));
        System.out.println(localDate.with(TemporalAdjusters.firstDayOfMonth()));

        // 判断今天是否是我的生日，例如我的生日是 2009-07-20
        LocalDate birthday = LocalDate.parse("2009-02-23");
        MonthDay monthDay = MonthDay.of(birthday.getMonth(), birthday.getDayOfMonth());
        MonthDay from = MonthDay.from(LocalDate.now());
        System.out.println(Objects.equals(monthDay, from));

    }

    /**
     * LocalTime表示一个时间，而不是日期，下面介绍一下它的使用方法。
     */
    @Test
    public void testLocalTime() {

        // 获取现在的时间
        System.out.println(LocalTime.now());

        // 将一个字符串时间解析为LocalTime，输出15:02
        System.out.println(LocalTime.parse("15:02"));

        // 使用静态方法of创建一个时间
        System.out.println(LocalTime.of(15, 4, 30));

        // 使用解析字符串的方式并添加一小时，输出16:02
        System.out.println(LocalTime.parse("15:02").plusHours(1));

        // 获取时间的小时、分钟
        int hour = LocalTime.parse("15:02").getHour();
        int minute = LocalTime.parse("15:02").getMinute();
        System.out.println(hour + "--" + minute);

        // 我们也可以通过之前类似的API检查一个时间是否在另一个时间之前、之后
        System.out.println(LocalTime.parse("15:02").isBefore(LocalTime.parse("16:02")));

        // 在LocalTime类中也将每天的开始和结束作为常量供我们使用:
        System.out.println(LocalTime.MAX);
        System.out.println(LocalTime.MIN);
    }

    /**
     * LocalDateTime是用来表示日期和时间的，这是一个最常用的类之一。
     */

    @Test
    public void TestDateTime() {

        // 获取当前的日期和时间:
        System.out.println(LocalDateTime.now());

        // 下面使用静态方法和字符串的方式分别创建 LocalDateTime 对象
        LocalDateTime localDateTime = LocalDateTime.of(2018, Month.JANUARY, 20, 18, 10);
        System.out.println(localDateTime);
        System.out.println(LocalDateTime.parse("2017-07-20T15:18:00"));

        // //同时LocalDateTime也提供了相关API来对日期和时间进行增减操作:
        System.out.println(localDateTime.plusDays(1));
        System.out.println(localDateTime.plusHours(2));

        // 这个类也提供一系列的get方法来获取特定单位:
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getDayOfYear());
        System.out.println(localDateTime.getHour());
    }

    /**
     * 在日常开发中我们用到最多的也许就是日期、时间的格式化了，那在Java8种该如何操作呢？
     */
    @Test
    public void test5() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("默认格式：" + now);
        // 日期时间转自定义格式字符串
        System.out.println("格式化：" + now.format(dateTimeFormatter));
        // 自定义格式字符串转日期时间
        LocalDateTime parse = LocalDateTime.parse("2017-07-20 15:27:44", dateTimeFormatter);
        System.out.println(parse);

        /**
         * 也可以使用DateTimeFormatter的format方法将日期、时间格式化为字符串
         */
        String format = dateTimeFormatter.format(LocalDateTime.now());
        System.out.println(format);
    }

    /**
     * Period类用于修改给定日期或获得的两个日期之间的区别。
     */
    @Test
    public void testPeriod() {

        // 给初始日期加5天
        LocalDate initialDate = LocalDate.now();
        final LocalDate finalDate = initialDate.plus(Period.ofDays(5));
        System.out.println("初始化日期: " + initialDate);
        System.out.println("加日期之后: " + finalDate);

        // 周期API中提供给我们可以比较两个日期的差别，像下面这样获取差距天数:
        Period period = Period.between(initialDate, finalDate);
        System.out.println(period);

        long between = ChronoUnit.DAYS.between(initialDate, finalDate);
        System.out.println(between);

    }

    /**
     * 与遗留代码转换
     */
    public void test8() {

        // Date和Instant互相转换
        Date date = Date.from(Instant.now());
        Instant instant = date.toInstant();

        // Date转换成LocalDateTime
        Instant instant2 = new Date().toInstant();
        LocalDateTime dateTime = instant2.atZone(ZoneId.systemDefault()).toLocalDateTime();

        // LocalDateTime转换成Date
        ZonedDateTime zonedDateTime = dateTime.atZone(ZoneId.systemDefault());
        Instant instant1 = zonedDateTime.toInstant();
        Date date1 = Date.from(instant1);

        // Date转LocalDate
        LocalDate localDate = new Date().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

        // LocalDate转Date
        Instant instant3 = LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant();
        Date date2 = Date.from(instant3);

    }
}
