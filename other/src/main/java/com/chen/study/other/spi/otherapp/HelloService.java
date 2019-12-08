package com.chen.study.other.spi.otherapp;

import com.chen.study.other.spi.IHelloService;

/**
 * 假设实现是在其它项目中的
 *
 * @author 陈添明
 * @date 2019/12/8
 */
public class HelloService implements IHelloService {

    @Override
    public String sayHello(String s) {
        String s1 = "hello: " + s;
        System.out.println(s1);
        return s1;
    }
}
