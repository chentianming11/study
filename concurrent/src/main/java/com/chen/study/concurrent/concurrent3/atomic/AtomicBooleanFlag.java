package com.chen.study.concurrent.concurrent3.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 陈添明
 * @date 2018/11/4
 */
public class AtomicBooleanFlag {

    private final static AtomicBoolean flag = new AtomicBoolean(true);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (flag.get()){
                try {
                    Thread.sleep(1_000);
                    System.out.println("I am working...");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("I am finished!");
        }).start();

        Thread.sleep(5_000);

        flag.set(false);
    }
}
