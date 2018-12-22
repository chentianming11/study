package com.chen.study.concurrent.concurrent3.executors;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/11/13
 */
public class ThreadPoolExecutorTask {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), r -> new Thread(r), new ThreadPoolExecutor.AbortPolicy());
        IntStream.range(0, 20).forEach(i -> {
            threadPoolExecutor.execute(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                    System.out.println(Thread.currentThread().getName() + "[" + i + "] finish.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        });

        /**
         * shutdown():
         *  调用前：
         *  20 thread
         *  10 thread 正在工作
         *  10 thread 空闲
         *
         *  调用shutdown()后
         *  1. 等待10个正在工作的线程完成任务
         *  2. 打断10个空闲线程
         *  3. 20个空闲线程将会退出
         *
         *
         *  shutdownNow()：
         *  调用前：
         *  20 thread
         *  10 thread 正在处理10个任务
         *  10 任务存在BlockingQueue等待处理
         *
         *  调用后：
         *  1. 中断线程池中所有的线程（对于正在处理任务的线程，任务处理完成中断生效，如果任务中途有会捕获InterruptedException，）
         *  2. 返回10个存在BlockingQueue等待处理的任务
         *
         */

//        // 关闭线程池 -- 等所有任务执行完成再关闭
//        // 方法不阻塞
//        threadPoolExecutor.shutdown();

//        // 等待线程池终结 -- 阻塞
//        threadPoolExecutor.awaitTermination(1,TimeUnit.HOURS);

        // 现在关闭：处理完正在执行的任务，返回在BlockingQueue中未处理的任务
        // 方法不阻塞
        List<Runnable> runnables = threadPoolExecutor.shutdownNow();
        System.out.println("立即关闭: " + runnables);

    }
}
