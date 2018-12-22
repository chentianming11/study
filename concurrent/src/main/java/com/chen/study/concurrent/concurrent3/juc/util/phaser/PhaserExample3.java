package com.chen.study.concurrent.concurrent3.juc.util.phaser;

import java.util.Random;
import java.util.concurrent.Phaser;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class PhaserExample3 {

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
        Phaser phaser = new Phaser(6);
        for (int i = 0; i < 5; i++) {
            new Athletes( i, phaser).start();
        }

        new InjuredAthletes(5, phaser).start();

    }

    /**
     * 受伤的运动员
     */
    static class InjuredAthletes extends Thread {
        private final int no;
        private final Phaser phaser;

        InjuredAthletes(int no, Phaser phaser) {
            this.no = no;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                sport(no + ": start running.", no + ": end running.");

                sport(no + ": start bicycle.", no + ": end bicycle.");

//                System.out.println("shit, I am injured...");
                System.out.println("shit, I am injured...我退赛");
                // 到达并且取消注册
                phaser.arriveAndDeregister();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void sport(String x, String x2) throws InterruptedException {
            System.out.println(x);
            Thread.sleep(random.nextInt(5_000));
            System.out.println(x2);
            // 到达并等待前行
            phaser.arriveAndAwaitAdvance();
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
                sport(no + ": start running.", no + ": end running.");

                sport(no + ": start bicycle.", no + ": end bicycle.");

                sport(no + ": start long jump.", no + ": end long jump.");

                System.out.println("运动全部完成");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void sport(String x, String x2) throws InterruptedException {
            System.out.println(x);
            Thread.sleep(random.nextInt(5_000));
            System.out.println(x2);
            // 到达并等待前行
            phaser.arriveAndAwaitAdvance();
        }
    }
}
