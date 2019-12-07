package com.chen.study.dubbo.client;


import com.chen.study.dubbo.api.ISayHelloService;


public class SayHelloServiceMock implements ISayHelloService {
    @Override
    public String sayHello() {
        return "服务端发生异常， 被降解了。返回兜底数据。。。";
    }
}
