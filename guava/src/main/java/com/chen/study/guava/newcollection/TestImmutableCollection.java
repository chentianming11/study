package com.chen.study.guava.newcollection;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSortedSet;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 不可变集合：顾名思义就是说集合是不可被修改的。集合的数据项是在创建的时候提供，并且在整个生命周期中都不可改变。
 * 当对象被不可信的库调用时，不可变形式是安全的；
 * 不可变对象被多个线程调用时，不存在竞态条件问题
 * 不可变集合不需要考虑变化，因此可以节省时间和空间。所有不可变的集合都比它们的可变形式有更好的内存利用率（分析和测试细节）；
 * 不可变对象因为有固定不变，可以作为常量来安全使用。
 * Created by chen on 2018/2/24.
 */
public class TestImmutableCollection {

    @Test
    public void testImmutable() {
        List<String> list = new ArrayList<String>();
        list.add("a");
        list.add("b");
        list.add("c");
        System.out.println("list：" + list);
        // 用copyOf方法创建不可变集合, 譬如, ImmutableSet.copyOf(set)
        final ImmutableList<String> imList = ImmutableList.copyOf(list);
        System.out.println("imList" + imList);
        // 修改原始集合数据
        list.add("d");
        System.out.println("imList" + imList); // 不可变集合数据不变

        // 使用of方法创建
        ImmutableList<String> list2 = ImmutableList.of("aaa", "bbb", "ccc");
        ImmutableSortedSet<String> imSortList = ImmutableSortedSet.of("a", "b", "c", "a", "d", "b");
        System.out.println("imSortList：" + imSortList);

        // 使用builder方法创建
        ImmutableList<String> hello = ImmutableList.<String>builder()
                .add("hello")
                .add("world")
                .build();
        System.out.println(hello);
    }


}
