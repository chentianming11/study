package com.chen.study.concurrent.concurrent2.suspension;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2018/9/22
 */
public class ClientThread extends Thread {

    private final RequestQueue requestQueue;

   private final Random random;

   private final String sendValue;


    public ClientThread(RequestQueue requestQueue, String sendValue) {
        this.requestQueue = requestQueue;
        this.random = new Random(System.currentTimeMillis());
        this.sendValue = sendValue;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            System.out.println("client -> request: " + sendValue);
            requestQueue.putRequest(new Request(sendValue));
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
