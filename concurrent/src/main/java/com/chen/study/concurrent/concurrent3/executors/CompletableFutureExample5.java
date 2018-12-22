package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈添明
 * @date 2018/11/24
 */
public class CompletableFutureExample5 {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        getNow();

//        complete();

//        join();

        obtrudeException();

        Thread.currentThread().join();
    }




    /**
     * get的时候，直接抛出异常
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void obtrudeException() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            System.out.println("------------");
            return "hello";
        });

        future.obtrudeException(new Exception("出错啦"));
        future.get();
    }


    /**
     * join()：阻塞等待future中的结果返回，和get()类似，只是不会显示抛出异常
     */
    private static void join(){
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            System.out.println("------------");
            return "hello";
        });

        String s = future.join();
        System.out.println(s);
    }

    /**
     * complete()：完成future，并以给定的值作为future的结果。,并且会取消之前的任务
     * 如果future已经完成，此方法不起作用
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void complete() throws ExecutionException, InterruptedException {

        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            System.out.println("------------");
            return "hello";
        });

//        sleep(5);
        future.complete("world");

        System.out.println(future.get());

    }

    /**
     * getNow()：从future中立即获取值，如果没取到，则返回指定的默认值
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void getNow() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleep(3);
            return "hello";
        });


        System.out.println(future.getNow("不存在"));
        System.out.println(future.get());
        System.out.println(future.getNow("不存在"));

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
