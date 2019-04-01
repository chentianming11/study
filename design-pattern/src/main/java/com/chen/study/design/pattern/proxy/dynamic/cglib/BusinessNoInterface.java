package com.chen.study.design.pattern.proxy.dynamic.cglib;

/**
 * 目标对象实现类
 * @author jiyukai
 */
public class BusinessNoInterface {

    public void execute() {
        System.out.println("执行业务逻辑...");
    }
}