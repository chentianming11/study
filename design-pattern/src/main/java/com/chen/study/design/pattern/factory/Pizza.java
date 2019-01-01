package com.chen.study.design.pattern.factory;

/**
 * @author 陈添明
 * @date 2019/1/1
 */
public abstract class Pizza {

    public void prepare(){
        System.out.println("准备披萨");
    }

    public void bake(){
        System.out.println("烘烤披萨");
    }

    public void cut(){
        System.out.println("切片");
    }
    public void box(){
        System.out.println("装盒");
    }

}
