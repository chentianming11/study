package com.chen.study.concurrent.concurrent3.juc.util.phaser;

import java.util.concurrent.Phaser;

/**
 * @author 陈添明
 * @date 2018/11/11
 */
public class PhaserExample4 {

    public static void main(String[] args) throws InterruptedException {
//        Phaser phaser = new Phaser(1);

        /**
         * phaser.getPhase()
         * 获取当前进行到的阶段  从0开始
         */
//        System.out.println(phaser.getPhase());
//        phaser.arriveAndAwaitAdvance();
//
//        System.out.println(phaser.getPhase());
//        phaser.arriveAndAwaitAdvance();
//
//        System.out.println(phaser.getPhase());
//        phaser.arriveAndAwaitAdvance();

        /**
         * phaser.getRegisteredParties()
         * 获取注册的parties数量
         *
         * phaser.getArrivedParties()
         * 获取已经到达的parties的数量
         *
         * phaser.getUnarrivedParties()
         * 获取没有到达的parties的数量
         */
//        System.out.println(phaser.getRegisteredParties());
//
//        phaser.register();
//        System.out.println(phaser.getRegisteredParties());
//
//        System.out.println(phaser.getArrivedParties());
//        System.out.println(phaser.getUnarrivedParties());

        /**
         *  phaser.bulkRegister(10);
         *  一次性注册10个parties
         */
//        phaser.bulkRegister(10);
//        System.out.println(phaser.getRegisteredParties());
//        System.out.println(phaser.getArrivedParties());
//        System.out.println(phaser.getUnarrivedParties());
//        new Thread(phaser::arriveAndAwaitAdvance).start();
//        Thread.sleep(1_000);
//        System.out.println("===========================");
//        System.out.println(phaser.getRegisteredParties());
//        System.out.println(phaser.getArrivedParties());
//        System.out.println(phaser.getUnarrivedParties());


        Phaser phaser = new Phaser(2) {
            @Override
            protected boolean onAdvance(int phase, int registeredParties) {
                return false;
            }
        };

        new OnAdvanceTask("chen", phaser).start();
        new OnAdvanceTask("wang", phaser).start();

        Thread.sleep(5_000);

        System.out.println(phaser.getUnarrivedParties());
        System.out.println(phaser.getArrivedParties());

    }

    static class OnAdvanceTask extends Thread {
        private final Phaser phaser;

        OnAdvanceTask(String name, Phaser phaser) {
            super(name);
            this.phaser = phaser;
        }

        @Override
        public void run() {
            try {
                System.out.println(getName() + " 任务一 : I am start and the phase = " + phaser.getPhase());
                Thread.sleep(1_000);
                System.out.println(getName() + " 任务一 : I am end. ");
                phaser.arriveAndAwaitAdvance();

                if ("chen".equals(getName())){
                    System.out.println(getName() + " 任务二 : I am start and the phase = " + phaser.getPhase());
                    Thread.sleep(1_000);
                    System.out.println(getName() + " 任务二 : I am end. ");
                    phaser.arriveAndAwaitAdvance();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }
    }
}
