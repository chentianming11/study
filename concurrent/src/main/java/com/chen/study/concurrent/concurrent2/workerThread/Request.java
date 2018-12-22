package com.chen.study.concurrent.concurrent2.workerThread;

/**
 * 商品
 * @author 陈添明
 * @date 2018/9/26
 */
public class Request {

    private final String name;
    private final int number;

    public Request(String name, int number) {
        this.name = name;
        this.number = number;
    }

    public void execute(){
        System.out.println(Thread.currentThread().getName() + " 线程执行了 " + this);
    }

    @Override
    public String toString() {
        return "Request=> No." + number + "  Name:" + name;
    }
}
