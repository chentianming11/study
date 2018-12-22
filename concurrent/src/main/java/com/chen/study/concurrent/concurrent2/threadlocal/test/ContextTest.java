package com.chen.study.concurrent.concurrent2.threadlocal.test;

/**
 * @author 陈添明
 * @date 2018/9/23
 */
public class ContextTest {

    public static void main(String[] args) {

        ExecutionTask executionTask = new ExecutionTask();
        for (int i = 0; i < 5; i++) {

            new Thread(executionTask).start();

        }
    }
}
