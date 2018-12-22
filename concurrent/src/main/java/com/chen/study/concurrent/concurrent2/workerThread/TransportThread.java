package com.chen.study.concurrent.concurrent2.workerThread;

import java.util.Random;

/**
 * 搬运工
 * @author 陈添明
 * @date 2018/9/26
 */
public class TransportThread extends Thread {

    private final Channel channel;

    private static final Random random = new Random(System.currentTimeMillis());

    public TransportThread(String name, Channel channel){
        super(name);
        this.channel = channel;
    }

    @Override
    public void run() {
        try {

            for (int i = 0;   true; i++) {
                Request request = new Request(getName(), i);
                this.channel.put(request);
                Thread.sleep(random.nextInt(1000));

            }
        }catch (Exception e){

        }
    }
}
