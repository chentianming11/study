package com.chen.study.other.spi;

import java.util.ServiceLoader;

/**
 * @author 陈添明
 * @date 2019/12/8
 */
public class SPITest {

    public static void main(String[] args) {

        ServiceLoader<IHelloService> iHelloServices = ServiceLoader.load(IHelloService.class);
        for (IHelloService iHelloService : iHelloServices) {
            String test = iHelloService.sayHello("test");
            System.out.println(test);
        }
    }
}
