package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 构造
 *
 * @author 陈添明
 * @date 2018/11/18
 */
public class CompletableFutureExample2 {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        supplyAsync();
//        runAsync();
//        completed("hello");
//        anyOf();

        allof();

        Thread.currentThread().join();
    }


    private static void supplyAsync() {
        /**
         * supplyAsync(supplier)
         * 异步提交一个有返回值的任务
         */
        CompletableFuture.supplyAsync(Object::new)
                /**
                 * thenAcceptAsync(consumer)
                 * 异步消费上一个CompletableFuture的返回结果
                 */
                .thenAcceptAsync(o -> {
                    try {
                        System.out.println("obj start");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("obj end--" + o);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                })

                /**
                 * runAfterBoth(CompletableFuture, Runnable)
                 * 执行完两端的CompletableFuture之后，执行Runnable回调
                 */
                .runAfterBoth(CompletableFuture.supplyAsync(() -> "hello")
                                .thenAcceptAsync(s -> {
                                    try {
                                        System.out.println("string start");
                                        TimeUnit.SECONDS.sleep(2);
                                        System.out.println("string end -- " + s);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                })
                        , () -> System.out.println("===finished====="));

        System.out.println("----------------");


    }


    private static void runAsync() throws ExecutionException, InterruptedException {

        /**
         * runAsync(Runnable)
         * 异步执行一个无返回值的任务
         */
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            try {
                System.out.println(Thread.currentThread().getName() + ": obj start");
                TimeUnit.SECONDS.sleep(5);
                System.out.println(Thread.currentThread().getName() + ": obj end--");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            /**
             * whenComplete(BiConsumer)
             * 任务执行完成之后，回调BiConsumer
             */
        }).whenComplete((v, t) -> System.out.println(Thread.currentThread().getName() +  "----finished---------"));

//        System.out.println(future.get());
        System.out.println(Thread.currentThread().getName() + "============");
    }

    private static void completed(String data){
        /**
         * 以一个值作为CompletableFuture的起始
         */
        CompletableFuture<Void> future = CompletableFuture.completedFuture(data)
                // thenAccept 同步阻塞
                // thenAcceptAsync 异步非阻塞
                .thenAccept(s -> {
                    try {
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println(Thread.currentThread().getName() + ": " + s);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        System.out.println(future.isDone());
        System.out.println("-----------");
    }


    private static void anyOf() throws ExecutionException, InterruptedException {
        /**
         * anyOf(CompletableFuture list)
         * 返回执行最快任务的CompletableFuture，其他任务也会执行。
         */
        CompletableFuture<Object> future = CompletableFuture.anyOf(CompletableFuture.runAsync(() -> {
                    try {
                        System.out.println("1 == start");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("1 ==  end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).whenComplete((v, t) -> System.out.println(v + "======== 1 over ========")),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("2 == start");
                        TimeUnit.SECONDS.sleep(4);
                        System.out.println("2 ==  end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello";
                }).whenComplete((v, t) -> System.out.println(v + "======== 2 over ========"))

        );

        System.out.println("执行快的任务的返回值：" + future.get());


    }


    private static void allof() throws ExecutionException, InterruptedException {
        /**
         * allOf(CompletableFuture list)
         * 返回一个所有任务都执行完成的CompletableFuture
         */
        CompletableFuture<Void> future = CompletableFuture.allOf(CompletableFuture.runAsync(() -> {
                    try {
                        System.out.println("1 == start");
                        TimeUnit.SECONDS.sleep(5);
                        System.out.println("1 ==  end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).whenComplete((v, t) -> System.out.println(v + "======== 1 over ========")),
                CompletableFuture.supplyAsync(() -> {
                    try {
                        System.out.println("2 == start");
                        TimeUnit.SECONDS.sleep(4);
                        System.out.println("2 ==  end");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return "hello";
                }).whenComplete((v, t) -> System.out.println(v + "======== 2 over ========"))

        );

        /**
         * join(): 等待future中的任务执行结束
         */
        System.out.println("任务全部执行完成：" + future.join());
        System.out.println("-----------------");


    }
}
