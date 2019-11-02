package com.chen.study.spring.boot.starter.controller;

import com.chen.study.format.starter.HelloFormatTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 陈添明
 * @date 2019/11/2
 */
@RestController
public class TestController {


    @Autowired
    HelloFormatTemplate helloFormatTemplate;

    @GetMapping("/api/test/format")
    public String format() {
        User user = new User().setName("hello").setAge(10);
        return helloFormatTemplate.doFormat(user);
    }
}
