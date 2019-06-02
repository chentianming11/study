package com.chen.study.classloader.c1;

/**
 * @author 陈添明
 * @date 2018/10/13
 */
public class ActiveUseClass {

    public static void main(String[] args) {
        new Obj();
    }
}

class Obj {
    static {
        System.out.println("obj 类被初始化了");
    }

}

