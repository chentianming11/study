package com.chen.study.concurrent.concurrent2.balking;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class CustomerThread  extends Thread{

    private BalkingData balkingData;

    private Random random = new Random(System.currentTimeMillis());

    public CustomerThread(BalkingData balkingData){
        super("customer");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            balkingData.change("No." + i);
            try {
                Thread.sleep(random.nextInt(1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
