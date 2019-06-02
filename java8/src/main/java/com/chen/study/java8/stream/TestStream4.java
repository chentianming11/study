package com.chen.study.java8.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by chen on 2018/3/23.
 */
public class TestStream4 {

    /**
     * 那么，Stream是怎么做的呢？
     * 显然不是每次操作都进行迭代，因为这对于执行时间与存储中间变量来说都将是噩梦。
     */
    @Test
    public void test1() {
        List<String> list = Lists.newArrayList(
                "bcd", "cde", "def", "abc", "a", "aa");
        List<String> result = list.stream()
                //.parallel()
                .filter(e -> e.length() >= 3)
                .map(e -> e.charAt(0))
                //.peek(System.out :: println)
                //.sorted()
                //.peek(e -> System.out.println("++++" + e))
                .map(e -> String.valueOf(e))
                .collect(Collectors.toList());
        System.out.println("----------------------------");
        System.out.println(result);
    }


    /**
     * 显然，如果code_2只对集合迭代了一次，也就是说相当高效。那么这么做有没有弊端？
     * 有！模板代码、中间变量、不利于并行都是其存在的问题。
     */
    @Test
    public void test2() {
        List<String> list = Lists.newArrayList(
                "bcd", "cde", "def", "abc");
        List<String> result = Lists.newArrayListWithCapacity(list.size());
        for (String str : list) {
            if (str.length() >= 3) {
                char e = str.charAt(0);
                String tempStr = String.valueOf(e);
                result.add(tempStr);
            }
        }
        System.out.println("----------------------------");
        System.out.println(result);
    }
}
