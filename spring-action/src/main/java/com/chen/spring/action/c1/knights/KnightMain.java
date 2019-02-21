package com.chen.spring.action.c1.knights;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class KnightMain {
  public static void main(String[] args) throws Exception {
    // 使用xml配置，选择ClassPathXmlApplicationContext作为应用上下文
    ClassPathXmlApplicationContext context = 
        new ClassPathXmlApplicationContext(
            "spring/knight.xml");
    Knight knight = context.getBean(Knight.class);
    knight.embarkOnQuest();
    context.close();
  }
}
