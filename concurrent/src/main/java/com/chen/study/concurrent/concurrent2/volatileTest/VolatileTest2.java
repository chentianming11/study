package com.chen.study.concurrent.concurrent2.volatileTest;

/**
 * volatile使用：
 * 1. 标志位
 * 2. 状态判断
 * @author 陈添明
 * @date 2018/9/9
 */
public class VolatileTest2 {

    private static int INIT_VALUE = 0;
    private static int MAX_LIMIT = 1000;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT){
                System.out.println("t1 -> " + (++ INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "adder1").start();


        Thread.sleep(100);


        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT){
                System.out.println("t2 -> " + (++ INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "adder2").start();



        new Thread(() -> {
            while (INIT_VALUE < MAX_LIMIT){
                System.out.println("t3 -> " + (++ INIT_VALUE));
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "adder3").start();



    }
}
