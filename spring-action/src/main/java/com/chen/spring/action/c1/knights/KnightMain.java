package com.chen.spring.action.c1.knights;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class KnightMain {
    public static void main(String[] args) throws Exception {
        // 使用xml配置，选择ClassPathXmlApplicationContext作为应用上下文
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext(
                        "c1/spring/knight.xml");
        Knight knight = context.getBean(Knight.class);
        knight.embarkOnQuest();
        context.close();
    }

    private void test() {
        ApplicationContext context = new ClassPathXmlApplicationContext("c1/spring/knight.xml");
    }

    private void test1() {
        ApplicationContext context = new FileSystemXmlApplicationContext("d://knight.xml");
    }

    private void test2() {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.chen.spring.action.c1.knights.config");
    }

}
