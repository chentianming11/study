package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.*;

/**
 * @author 陈添明
 * @date 2018/11/18
 */
public class FutureExample1 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
//        testGet();
        testGetTimeout();
    }


    /**
     * future.get(): 阻塞方法，直到有返回结果
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testGet() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return 10;
        });

        // 做一些其它事情
        System.out.println("=====可以马上做其他事情===");

        Integer result = future.get();
        System.out.println(result);

    }

    /**
     * future.get(timeout): 阻塞方法，等待指定时间，超时抛出TimeoutException。
     *  注意：即使get等待超时，原任务还是一样执行。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void testGetTimeout() throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        Future<Integer> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("执行结果：" + 10);
            return 10;
        });

        // 做一些其它事情
        System.out.println("=====可以马上做其他事情===");

        Integer result = future.get(5, TimeUnit.SECONDS);
        System.out.println(result);

    }


}
