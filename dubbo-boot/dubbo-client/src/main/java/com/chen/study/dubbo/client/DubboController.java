package com.chen.study.dubbo.client;

import com.chen.study.dubbo.api.ISayHelloService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈添明
 * @date 2019/12/7
 */
@RestController
@RequestMapping("/api/dubbo")
public class DubboController {

    @Reference
    private ISayHelloService sayHelloService;

    @GetMapping("/sayhello")
    public String sayHello() {
        return sayHelloService.sayHello(); //我调用这个服务可能失败，如果失败了，我要怎么处理
    }
}
