package com.chen.study.java8.future;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;

/**
 * @author 陈添明
 * @date 2018/11/1
 */
public class CompeletableFutureTest {


    @Test
    public void test1(){
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f1完成");
            return "abc";
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f2完成");
            return "def";
        });

        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("f3完成");
            return "fgh";
        });

        CompletableFuture<Void> f = CompletableFuture.allOf(f1, f2, f3);
        System.out.println("阻塞开始");
        f.join();
        System.out.println("阻塞结束");



    }
}
