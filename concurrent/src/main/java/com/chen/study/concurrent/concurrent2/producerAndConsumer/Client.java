package com.chen.study.concurrent.concurrent2.producerAndConsumer;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class Client {

    public static void main(String[] args) {
        MessageQueue messageQueue = new MessageQueue();

        new ProducerThread(messageQueue, 1).start();
        new ProducerThread(messageQueue, 2).start();
        new ProducerThread(messageQueue, 3).start();
        new ProducerThread(messageQueue, 4).start();
        new ProducerThread(messageQueue, 5).start();

        new ConsumerThread(messageQueue, 1).start();
        new ConsumerThread(messageQueue, 2).start();
        new ConsumerThread(messageQueue, 3).start();
    }
}
