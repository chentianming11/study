package com.chen.study.design.pattern.proxy;

/**
 * 目标对象实现类
 * @author jiyukai
 */
public class Business implements BusinessInterface {
 
    @Override
    public void execute() {
        System.out.println("执行业务逻辑...");
    }
}