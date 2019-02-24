package com.chen.spring.action.c4.aop;

import org.springframework.stereotype.Component;

/**
 * @author 陈添明
 * @date 2019/2/24
 */
@Component
public class DefaultPermance implements Performance {

    @Override
    public void perform() {
        System.out.println("开始表演");
    }
}
