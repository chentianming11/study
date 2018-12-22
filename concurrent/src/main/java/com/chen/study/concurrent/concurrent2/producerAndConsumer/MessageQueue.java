package com.chen.study.concurrent.concurrent2.producerAndConsumer;

import java.util.LinkedList;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class MessageQueue {

    private LinkedList<Message> queue;

    private final static int DEFALUT_MAX_LIMIT = 100;

    private final int limit;

    public MessageQueue(int limit) {
        this.queue = new LinkedList<>();
        this.limit = limit;
    }

    public MessageQueue(){
        this(DEFALUT_MAX_LIMIT);
    }

    public void put(Message message) throws InterruptedException {
        synchronized (queue){
            while (queue.size() > limit){
                queue.wait();
            }
            queue.addLast(message);
            queue.notifyAll();
        }
    }

    public Message take() throws InterruptedException {
        synchronized (queue){
            while (queue.isEmpty()){
                queue.wait();
            }
            Message message = queue.removeFirst();
            queue.notifyAll();
            return message;
        }
    }

    public int getLimit(){
        return this.limit;
    }

    public int getMessageSize(){
        synchronized (queue){
            return this.queue.size();
        }
    }
}
