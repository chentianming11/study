package com.chen.study.concurrent.concurrent3.executors;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/11/17
 */
public class ExecutorServiceExample4 {

    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {

//        testInvokeAny();
//        testInvokeAnyTimeout();
//        testInvokeAll();
//        testSubmitRunnable();
        testSubmitRunnableWithResult();
    }


    /**
     * invokeAny：调用任务列表中的任意一个任务，并返回结果。该方法会阻塞；其他没完成的任务会被取消。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testInvokeAny() throws ExecutionException, InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 定义多个有返回值的任务
        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed()
                .map(i -> (Callable<Integer>) () -> {
                    System.out.println(Thread.currentThread().getName() + "执行开始");
                    Thread.sleep(ThreadLocalRandom.current().nextInt(20_000));
                    System.out.println(Thread.currentThread().getName() + "执行结束，结果是" + i);
                    return i;
                }).collect(Collectors.toList());

        // 调用其中的任何任务，并返回值
        Integer integer = executorService.invokeAny(callableList);
        System.out.println("==============");
        System.out.println(integer);


    }

    /**
     * invokeAnyTimeout：可以设置超时时间
     * 如果超过超时时间，还没有一个任务结束，该方法就会抛出TimeoutException，并且取消所有在执行的任务。
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testInvokeAnyTimeout() throws ExecutionException, InterruptedException, TimeoutException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 定义多个有返回值的任务
        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed()
                .map(i -> (Callable<Integer>) () -> {

                    Thread.sleep(ThreadLocalRandom.current().nextInt(30_000));
                    System.out.println(Thread.currentThread().getName() + "执行了，结果是" + i);
                    return i;
                }).collect(Collectors.toList());

        // 调用其中的任何任务，并返回值
        Integer integer = executorService.invokeAny(callableList, 3, TimeUnit.SECONDS);
        System.out.println("==============");
        System.out.println(integer);


    }


    /**
     * invokeAll: 执行所有任务，并返回futureList，该方法会阻塞到所有任务执行完成。
     *
     * @throws InterruptedException
     */
    private static void testInvokeAll() throws InterruptedException {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        // 定义多个有返回值的任务
        List<Callable<Integer>> callableList = IntStream.range(0, 5).boxed()
                .map(i -> (Callable<Integer>) () -> {

                    Thread.sleep(ThreadLocalRandom.current().nextInt(5_000));
                    System.out.println(Thread.currentThread().getName() + "执行了，结果是" + i);
                    return i;
                }).collect(Collectors.toList());

        // 调用其中的任何任务，并返回值
        List<Future<Integer>> futureList = executorService.invokeAll(callableList);
        System.out.println("==============");
        futureList.stream()
                .map(future -> {
                    try {
                        return future.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        throw new RuntimeException();
                    }
                }).forEach(System.out::println);
    }


    /**
     * submit(): 异步提交一个任务
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testSubmitRunnable() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        // future.get()阻塞到任务完成
        Object o = future.get();
        System.out.println(o);
    }


    /**
     * submit(runnable, result): 异步提交一个任务, 并传入结果的引用。
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void testSubmitRunnableWithResult() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        String result = "DONE";
        Future<?> future = executorService.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
                System.out.println("执行完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, result);
        // future.get()阻塞到任务完成
        Object o = future.get();
        System.out.println(o);
    }
}
