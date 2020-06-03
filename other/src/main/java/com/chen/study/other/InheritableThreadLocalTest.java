package com.chen.study.other;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 陈添明
 */
public class InheritableThreadLocalTest {


    static InheritableThreadLocal inheritableThreadLocal = new InheritableThreadLocal();

    static ExecutorService executorService = Executors.newFixedThreadPool(100);


    public static void main(String[] args) throws InterruptedException {


        inheritableThreadLocal.set(100);

        CountDownLatch countDownLatch = new CountDownLatch(1);

        Thread thread = new Thread(() -> {
            Object o = inheritableThreadLocal.get();
            System.out.println("子线程取值：" + o);
            countDownLatch.countDown();
        });
        thread.start();

        countDownLatch.await();


        CountDownLatch countDownLatch2 = new CountDownLatch(1);

        executorService.execute(() -> {
            Object o = inheritableThreadLocal.get();
            System.out.println("线程池取值：" + o);
            countDownLatch2.countDown();
        });
        countDownLatch2.await();

    }


}
