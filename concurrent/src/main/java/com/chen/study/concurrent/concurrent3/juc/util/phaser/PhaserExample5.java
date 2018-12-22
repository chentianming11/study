package com.chen.study.concurrent.concurrent3.juc.util.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class PhaserExample5 {

    private static final Random random = new Random(System.currentTimeMillis());

    // arrive
    public static void main(String[] args) throws InterruptedException {
//        Phaser phaser = new Phaser(3);
        /**
         * arrive
         * 到达不等待
         */
//        new Thread(phaser::arrive).start();
//
//        Thread.sleep(1_000);
//        System.out.println("-----");

        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 4; i++) {
            new ArriveTask(phaser, i).start();
        }

        phaser.arriveAndAwaitAdvance();
        System.out.println("所有线程都完成了阶段一任务。。。。");


    }


    private static class ArriveTask extends Thread {
        private final Phaser phaser;

        ArriveTask(Phaser phaser, int no){
            super(String.valueOf(no));
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println(getName() + " start working.");
                Thread.sleep(random.nextInt(5_000));
                System.out.println(getName() + " the phase one finish.");
                phaser.arrive();
                Thread.sleep(random.nextInt(5_000));
                System.out.println(getName() + " end working.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
