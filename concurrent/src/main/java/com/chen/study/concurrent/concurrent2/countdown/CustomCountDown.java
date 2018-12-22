package com.chen.study.concurrent.concurrent2.countdown;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class CustomCountDown  {

    private static final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) throws InterruptedException {

        final CountDown countDown = new CountDown(5);

        System.out.println("准备多线程处理任务。");
        // 第一阶段 -- 多线程去处理任务
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(random.nextInt(1000));
                    System.out.println(Thread.currentThread().getName() + "工作完成。。。");
                    countDown.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "线程-" + i).start();
        }
        countDown.await();
        System.out.println("多线程任务全部结束，开始执行第二阶段");
        // 第二阶段 -- 所有线程都完成之后执行
        System.out.println("------------------");
        System.out.println("---结束———");
    }
}
