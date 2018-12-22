package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/11/13
 */
public class ThreadPoolExecutorLongTimeTask {

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10, 20, 30, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(10), r -> {
            Thread thread = new Thread(r);
            thread.setDaemon(true);  // 启用守护线程
            return thread;
        }, new ThreadPoolExecutor.AbortPolicy());

        /**
         * 并行执行10个task
         * 假设task任务执行时间很长，这里直接用while true
         */
        IntStream.range(0, 10).boxed().forEach(i -> {
            threadPoolExecutor.submit(() -> {
                while (true){

                }
            });
        });

        /**
         * 假设10个并行任务正常情况下最多5s内全部执行完成
         * 如果超时，则停止执行
         */
        threadPoolExecutor.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("执行完成");

    }
}
