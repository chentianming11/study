package com.chen.study.guava.string;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableMap;
import org.junit.Test;
import strman.Strman;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 字符串处理
 * Guava将常用的字符串处理设计了4种角色：连接器、拆分器、匹配器、格式器，很方便我们自己拓展，强大！
 * Created by chen on 2018/2/23.
 */
public class TestStringOps {

    /**
     * joiner 实例总是不可变的。用来定义 joiner 目标语义的配置方法总会返回一个新的 joiner 实例。
     * 这使得 joiner 实例都是线程安全的，你可以将其定义为 static final常量。
     * 测试连接器-Joiner
     */
    @Test
    public void testJoiner() throws IOException {
        // 连接器，
        List<String> list = Arrays.asList("a", null, "b", "c", "d", "f");
        // 跳过null
        String s = Joiner.on(",")
                .skipNulls()
                .join(list);
        System.out.println(s);


        // 给定某个字符串来替换 null
        String s2 = Joiner.on(",")
                .useForNull("zzz")
                .join(list);
        System.out.println(s2);


        // 连接map
        Map<String, Object>  map = ImmutableMap.of("a", 100, "b", 200);
        String s3 = Joiner.on(",")
                .withKeyValueSeparator("-")
                .join(map);

        System.out.println(s3);


        // 连接的内容置入到stringBuffer
        StringBuffer stringBuffer = new StringBuffer().append("max(");
        Joiner.on(",")
                .skipNulls()
                .appendTo(stringBuffer, list);
        stringBuffer.append(")");
        System.out.println(stringBuffer);

    }

    /**
     * 测试拆分器-Splitter
     */
    @Test
    public void testSplitter() {

        String split = "#foo;bar;;  ; qux;";

        // 按分隔符进行拆分
        List<String> list = Splitter.on(";")
                // 移除指定的首尾指定的字符
//                .trimResults(CharMatcher.is('#'))
                //移除结果字符串的前导空白和尾部空白
                .trimResults()
                // 从结果中自动忽略空字符串
                .omitEmptyStrings()
                // 限制拆分出的字符串数量
                .limit(10)
                .splitToList(split);
        System.out.println(list);




        // 按字符长度进行拆分
        List<String> list1 = Splitter
                .fixedLength(3)
                .splitToList("阿大放送dsafasf阿斯顿发");
        System.out.println(list1);


        // 正则拆分
        List<String> list2 = Splitter.onPattern("\\n")
                .splitToList("abc\nzxc\ndfg");
        System.out.println(list2);


        // 拆分成一个map
        Map<String, String> map = Splitter.on(",")
                .withKeyValueSeparator("-")
                .split("a-100,b-200");
        System.out.println(map);



        // 拆分成一个map
        Map<String, String> map2 = Splitter.onPattern("&")
                .withKeyValueSeparator("=")
                .split("a=1&b=2&c=3&d=");
        System.out.println(map2);

    }

    /**
     * 测试字符匹配器-CharMatcher
     */
    @Test
    public void testCharMatcher() {
        // 只保留数字
        String retainFrom = CharMatcher.digit().retainFrom("今天是2016年9月16日");
        System.out.println(retainFrom);

        // 去除两端的空格，并把中间的连续空格替换成单个空格
        String s = CharMatcher.whitespace().trimAndCollapseFrom("  一个 CharMatcher   代表 一类 字符 ", ' ');
        System.out.println(s);

        //用*号替换所有数字
        String s1 = CharMatcher.javaDigit().replaceFrom("我的手机号是13400001234", '*');
        System.out.println(s1);

        // 只保留数字和小写字母
        String s2 = CharMatcher.JAVA_DIGIT.or(CharMatcher.JAVA_LOWER_CASE).retainFrom("yutianran1314@gmail.COM");
        System.out.println(s2);
    }

    /**
     * 测试变量格式器-CaseFormat
     */
    @Test
    public void testCaseFormat() {
        // userName -> user-name
        String userName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "userName");
        System.out.println(userName);

        // userName -> user_name
        String userName1 = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "userName");
        System.out.println(userName1);

        // userName -> USER_NAME
        String userName2 = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "userName");
        System.out.println(userName2);


        /**
         * 我用下面的方式转
         */
        // 字符串转成驼峰  isTest
        String is_emp_test = Strman.toCamelCase("IS_test".toLowerCase());
        System.out.println(is_emp_test);

        // 字符串转羊肉串（中杆）  is-test
        String isTest = Strman.toKebabCase("isTest");
        System.out.println(isTest);

        // 字符串转蛇型（下划线） is_test
        String s = Strman.toSnakeCase("isTest");
        System.out.println(s);

        // 字符串转大驼峰式  IsTest
        String studlyCase = Strman.toStudlyCase("is_test");
        System.out.println(studlyCase);

    }

}
