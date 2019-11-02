package com.chen.study.spring.boot.starter.controller;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 陈添明
 * @date 2019/11/2
 */
@Data
@Accessors(chain = true)
public class User {

    private String name;

    private Integer age;
}
