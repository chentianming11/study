package com.chen.study.guava.newcollection;

import com.google.common.collect.BoundType;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.TreeMultiset;
import org.junit.Test;

import java.util.Set;

/**
 * 测试MultiSet
 * MultiSet是Set的延伸。它可以多次添加相等的元素，并且MultiSet元素的顺序是无关紧要的
 * 常见使用场景：Multiset有一个有用的功能，就是跟踪每种对象的数量，所以你可以用来进行数字统计。
 * Created by chen on 2018/2/25.
 */
public class TestMultiSet {

    /**
     * 类似于collection操作
     */
    @Test
    public void test1() {
        HashMultiset<String> multiset = HashMultiset.create();
        multiset.add("a"); // 添加一个 a
        System.out.println(multiset);
        multiset.add("b", 2); // 添加2个 b
        multiset.add("c", 5);
        System.out.println(multiset);
        multiset.remove("c", 2); // 移除2个c
        System.out.println(multiset);
        multiset.setCount("a", 3); // 将a元素的 计数设置为3
        System.out.println(multiset);
        multiset.forEach(e -> System.out.println(e)); //遍历所有元素
        System.out.println(multiset.size()); // 元素数量（含重复元素）

    }

    /**
     * 类似于Map<E, Integer>操作
     */
    @Test
    public void test2() {
        String[] strings = {"aaa", "aaa", "bbb", "ccc", "ccc", "ccc", "ccc", "ccc",};
        HashMultiset<String> multiset = HashMultiset.create();
        for (String string : strings) {
            multiset.add(string);
        }

        System.out.println(multiset.count("ccc")); // 返回给定元素的计数
        Set<Multiset.Entry<String>> entrySet = multiset.entrySet(); // 返回entrySet
        entrySet.forEach((e) -> System.out.println(e.getElement() + "=" + e.getCount()));

        Set<String> elementSet = multiset.elementSet(); // 获取元素集合，不包含重复元素
        System.out.println(elementSet);


    }

    /**
     * 测试storedMultiSet
     */
    @Test
    public void test() {
        Integer[] integers = {1, 5, 7, 1, 4, 9, 10, 5, 4, 10, 32, 15};
        TreeMultiset<Integer> treeMultiset = TreeMultiset.create();

        for (Integer integer : integers) {
            treeMultiset.add(integer);
        }

        System.out.println(treeMultiset);

        // 取TreeMultiSet中 小于等于9的所有元素
        System.out.println(treeMultiset.headMultiset(9, BoundType.CLOSED));
        // 取所有大于7的元素
        System.out.println(treeMultiset.tailMultiset(7, BoundType.OPEN));

        // 取大于等于5 小于10的所有元素
        System.out.println(treeMultiset.subMultiset(5, BoundType.CLOSED, 10, BoundType.OPEN));
    }
}
