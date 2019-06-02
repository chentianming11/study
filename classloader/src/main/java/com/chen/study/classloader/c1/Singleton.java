package com.chen.study.classloader.c1;

/**
 * @author 陈添明
 * @date 2018/10/13
 */
public class Singleton {

    private static Singleton singleton = new Singleton();  // 0  1

    private static int x = 0;

    private static int y ;

//    private static Singleton singleton = new Singleton();  1  1


    private Singleton(){
        x++;
        y++;
    }

    public static Singleton getInstance(){
        return singleton;
    }

    public static void main(String[] args) {
        Singleton instance = getInstance();
        System.out.println(Singleton.x);
        System.out.println(Singleton.y);
    }
}
