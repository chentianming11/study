package com.chen.study.concurrent.concurrent3.executors;

import org.junit.Test;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/11/13
 */
public class ExecutorsExample2 {

    public static void main(String[] args) throws InterruptedException {

        /**
         * 根据cpu核心数确定启动的线程数
         * 完成任务后，会自动结束
         */
        ExecutorService executorService = Executors.newWorkStealingPool();
        List<Callable<String>> callableList = IntStream.range(0, 20).boxed().map(i -> (Callable<String>) () -> {
            System.out.println(Thread.currentThread().getName() + "[" + i + "] finish.");
            sleep(2);
            return "Task-" + i;
        }).collect(Collectors.toList());
        executorService.invokeAll(callableList).stream()
                .map(stringFuture -> {
                    try {
                        return stringFuture.get();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                })
                .forEach(System.out::println);
    }


    private static void sleep(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testRuntime(){
        // cpu核心数
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

}
