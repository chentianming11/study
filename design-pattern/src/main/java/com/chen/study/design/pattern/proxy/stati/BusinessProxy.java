package com.chen.study.design.pattern.proxy.stati;

import com.chen.study.design.pattern.proxy.BusinessInterface;

/**
 * 代理类，通过实现与目标对象相同的接口
 * 并维护一个代理对象，通过构造器传入实际目标对象并赋值
 * 执行代理对象实现的接口方法，实现对目标对象实现的干预
 * @author jiyukai
 */
public class BusinessProxy implements BusinessInterface {
     
    private BusinessInterface businessInterface;
     
    public BusinessProxy(BusinessInterface bussinessImpl) {
        this.businessInterface = bussinessImpl;
    }
     
    @Override
    public void execute() {
        System.out.println("前拦截...");
        businessInterface.execute();
        System.out.println("后拦截...");
    }
}