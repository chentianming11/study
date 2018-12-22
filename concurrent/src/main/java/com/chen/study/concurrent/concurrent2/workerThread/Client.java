package com.chen.study.concurrent.concurrent2.workerThread;

/**
 * @author 陈添明
 * @date 2018/9/26
 */
public class Client {

    public static void main(String[] args) {
        Channel channel = new Channel(5);
        channel.startWorker();

        new TransportThread("chen", channel).start();
        new TransportThread("luo", channel).start();
        new TransportThread("liu", channel).start();
    }
}
