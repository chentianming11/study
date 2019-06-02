package com.chen.study.java8.objects;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by chen on 2018/2/23.
 */
public class TestObjects {

    @Test
    public void test1() {


        int i1 = Objects.compare("aaa", "bbb", String::compareTo);
        System.out.println(i1);

        System.out.println(Objects.toString(null));

        System.out.println(Objects.toString(null, "空"));

        System.out.println(Objects.equals(null, "aaa"));

        String[] s1 = {"a", "b", "c"};
        String[] s2 = {"a", "b", "c"};
        List<String> l1 = new ArrayList<>();
        l1.add("a");
        l1.add("b");
        List<String> l2 = new ArrayList<>();
        l2.add("a");
        l2.add("b");
        System.out.println(Objects.equals(s1, s2));
        System.out.println(Objects.deepEquals(s1, s2));
        System.out.println("-------------");
        System.out.println(Objects.equals(l1, l2));
        System.out.println(Objects.deepEquals(l1, l2));

        System.out.println("null nonNull");

        System.out.println(Objects.isNull(null));
        System.out.println(Objects.nonNull(null));

        Object o = Objects.requireNonNull(null, "不能为空");
        System.out.println(o.toString());

        Objects.requireNonNull(null, () -> {
            return "空";
        });

    }
}
