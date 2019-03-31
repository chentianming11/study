package com.chen.study.design.pattern.proxy.custom;

import java.lang.reflect.Method;

/**
 * 自定义调用处理器
 * @author 陈添明
 * @date 2019/3/31
 */
public interface CustomInvocationHandler {

    Object invoke(Object proxy, Method method, Object[] args)
            throws Throwable;
}
