package com.chen.study.concurrent.concurrent2.active.objects;

/**
 * @author 陈添明
 * @date 2018/10/1
 */
public class ActiveObjectClientTest {

    public static void main(String[] args) {
        ActiveObject activeObject = ActiveObjectFactory.createActiveObject();

        new MakerClientThread("chen", activeObject).start();
        new MakerClientThread("luo", activeObject).start();


        new DisplayClientThread("xxx", activeObject).start();
//        new DisplayClientThread("yyy", activeObject).start();

    }
}
