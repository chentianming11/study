package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/11/13
 */
public class ExecutorsExample1 {

    public static void main(String[] args) throws InterruptedException {
//        useCacheThreadPool();
//        useFixedThreadPool();

        useSinglePool();
    }

    /**
     * These pools will typically improve the performance of programs that
     * execute many short-lived asynchronous tasks
     * 这种线程池通常会提高执行许多短期异步任务的程序的性能
     * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
     *                          60L, TimeUnit.SECONDS,
     *                           new SynchronousQueue<Runnable>());
     *  SynchronousQueue: 同步阻塞队列  这个队列size只有1
     */
    private static void useCacheThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        System.out.println("活动的线程数：" + ((ThreadPoolExecutor)executorService).getActiveCount());
        executorService.execute(() -> System.out.println("==========="));
        System.out.println("活动的线程数：" + ((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "[" + i + "] finish.");
            });
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("活动的线程数：" + ((ThreadPoolExecutor)executorService).getActiveCount());

    }


    /**
     * ThreadPoolExecutor(nThreads, nThreads,
     *                      0L, TimeUnit.MILLISECONDS,
     *                      new LinkedBlockingQueue<Runnable>())
     *  LinkedBlockingQueue.size = Integer.MAX
     *  开辟固定数量的线程放在线程池中
     */
    private static void useFixedThreadPool() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        System.out.println("活动的线程数：" + ((ThreadPoolExecutor)executorService).getActiveCount());
        executorService.execute(() -> System.out.println("==========="));
        System.out.println("活动的线程数：" + ((ThreadPoolExecutor)executorService).getActiveCount());

        IntStream.range(0, 100).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "[" + i + "] finish.");
            });
        });
        TimeUnit.SECONDS.sleep(1);
        System.out.println("活动的线程数：" + ((ThreadPoolExecutor)executorService).getActiveCount());
    }


    /**
     * SingleThreadExecutor 和 new Thread之间的区别：
     * 1. Thread在执行完任务之后就会销毁，但是SingleThreadExecutor中的线程会一直存活
     * 2. Thread并不能将提交的任务缓存到队列中，singleThreadExecutor可以。
     *
     * new ThreadPoolExecutor(1, 1,
     *                          0L, TimeUnit.MILLISECONDS,
     *                          new LinkedBlockingQueue<Runnable>()))
     *  等价于  Executors.newFixedThreadPool(1)
     */
    private static void useSinglePool() throws InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        /**
         * 不能转换成ThreadPoolExecutor，因为不是ThreadPoolExecutor的实现。
         */
//        System.out.println("活动的线程数：" + ((ThreadPoolExecutor)executorService).getActiveCount());
        IntStream.range(0, 100).boxed().forEach(i -> {
            executorService.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "[" + i + "] finish.");
            });
        });
        TimeUnit.SECONDS.sleep(1);
    }
}
