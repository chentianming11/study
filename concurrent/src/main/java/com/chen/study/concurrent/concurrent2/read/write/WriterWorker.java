package com.chen.study.concurrent.concurrent2.read.write;

import java.util.Random;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public class WriterWorker extends Thread {

    private static final Random random = new Random(System.currentTimeMillis());

    private final ShareData data;

    private final String filler;

    private int index = 0;

    public WriterWorker(ShareData data, String filler) {
        this.data = data;
        this.filler = filler;
    }


    @Override
    public void run() {
        try {
            while (true){
                char c = nextChar();
                data.write(c);
                Thread.sleep(random.nextInt(1000));
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private char nextChar(){
        char c = filler.charAt(index);
        index++;
        if (index >= filler.length()){
            index = 0;
        }

        return c;
    }
}
