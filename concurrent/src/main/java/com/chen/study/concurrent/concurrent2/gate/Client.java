package com.chen.study.concurrent.concurrent2.gate;

/**
 * @author 陈添明
 * @date 2018/9/16
 */
public class Client {

    public static void main(String[] args) {
        Gate gate = new Gate();
        User bj = new User("baobao", "beijing", gate);
        User sh = new User("shanglao", "shanghai", gate);
        User gz = new User("guanglao", "guangzhou", gate);

        bj.start();
        sh.start();
        gz.start();

    }
}
