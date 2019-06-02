package com.chen.study.guava.primitive;


import com.google.common.collect.Lists;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试原生类型
 * Created by chen on 2018/2/27.
 */
public class TestPrimitive {

    @Test
    public void test1() {
        // 创建List<Integer>集合
        List<Integer> list = Ints.asList(1, 2, 3);
        // 将long转成int
        System.out.println(Ints.checkedCast(4L));
        // 比较2个int
        System.out.println(Ints.compare(3, 3));
        int[] ints1 = {1, 23, 3};
        int[] ints2 = {1, 4, 7};
        // 合并2个int[]
        System.out.println(Ints.concat(ints1, ints2));
        // 保证返回的值在区间中
        System.out.println(Ints.constrainToRange(0, 1, 6));
        // 保证数组容量，不足的话，填充指定值
        int[] ints = Ints.ensureCapacity(ints1, 5, 0);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
        // 判断数组中是否包含么某个值
        System.out.println(Ints.contains(ints, 1));
        // 获取指定值在数组中的索引
        System.out.println(Ints.indexOf(ints, 8));
        // 按执行分隔符将数组拼接成字符串
        System.out.println(Ints.join(",", 1, 2, 3));
        // 取数组最大值
        int max = Ints.max(1, 2, 3);
        // 反转数组
        Ints.reverse(ints);
        // 如果数值大于int最大值，直接返回最大值
        Ints.saturatedCast(23);
        ArrayList<Integer> integers = Lists.newArrayList(1, 2, 3, 4);
        // 将一个集合转换成int数组
        int[] ints3 = Ints.toArray(integers);
        // 将字符串转成Integer
        System.out.println(Ints.tryParse("10"));
    }
}
