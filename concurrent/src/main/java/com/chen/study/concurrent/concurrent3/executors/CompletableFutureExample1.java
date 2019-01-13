package com.chen.study.concurrent.concurrent3.executors;

import org.junit.Test;

import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 * 简单使用
 * @author 陈添明
 * @date 2018/11/18
 */
public class CompletableFutureExample1 {


    @Test
    public void test() throws InterruptedException {
        /**
         * runAsync(): 异步执行一个任务
         */
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * whenComplete(): 任务执行完成的回调
             */
        }).whenComplete(((aVoid, throwable) -> System.out.println("执行完成")));

        System.out.println("===========");

        Thread.currentThread().join();
    }


    public static void main(String[] args) throws InterruptedException {

        /**
         * ExecutorService传统方式，需要等待所有task执行完成，再开始并行进行display
         */
      /*  ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 多个有返回值的task
        List<Callable<Integer>> tasks = IntStream.range(0, 10).boxed()
                .map(i -> (Callable<Integer>) () -> get())
                .collect(Collectors.toList());
        executorService.invokeAll(tasks).stream()
                .map(integerFuture -> {
                    try {
                        return integerFuture.get();
                    } catch (Exception e) {
                        throw new RuntimeException();
                    }
                }).parallel().forEach(integer -> display(integer));*/

       IntStream.range(0, 10).boxed()
                .forEach(i -> CompletableFuture
                        .supplyAsync(() -> get()) // 提交一个异步任务
                        .thenAccept((data) -> display(data)) // 任务完成，处理结果
                        .whenComplete((v, t) -> System.out.println( i + " -> done")) // 每个任务执行完成的回调
                        .join()
                );

        System.out.println("全部执行完成");
       Thread.currentThread().join();


    }

    /**
     * 展示数据
     * @param data
     */
    private static void display(int data){
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + "：开始打印数据，预计" + value + "s完成");
            TimeUnit.SECONDS.sleep(value);
            System.out.println(Thread.currentThread().getName() + "：输出，data=" + data);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取数据
     * @return
     */
    private static int get(){
        int value = ThreadLocalRandom.current().nextInt(20);
        try {
            System.out.println(Thread.currentThread().getName() + "：开始获取数据，预计" + value + "s完成");
            TimeUnit.SECONDS.sleep(value);
            System.out.println(Thread.currentThread().getName() + "：执行完成，value=" + value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return value;
    }
}
