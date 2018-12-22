package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 拒绝策略
 * Created by chen on 2018/11/13.
 */
public class ExecutorServiceExample2 {

    public static void main(String[] args) throws InterruptedException {
//        testAbortPolicy();

//        testDiscardPolicy();
//        testCallerRunsPolicy();

        testDiscardOldestPolicy();


    }


    /**
     * 放弃并通知 抛出RejectedExecutionException
     */
    private static void testAbortPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1),
                r -> new Thread(r), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(100_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(1_000);

        threadPoolExecutor.execute(() -> {
            System.out.println("x");
        });
    }

    /**
     * 并直接放弃
     */
    public static void testDiscardPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1),
                r -> new Thread(r), new ThreadPoolExecutor.DiscardPolicy());
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(100_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(1_000);

        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": x");
        });

        System.out.println("----------------");
    }


    /**
     *  由 [调用方线程] 直接执行任务的run方法
     */
    private static void testCallerRunsPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1),
                r -> new Thread(r), new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    Thread.sleep(100_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(1_000);

        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": x");
        });

        System.out.println("----------------");
    }

    /**
     * 直接放弃阻塞队列中最老的任务，将新任务加入队列
     * @throws InterruptedException
     */
    private static void testDiscardOldestPolicy() throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 2,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(1),
                r -> new Thread(r), new ThreadPoolExecutor.DiscardOldestPolicy());
        for (int i = 0; i < 3; i++) {
            threadPoolExecutor.execute(() -> {
                try {
                    System.out.println("按道理这句话应该输出3遍");
                    Thread.sleep(5_000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        Thread.sleep(1_000);

        threadPoolExecutor.execute(() -> {
            System.out.println(Thread.currentThread().getName() + ": x");
        });
        System.out.println("----------------");
    }


}
