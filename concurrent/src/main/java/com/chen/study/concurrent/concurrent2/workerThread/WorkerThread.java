package com.chen.study.concurrent.concurrent2.workerThread;

import java.util.Random;

/**
 * 装配工
 * @author 陈添明
 * @date 2018/9/26
 */
public class WorkerThread extends Thread {

    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public WorkerThread(String name, Channel channel) {
        super(name);
        this.channel = channel;

    }

    @Override
    public void run() {
        while (true){
            channel.take().execute();
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
