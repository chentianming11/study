package com.chen.study.demo.service;

/**
 * 我想实现，引用了这个jar包的应用自动装配DemoService这个Bean
 * 任何配置都不需要做！！！
 * @author 陈添明
 * @date 2019/11/2
 */
public class DemoService {
    public String say() {
        System.out.println("demo!!!");
        return "demo";
    }
}
