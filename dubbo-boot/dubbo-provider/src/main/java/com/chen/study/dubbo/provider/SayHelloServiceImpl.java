package com.chen.study.dubbo.provider;

import com.chen.study.dubbo.api.ISayHelloService;
import org.apache.dubbo.config.annotation.Service;

/**
 * @author 陈添明
 * @date 2019/12/7
 */
@Service
public class SayHelloServiceImpl implements ISayHelloService {

    @Override
    public String sayHello() {
        System.out.println("Come in SayHello()");
        return "Hello Dubbo";
    }
}
