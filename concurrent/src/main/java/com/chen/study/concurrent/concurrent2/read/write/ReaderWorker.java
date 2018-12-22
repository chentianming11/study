package com.chen.study.concurrent.concurrent2.read.write;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public class ReaderWorker extends Thread {
    private final ShareData data;


    public ReaderWorker(ShareData data) {
        this.data = data;
    }

    @Override
    public void run() {
        try {
            while (true){
                char[] readBuf = data.read();
                System.out.println(Thread.currentThread().getName() + " reads " + String.valueOf(readBuf));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
