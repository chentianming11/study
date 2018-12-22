package com.chen.study.concurrent.concurrent2.suspension;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2018/9/22
 */
public class ServerThread extends Thread {

    private final RequestQueue queue;
    private final Random random;

    private volatile boolean flag = true;

    public ServerThread(RequestQueue queue){
        this.queue = queue;
        this.random = new Random(System.currentTimeMillis());
    }


    public void close(){
        this.flag = false;
        this.interrupt();
    }

    @Override
    public void run() {
        while (flag){
            Request request = queue.getRequest();
            if (request == null) {
                System.out.println("接收请求为null");
                continue;
            }
            System.out.println("Server -> " + request.getValue());
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
               return;
            }
        }
    }
}
