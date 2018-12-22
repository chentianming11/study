package com.chen.study.concurrent.concurrent2.waitset;

import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/9/9
 */
public class WaitSet {

    private static final Object LOCK = new Object();

    /**
     * 1. 所有的对象都会有一个wait set，用来存放调用了该对象wait方法之后进入block状态的线程
     * 2. 线程被notify之后，不一定立即得到执行
     * 3. 线程从wait set中被唤醒顺序是随机的
     * @param args
     */
    public static void main(String[] args) {

        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    new Thread(String.valueOf(i)){
                        @Override
                        public void run() {
                            synchronized (LOCK) {
                                try {
                                    System.out.println(Thread.currentThread().getName() + " 即将进入waitSet中");
                                    LOCK.wait();
                                    System.out.println(Thread.currentThread().getName() + " 即将离开waitSet中");
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    }.start();

                });


        IntStream.rangeClosed(1, 10)
                .forEach(i -> {
                    synchronized (LOCK){
                        LOCK.notify();
                        try {
                            Thread.sleep(1_000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }
}
