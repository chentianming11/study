package com.chen.study.concurrent.concurrent3.juc.util.condition;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class ComBetweenThreads {

    private static int data = 0;
    private static boolean noUse = true;

    private final static Object monitor = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            while (true){
                buildData();
            }
        }).start();

        new Thread(() -> {
            while (true){
                useData();
            }
        }).start();
    }

    private static void buildData() {
        synchronized (monitor){
            while (noUse){
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            data++;
            System.out.println("p => " + data);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            noUse = true;
            monitor.notifyAll();
        }
    }

    private static void useData(){
        synchronized (monitor){
            while (!noUse){
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            System.out.println("c => " + data);
            try {
                Thread.sleep(1_000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            noUse = false;
            monitor.notifyAll();
        }
    }
}
