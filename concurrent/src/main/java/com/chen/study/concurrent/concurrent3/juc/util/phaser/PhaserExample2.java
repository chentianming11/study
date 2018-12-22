package com.chen.study.concurrent.concurrent3.juc.util.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class PhaserExample2 {

    private static final Random random = new Random(System.currentTimeMillis());

    /**
     * 跑步 running
     * <p>
     * 骑车 bicycle
     * <p>
     * 跳远 long jump
     *
     * @param args
     */
    public static void main(String[] args) {

        /**
         * 5个运动员：
         * 同时开始跑步，所有人跑步完成，才可以进行骑车，骑车完成才可以跳远
         */
        Phaser phaser = new Phaser(5);
        for (int i = 0; i < 5; i++) {
            new Athletes( i, phaser).start();
        }

    }

    /**
     * 运动员
     */
    static class Athletes extends Thread {
        private final int no;
        private final Phaser phaser;

        Athletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println(no + ": start running.");
                Thread.sleep(random.nextInt(5_000));
                System.out.println(no + ": end running.");
                System.out.println( "phaser => " + phaser.getPhase());
                // 到达并等待前行
                phaser.arriveAndAwaitAdvance();

                System.out.println(no + ": start bicycle.");
                Thread.sleep(random.nextInt(5_000));
                System.out.println(no + ": end bicycle.");
                System.out.println( "phaser => " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();


                System.out.println(no + ": start long jump.");
                Thread.sleep(random.nextInt(5_000));
                System.out.println(no + ": end long jump.");
                System.out.println( "phaser => " + phaser.getPhase());
                phaser.arriveAndAwaitAdvance();

                System.out.println("运动全部完成");



            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
