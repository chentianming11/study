package com.chen.study.concurrent.concurrent2.producerAndConsumer;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class ProducerThread  extends Thread{

    private final MessageQueue messageQueue;

    private final static Random random = new Random(System.currentTimeMillis());

    private final static AtomicInteger counter = new AtomicInteger(0);

    public ProducerThread(MessageQueue messageQueue, int seq){
        super("producer-" + seq);
        this.messageQueue = messageQueue;
    }

    @Override
    public void run() {
        while (true){
            try {
                Message message = new Message("message-" + counter.getAndIncrement());
                messageQueue.put(message);
                System.out.println(Thread.currentThread().getName() + "放置消息：" + message);
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
