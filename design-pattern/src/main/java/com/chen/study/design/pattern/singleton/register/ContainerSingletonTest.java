package com.chen.study.design.pattern.singleton.register;

/**
 * @author 陈添明
 * @date 2019/3/30
 */
public class ContainerSingletonTest {

    public static void main(String[] args) throws IllegalAccessException {


        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
                POJO bean = ContainerSingleton.getBean(POJO.class);
                System.out.println(bean);
            }).start();
        }


    }
}
