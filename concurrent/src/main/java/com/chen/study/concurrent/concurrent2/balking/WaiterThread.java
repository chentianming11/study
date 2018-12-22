package com.chen.study.concurrent.concurrent2.balking;

import java.io.IOException;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class WaiterThread extends Thread{

    private BalkingData balkingData;


    public WaiterThread(BalkingData balkingData){
        super("waiter");
        this.balkingData = balkingData;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                balkingData.save();
                Thread.sleep(1000);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
