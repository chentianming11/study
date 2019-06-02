package com.chen.study.guava.collectionutils;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

/**
 * Created by chen on 2018/2/26.
 */
public class TestIterables {

    @Test
    public void test1() {

        final List<String> list1 = Lists.newArrayList("a", "b", "c");
        final List<String> list2 = Lists.newArrayList("c", "f", "g");
        final List<String> list3 = Lists.newArrayList("x", "b", "g");
        // 串联多个 iterables 的懒视图*
        Iterable<String> concat = Iterables.concat(list1, list2, list3);
        System.out.println(concat);
        // 获取Iterable中指定元素出现的次数
        System.out.println(Iterables.frequency(concat, "c"));
        // 	把 iterable 按指定大小分割，得到的子集都不能进行修改操作
        System.out.println(Iterables.partition(concat, 4));
        // 返回 iterable 的第一个元素，若 iterable 为空则返回默认值
        System.out.println(Iterables.getFirst(concat, "默认值"));
        // Iterable)	如果两个 iterable 中的所有元素相等且顺序一致，返回 true
        Iterables.elementsEqual(list1, list2);
        // 限制 iterable 的元素个数限制给定值
        System.out.println(Iterables.limit(concat, 2));
        // 获取 iterable 中唯一的元素，如果 iterable 为空或有多个元素，则快速失败
        System.out.println(Iterables.getOnlyElement(concat));

    }
}
