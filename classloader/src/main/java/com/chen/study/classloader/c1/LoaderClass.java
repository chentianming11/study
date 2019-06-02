package com.chen.study.classloader.c1;

/**
 * @author 陈添明
 * @date 2018/10/13
 */
public class LoaderClass {

    public static void main(String[] args) {
        MyObject myObject1 = new MyObject();
        MyObject myObject2 = new MyObject();
        MyObject myObject3 = new MyObject();

        System.out.println(myObject1.getClass() == myObject2.getClass()); // true
        System.out.println(myObject1.getClass() == myObject3.getClass()); // true
    }
}

class MyObject {

}
