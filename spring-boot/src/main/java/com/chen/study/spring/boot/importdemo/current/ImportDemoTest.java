package com.chen.study.spring.boot.importdemo.current;

import com.chen.study.spring.boot.importdemo.other.Other;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @author 陈添明
 * @date 2019/11/2
 */
public class ImportDemoTest {


    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(CurrentConfig.class);

        // 如果CurrentConfig没有导入其他配置，是无法获取的 other 这个bean的
        Other bean = context.getBean(Other.class);
        System.out.println(bean);
    }
}
