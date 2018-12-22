package com.chen.study.concurrent.concurrent3.executors;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈添明
 * @date 2018/11/18
 */
public class CompletableFutureExample3 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
//       whenComplete();
//        thenApply();
//        thenAccept();
//        handle();
        thenRun();

    }


    private static void whenComplete() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "hello")
                /**
                 * whenComplete(BiConsumer)
                 * 当CompletableFuture中的任务执行完成时，执行BiConsumer中的回调逻辑。
                 * 这是一个同步方法，whenCompleteAsync()则是一个异步方法。
                 */
                .whenCompleteAsync((s, t) -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(s + " finished.");
                });

        System.out.println("===============");
        System.out.println(future.get());
    }


    private static void thenApply() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> "hello")
                /**
                 * thenApply(Function)
                 * 将上一个CompletableFuture中任务的返回值进行转换处理
                 * 同步方法。异步方法--thenApplyAsync
                 */
                .thenApply(s -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( "转换完成");
                    return 10;
                });
        System.out.println("===============");
        System.out.println(future.get());
    }




    private static void handle() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() ->{
            System.out.println(1/0);
            return "hello";
        })
                /**
                 * handle(BiFunction)
                 * 对上一个CompletableFuture中任务的结果(返回值或者异常)进行处理, 结果转换成另一类型后返回future
                 * 同步方法。异步方法--handleAsync
                 */
                .handle((s, t) -> {
                    try {
                        TimeUnit.SECONDS.sleep(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( "处理完成");
                    if (t == null) {
                        return s.length();
                    } else {
                        System.out.println(t);
                        return -1;
                    }

                });
        System.out.println("===============");
        System.out.println(future.get());
    }



    private static void thenAccept() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> "hello")
                /**
                 * thenAccept(Consumer)
                 * 将上一个CompletableFuture中任务的返回值进行消费处理
                 * 同步方法。异步方法--thenAcceptAsync
                 */
                .thenAcceptAsync(s -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( "消费完成");

                });
        System.out.println("===============");
        System.out.println(future.get());
    }

    private static void thenRun() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> System.out.println("第一个任务执行完成"))
                /**
                 * thenAccept(Runnable)
                 * CompletableFuture中任务执行完成之后，接着执行下一个任务
                 * 同步方法。异步方法--thenAcceptAsync
                 */
                .thenRun(() -> {
                    try {
                        TimeUnit.SECONDS.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println( "第二个任务执行完成");
                });
        System.out.println("===============");
        System.out.println(future.get());
    }



}
