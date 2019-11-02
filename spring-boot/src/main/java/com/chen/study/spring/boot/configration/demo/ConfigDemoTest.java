package com.chen.study.spring.boot.configration.demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 陈添明
 * @date 2019/11/2
 */
public class ConfigDemoTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DemoConfig.class);
        Demo demo = context.getBean(Demo.class);
        demo.say();
    }
}
