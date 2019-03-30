package com.chen.study.design.pattern.prototype;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 具体对象A
 * @author 陈添明
 * @date 2019/3/30
 */
@Data
@Accessors
public class ConcretePrototypeB {

    private int count;
    private int age;
    private String name;
    private List<String> hobbies;


}
