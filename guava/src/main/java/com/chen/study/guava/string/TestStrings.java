package com.chen.study.guava.string;

import com.google.common.base.Strings;
import org.junit.Test;

/**
 * Created by chen on 2018/3/3.
 */
public class TestStrings {

    @Test
    public void test1() {
        // 获取2个字符串共同的前缀
        String commonPrefix = Strings.commonPrefix("aaacc", "aabbb");
        System.out.println("共同的前缀：" + commonPrefix);
        // 获取2个字符串共同的后缀
        System.out.println(Strings.commonSuffix("chentianming", "wangtidsaming"));
        // 将“”空字符串转换成null
        System.out.println(Strings.emptyToNull(""));
        // 判断是否为null或为“”
        System.out.println(Strings.isNullOrEmpty(""));
        // 将null 转换成“”
        System.out.println(Strings.nullToEmpty(null));
        // 前置补足字符串
        System.out.println(Strings.padStart("chen", 8, 'x'));
        // 后置补足字符串
        System.out.println(Strings.padEnd("chen", 8, 'x'));
        // 重复字符串
        System.out.println(Strings.repeat("chen", 3));

    }
}
