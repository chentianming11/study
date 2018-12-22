package com.chen.study.concurrent.concurrent2.volatileTest;

/**
 * @author 陈添明
 * @date 2018/9/9
 */
public class VolatileTest {

    private static int INIT_VALUE = 0;
    private static int MAX_LIMIT = 5;

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT){
                if (localValue != INIT_VALUE){
                    System.out.printf("数值更新到到[%d]\n", INIT_VALUE);
                    localValue = INIT_VALUE;
                }
            }
        }, "reader").start();


        Thread.sleep(100);


        new Thread(() -> {
            int localValue = INIT_VALUE;
            while (localValue < MAX_LIMIT){
                INIT_VALUE = ++localValue;
                System.out.printf("更新数值到[%d]\n", INIT_VALUE);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "updater").start();

    }
}
