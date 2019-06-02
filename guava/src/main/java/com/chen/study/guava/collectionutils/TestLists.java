package com.chen.study.guava.collectionutils;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chen on 2018/2/25.
 */
public class TestLists {

    @Test
    public void test1() {
        // 通过Lists创建集合
        ArrayList<Integer> list = Lists.newArrayList(1, 2, 3, 4, 5);
        // 将数组转换成集合，并加入一个first元素
        List<Integer> list1 = Lists.asList(null, new Integer[]{6, 7, 8});
        System.out.println(list1);
        // 反转list
        System.out.println(Lists.reverse(list));
        // 转换list中的元素
        List<String> transform = Lists.transform(list, (integer -> String.valueOf(integer)));

        // 按数量拆分list
        List<List<Integer>> partition = Lists.partition(list, 2);
        System.out.println(partition);

    }
}
