package com.chen.study.concurrent.concurrent3.juc.util.countdownlatch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class CountDownLatchExample1 {

    private static Random random = new Random(System.currentTimeMillis());

    private static ExecutorService executor = Executors.newFixedThreadPool(2);

    private static AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(10);

    private static final CountDownLatch latch = new CountDownLatch(10);

    public static void main(String[] args) throws InterruptedException {
        // 从DB中查询出10条数据
        int[] data = query();
        // 多线程去处理每条数据
        IntStream.range(0, data.length).forEach(i -> {
            executor.execute(() -> {
                int result = handleData(data[i]);
                System.out.println(Thread.currentThread().getName() + ": 数据处理完成" + data[i] + " -> " + result);
                atomicIntegerArray.set(i, result);
                latch.countDown();
            });
        });
        /**
         * 使用executor.shutdown() 和executor.awaitTermination实现
         */
        // shutdown方法不会阻塞。而是会给线程打上一个中断标记，任务处理完在将执行性器关闭
//        executor.shutdown();
//        // 等待执行器结束
//        executor.awaitTermination(10, TimeUnit.MINUTES);
//        // 数据处理完成，输出处理结果
//        System.out.println("数据处理完成：" + atomicIntegerArray);

        /**
         * 使用CountDownLatch
         */
        // 阻塞到latch的计数器为0为止
        latch.await();

        // 数据全部处理完成，在进行其他操作。
        System.out.println("数据处理完成：" + atomicIntegerArray);
    }

    /**
     * 假设从DB中查询出10条数据
     * @return
     */
    private static int[] query(){
        return new int[]{1,2,3,4,5,6,7,8,9,10};
    }

    private static int handleData(int i) {
        try {
            Thread.sleep(random.nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (i % 2 == 0){
            return i  * 2;
        }else  {
            return i * 10;
        }
    }
}
