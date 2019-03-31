package com.chen.study.design.pattern.proxy.custom;

import com.chen.study.design.pattern.proxy.Business;
import com.chen.study.design.pattern.proxy.BusinessInterface;

/**
 * @author 陈添明
 * @date 2019/3/31
 */
public class CutomDynamicTest {
    public static void main(String[] args) throws Exception {
        Business targetObject = new Business();
        // 不需要编写代理类，动态创建代理对象
        BusinessInterface proxyInstance = (BusinessInterface) CustomProxy.newProxyInstance(new CustomClassLoader(),
                targetObject.getClass().getInterfaces(),
                (proxy, method, args1) -> {
                    System.out.println("前置处理！");
                    Object o = method.invoke(targetObject, args1);
                    System.out.println("后置处理！");
                    return o;
                });
        proxyInstance.execute();
        System.out.println("-----------");
    }
}
