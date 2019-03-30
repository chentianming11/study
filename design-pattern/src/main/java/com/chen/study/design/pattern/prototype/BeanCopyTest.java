package com.chen.study.design.pattern.prototype;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * @author 陈添明
 * @date 2019/3/30
 */
public class BeanCopyTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        ConcretePrototypeA concretePrototypeA = new ConcretePrototypeA();
        concretePrototypeA.setAge(100);
        concretePrototypeA.setName("mike");
        ArrayList<String> list = new ArrayList<>();
        list.add("sport");
        list.add("music");
        concretePrototypeA.setHobbies(list);


        ConcretePrototypeB concretePrototypeB = new ConcretePrototypeB();

        BeanUtils.copyProperties(concretePrototypeB, concretePrototypeA);

        System.out.println(concretePrototypeB);
    }
}
