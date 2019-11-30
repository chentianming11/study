package com.chen.study.dubbo.order.service;

import com.chen.study.dubbo.pay.api.IPayService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext classPathXmlApplicationContext =
                new ClassPathXmlApplicationContext(new String[]{"application.xml"});

        IPayService payService = (IPayService) classPathXmlApplicationContext.
                getBean("payService");

        String rs = payService.pay("Test"); //pay方法一定是远程调用
        System.out.println(rs);
    }
}
