package com.chen.study.spring.boot.importDynamic;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 陈添明
 * @date 2019/11/2
 */
public class ImportDynamicTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(importDynamicConfig.class);
        System.out.println(context.getBean(CacheService.class));
        System.out.println(context.getBean(LoggerService.class));
    }
}
