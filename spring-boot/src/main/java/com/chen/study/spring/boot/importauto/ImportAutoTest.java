package com.chen.study.spring.boot.importauto;

import com.chen.study.demo.service.DemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author 陈添明
 * @date 2019/11/2
 */
@SpringBootApplication
public class ImportAutoTest {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ImportAutoTest.class, args);
        System.out.println(context.getBean(DemoService.class));
    }
}
