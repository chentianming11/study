package com.chen.study.concurrent.concurrent2.threadPremessage;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class Client {

    public static void main(String[] args) {
        MessageHandler handler = new MessageHandler();
        for (int i = 0; i < 10; i++) {
            handler.request(new Message(String.valueOf(i)));
        }

        handler.shutdown();
    }
}
