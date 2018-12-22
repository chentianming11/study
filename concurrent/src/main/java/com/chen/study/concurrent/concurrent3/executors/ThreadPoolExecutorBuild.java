package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈添明
 * @date 2018/11/13
 */
public class ThreadPoolExecutorBuild {

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = buildThreadPoolExecutor();

        int activeCount = -1;
        int queueSize = -1;
        while (true){
            if (activeCount != threadPoolExecutor.getActiveCount() || queueSize != threadPoolExecutor.getQueue().size()){
                // 活跃的线程数 -- 即正在执行任务的线程数
                System.out.println("活跃的线程数：" + threadPoolExecutor.getActiveCount());
                // 核心线程数量
                System.out.println("核心线程数量：" + threadPoolExecutor.getCorePoolSize());
                // 线程数
                System.out.println("线程数：" + threadPoolExecutor.getPoolSize());
                // 工作队列（阻塞队列）
                System.out.println("工作队列：" + threadPoolExecutor.getQueue());
                // 最大线程数
                System.out.println("最大线程数：" + threadPoolExecutor.getMaximumPoolSize());

                activeCount = threadPoolExecutor.getActiveCount();
                queueSize = threadPoolExecutor.getQueue().size();
                System.out.println("=================分割线=================");
            }
        }
    }

    /**
     *   int corePoolSize, 核心线程数
         int maximumPoolSize, 最大线程数
         long keepAliveTime, 非核心线程空闲存活时间
         TimeUnit unit,
         BlockingQueue<Runnable> workQueue, 工作队列
         ThreadFactory threadFactory, 线程工厂
         RejectedExecutionHandler handler 拒绝策略
     */


    /**
     * 测试点：
     * 1. coreSize=1, maxSize=2,BlockingQueue.size=1，提交3个任务会怎么样？
     *      1.1 线程池何时会扩充线程？
     *          当BlockingQueue满了的时候，就会扩充线程去处理任务。
     *      1.2 拒绝策略何时生效？
     *          当执行任务的线程数达到最大值(max)并且BlockingQueue队列满了的情况下，就会触发拒绝策略执行。
     *      1.3 线程何时会进行回收
     *          当线程数超过核心线程数corePoolSize时，空闲时间超过keepAliveTime的线程就会被回收。
     * 2. coreSize=1, maxSize=2,BlockingQueue.size=5， 提交7个任务会怎么样？
     * 3. coreSize=1, maxSize=2,BlockingQueue.size=5， 提交8个任务会怎么样？
     */
    private static ThreadPoolExecutor buildThreadPoolExecutor(){
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 3,
                30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5),
                r -> new Thread(r), new ThreadPoolExecutor.AbortPolicy());
        System.out.println("线程池创建完毕");
        // 执行任务
        threadPoolExecutor.execute(() -> sleepSeconds(10));
        threadPoolExecutor.execute(() -> sleepSeconds(10));
        threadPoolExecutor.execute(() -> sleepSeconds(10));
        threadPoolExecutor.execute(() -> sleepSeconds(10));
        threadPoolExecutor.execute(() -> sleepSeconds(10));
        threadPoolExecutor.execute(() -> sleepSeconds(10));
        return threadPoolExecutor;
    }


    private static void sleepSeconds(long seconds){
        try {
            System.out.println("* " + Thread.currentThread().getName() + " *");
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
