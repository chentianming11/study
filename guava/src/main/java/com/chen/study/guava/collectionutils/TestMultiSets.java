package com.chen.study.guava.collectionutils;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import org.junit.Test;

/**
 * Created by chen on 2018/2/26.
 */
public class TestMultiSets {

    @Test
    public void testMultiSets() {
        HashMultiset<String> sup = HashMultiset.create();
        sup.add("a", 5);
        sup.add("b", 2);
        sup.add("c", 1);
        sup.add("e", 7);
        sup.add("d", 1);

        HashMultiset<String> sub = HashMultiset.create();
        sub.add("a", 2);
        sub.add("b", 4);

        /**
         * 判断sup中是否包含sub
         * 对任意 o，如果 sub.count(o)<=super.count(o)，返回 true
         */
        System.out.println("判断sup中是否包含sub: " + Multisets.containsOccurrences(sup, sub));
        // 从sup中移除sub
//        boolean b = Multisets.removeOccurrences(sup, sub);
//        System.out.println("从sup中移除sub： " + sup);

        Multisets.retainOccurrences(sub, sup);
        System.out.println("修改sub以保证是sup的子集" + sub);

        // 取2个Multiset的交集
        Multiset<String> multiset = Multisets.intersection(sup, sub);
        System.out.println("取2个Multiset的交集" + multiset);

        // 返回MultiSet的不可变拷贝，并按次数降序
        ImmutableMultiset<String> immutableMultiset = Multisets.copyHighestCountFirst(sup);
        System.out.println("返回MultiSet的不可变拷贝，并按次数降序: " + immutableMultiset);

    }
}
