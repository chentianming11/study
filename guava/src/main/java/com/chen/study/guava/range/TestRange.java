package com.chen.study.guava.range;

import com.google.common.collect.Range;
import org.junit.Test;

/**
 * Created by chen on 2018/2/26.
 */
public class TestRange {

    @Test
    public void test() {
        Range<Integer> range = Range.open(1, 10);
        System.out.println("开集" + range); // (1..10)

        System.out.println("闭集" + Range.closed(1, 10)); // [1..10]

        System.out.println("左闭右开" + Range.closedOpen(1, 10)); //[1..10)

        System.out.println("左开右闭" + Range.openClosed(1, 10)); //(1..10]

        System.out.println(Range.greaterThan(10)); // (10..+∞)

        System.out.println(Range.lessThan(10)); // (-∞..10)

        System.out.println(Range.atLeast(10)); // [10..+∞)

        System.out.println(Range.atMost(10)); //(-∞..10]

        System.out.println(Range.all()); // (-∞..+∞)

        System.out.println(range.contains(5));


    }
}
