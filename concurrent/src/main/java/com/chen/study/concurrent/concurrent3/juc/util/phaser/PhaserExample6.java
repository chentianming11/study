package com.chen.study.concurrent.concurrent3.juc.util.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class PhaserExample6 {

    private static final Random random = new Random(System.currentTimeMillis());


    /**
     * AwaitAdvance(int): 如果传入的phase和阶段器所处的阶段相等，就会阻塞
     * 直到他们不相等，立即返回。
     *
     * 调用AwaitAdvance(int) 是否会减少已经到达的parties？
     * 答：不会
     *
     * @param args
     */
    public static void main(String[] args) throws InterruptedException {
        Phaser phaser = new Phaser(7);
//        new Thread(() -> phaser.awaitAdvance(0)).start();
//        Thread.sleep(1_000);
//        System.out.println(phaser.getArrivedParties());
//        phaser.awaitAdvance(phaser.getPhase());
//        phaser.awaitAdvance(10);
//        System.out.println("----------");

        for (int i = 0; i < 6; i++) {
            new AwaitAdvanceTask(phaser).start();
        }

        int phase = phaser.getPhase();
        System.out.println("phase => " + phase);
        phaser.awaitAdvance(phase);
        System.out.println("====================");
    }

    static class AwaitAdvanceTask extends Thread {
        private final Phaser phaser;

        AwaitAdvanceTask(Phaser phaser){
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(random.nextInt(5_000));
                System.out.println(getName() + " finished work. 进入等待");
//                phaser.arriveAndAwaitAdvance();
                phaser.arrive();
                System.out.println(getName() + " 等待结束，继续执行.");

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
