package com.chen.study.concurrent.concurrent2.producerAndConsumer;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class ConsumerThread extends Thread{

    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());


    public ConsumerThread(MessageQueue messageQueue, int seq){
        super("consumer-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message message = messageQueue.take();
                System.out.println(Thread.currentThread().getName() + "获取消息：" + message);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
               break;
            }

        }
    }
}
