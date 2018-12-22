package com.chen.study.concurrent.concurrent3.juc.util.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;
import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class PhaserExample1 {

    private static final Random random = new Random(System.currentTimeMillis());


    public static void main(String[] args) {
        Phaser phaser = new Phaser();
        IntStream.rangeClosed(1, 5).boxed().map(i -> phaser).forEach(Task::new);
        // 手动注册一个未到达的party到阶段器
        phaser.register();
        // 到达并等待前行
        phaser.arriveAndAwaitAdvance();

        System.out.println("all work are finished...");

    }

    static class Task extends Thread {
        private final Phaser phaser;

        Task(Phaser phaser){
            this.phaser = phaser;
            phaser.register();
            start();
        }

        @Override
        public void run() {
            System.out.println("the worker [" +  getName() +"] is working...");
            try {
                Thread.sleep(random.nextInt(5_000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            phaser.arriveAndAwaitAdvance();
        }
    }
}
