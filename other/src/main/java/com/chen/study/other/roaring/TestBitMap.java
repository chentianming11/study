package com.chen.study.other.roaring;

import org.junit.Test;
import org.roaringbitmap.IntConsumer;
import org.roaringbitmap.RoaringBitmap;
import org.roaringbitmap.buffer.ImmutableRoaringBitmap;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class TestBitMap {
    @Test
    public void test1() {
        // 向rr中添加1、2、3、1000四个数字
        RoaringBitmap rr = RoaringBitmap.bitmapOf(1, 2, 3, 1000);
        RoaringBitmap rr2 = new RoaringBitmap();
        //添加一个范围，前闭后开
        rr2.add(4000L, 4005L);

        System.out.println("bofore or, rr is: " + rr);
        System.out.println("bofore or, rr2 is: " + rr2);


        // 搜索第三个数值，索引从0开始  --thirdvalue is: 1000
        int thirdvalue = rr.select(3);
        System.out.println("thirdvalue is: " + thirdvalue);

        // rank是计算这个值的排序，排序从1开始  --rank is: 4
        int rank = rr.rank(1000);
        System.out.println("rank is: " + rank);

        // 是否包含1000和7  --c1 is: true , c2 is: false
        boolean c1 = rr.contains(1000);
        boolean c2 = rr.contains(7);
        System.out.println("c1 is: " + c1);
        System.out.println("c2 is: " + c2);
        System.out.println("=================================");

        // 做并集,生成新的bitmap，不会影响rr,rr2
        RoaringBitmap rror = RoaringBitmap.or(rr, rr2);

        // rr和rr2进行位运算,并将结果赋值给rr
        rr.or(rr2);

        System.out.println("rr is: " + rr);
        System.out.println("rr2 is: " + rr2);
        System.out.println("rror is: " + rror);

        boolean equals = rror.equals(rr);
        System.out.println("is equals: " + equals);

        // 获取位图中元素个数
        long cardinality = rr.getLongCardinality();
        System.out.println("cardinality is: " + cardinality);
        System.out.println("=================================");

        // 两种遍历方式
        for (int i : rr) {
            System.out.println(i);
        }

        System.out.println("===========================");

        rr.forEach((IntConsumer) i -> System.out.println(i));
    }
    @Test
    public void test2(){
        RoaringBitmap roaringBitmap = new RoaringBitmap();
        roaringBitmap.add(1L, 10L);

        // 遍历输出
        roaringBitmap.forEach((IntConsumer)i -> System.out.println(i));
        System.out.println("==========================================");


        // 遍历放入List中
        List<Integer> numbers = new ArrayList<>();
        roaringBitmap.forEach((IntConsumer) numbers::add);
        System.out.println(numbers);
        System.out.println("===========================================");

        //可能会开启 run-length 尝试优化 可以让生成的bitmap小一点
        roaringBitmap.runOptimize();

        int size = roaringBitmap.serializedSizeInBytes();

        ByteBuffer buffer = ByteBuffer.allocate(size);
        roaringBitmap.serialize(buffer);
        // 将RoaringBitmap的数据转成字节数组,这样就可以直接存入数据库了,数据库字段类型BLOB
        byte[] bitmapData = buffer.array();


        //使用byte[]数据
        ByteBuffer wrap = ByteBuffer.wrap(bitmapData);
        RoaringBitmap roaringBitmap1 = new RoaringBitmap(new ImmutableRoaringBitmap(wrap));
//        RoaringBitmap roaringBitmap1 = new RoaringBitmap();
//        try {
//            roaringBitmap1.deserialize(wrap);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        roaringBitmap1.forEach((IntConsumer)i -> System.out.println(i));
    }
}