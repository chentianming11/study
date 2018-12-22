package com.chen.study.concurrent.concurrent2.active.objects;

/**
 * 接受异步消息的主动方法
 * @author 陈添明
 * @date 2018/10/1
 */
public interface ActiveObject {

    Result makeString(int count, char fillChar);

    void displayString(String text);
}
