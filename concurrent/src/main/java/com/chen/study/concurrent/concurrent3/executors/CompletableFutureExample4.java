package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * Created by chen on 2018/11/24.
 */
public class CompletableFutureExample4 {


    public static void main(String[] args) throws InterruptedException {
//        thenAcceptBoth();
//        thenAcceptEither();

//        runAfterBoth();

//        combine();

        compose();

    }


    private static void compose(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始异步提交任务 -> supplyAsync ");
            sleep(5);
            System.out.println("结束异步提交任务 -> supplyAsync");
            return "compose";
        }).thenCompose(s -> CompletableFuture.supplyAsync(() -> {
            System.out.println("开始异步提交任务 -> supplyAsync ");
            sleep(2);
            System.out.println("结束异步提交任务 -> supplyAsync");
            return s + 102;
        }));

        future.thenAccept(s -> System.out.println(s));
        System.out.println("------------");


        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


    /**
     * 异步提交2个有返回值的任务，组合2个任务的返回值作为新的结果
     */
    private static void combine(){
        CompletableFuture<Boolean> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始异步提交任务 -> supplyAsync ");
            sleep(5);
            System.out.println("结束异步提交任务 -> supplyAsync");
            return "supplyAsync";
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            System.out.println("开始异步提交任务2 -> supplyAsync ");
            sleep(5);
            System.out.println("结束异步提交任务2 -> supplyAsync");
            return 100;
        }), (s, i) -> s.length() > i);

        future.whenComplete((v, t) -> System.out.println(v));

        System.out.println("--------");

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 异步提交2个任务，等2个任务都执行完成再执行回调
     */
    private static void runAfterBoth(){
        CompletableFuture.runAsync(() -> {
            System.out.println("开始异步执行任务1");
            sleep(5);
            System.out.println("结束异步执行任务1");
        }).runAfterBoth(CompletableFuture.runAsync(() -> {
            System.out.println("开始异步执行任务2");
            sleep(2);
            System.out.println("结束异步执行任务2");
        }), () -> {
            System.out.println("2个任务都执行完成了。。。");
        });

        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    /**
     * 异步提交2个有返回值的任务，返回值类型必须一致。
     * 回调处理先执行完成任务的返回值，但是2个任务都会去执行。
     */
    private static void thenAcceptEither(){
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始异步提交任务 -> supplyAsync ");
            sleep(5);
            System.out.println("结束异步提交任务 -> supplyAsync");
            return "thenAcceptEither";
        }).acceptEither(CompletableFuture.supplyAsync(() -> {
            System.out.println("开始异步提交任务 -> supplyAsync ");
            sleep(2);
            System.out.println("结束异步提交任务 -> supplyAsync");
            return "thenAcceptEither";
        }), s -> System.out.println("先执行完成的结果：" + s));
        future.join();
        System.out.println("--------------");
    }


    /**
     * 异步提交有2个返回值的任务，(2个任务的返回值类型可以不同)
     * 等2个都处理完成后，再执行回调处理2个任务的结果
     */
    private static void thenAcceptBoth(){
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("开始异步提交任务 -> supplyAsync ");
            sleep(5);
            System.out.println("结束异步提交任务 -> supplyAsync");
            return "thenAcceptBoth";
        }).thenAcceptBoth(CompletableFuture.supplyAsync(() -> {
            System.out.println("开始异步提交任务 -> thenAcceptBoth");
            sleep(2);
            System.out.println("结束异步提交任务 -> thenAcceptBoth");
            return 100;
        }), (s, i) -> System.out.println(s + "-----" + i));
        future.join();
        System.out.println("--------------");
    }


    /**
     * sleep
     * @param s
     */
    private static void sleep(int s)  {
        try {
            TimeUnit.SECONDS.sleep(s);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
