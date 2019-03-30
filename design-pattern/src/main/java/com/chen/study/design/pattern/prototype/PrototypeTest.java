package com.chen.study.design.pattern.prototype;

import java.util.ArrayList;

/**
 * @author 陈添明
 * @date 2019/3/30
 */
public class PrototypeTest {

    public static void main(String[] args) throws CloneNotSupportedException {
        ConcretePrototypeA concretePrototypeA = new ConcretePrototypeA();
        concretePrototypeA.setAge(100);
        concretePrototypeA.setName("mike");
        ArrayList<String> list = new ArrayList<>();
        list.add("sport");
        list.add("music");
        concretePrototypeA.setHobbies(list);
        ConcretePrototypeA clone = (ConcretePrototypeA) concretePrototypeA.deepCloneWithJSON();

        System.out.println("concretePrototypeA: " + concretePrototypeA);
        System.out.println("clone: " + clone);

        System.out.println(concretePrototypeA.getHobbies());
        System.out.println(clone.getHobbies());
        System.out.println(concretePrototypeA.getHobbies() == clone.getHobbies());
    }
}
