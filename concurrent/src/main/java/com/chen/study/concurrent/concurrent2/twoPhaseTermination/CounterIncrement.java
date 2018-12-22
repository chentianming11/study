package com.chen.study.concurrent.concurrent2.twoPhaseTermination;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class CounterIncrement extends Thread {

    private volatile boolean terminated = false;

    private int counter = 0;

    private static Random random = new Random(System.currentTimeMillis());


    @Override
    public void run() {
        try {
            while (!terminated){

                Thread.sleep(random.nextInt(1000));
                System.out.println(Thread.currentThread().getName() + " : " + counter++);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            this.clean();
        }
    }

    public void close(){
        this.terminated = true;
        this.interrupt();
    }

    private void clean(){
        System.out.println("执行清理工作。。。当前的counter=" + counter);

    }
}
