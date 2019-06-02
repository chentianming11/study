package com.chen.spring.action.c4.aop;

import java.io.*;

/**
 * @author 陈添明
 * @date 2019/2/24
 */ class Test {

    public void test1(Object o) {
        System.out.println("1111");
    }


    public void test1(String o) {
        System.out.println("2222");
    }

    static int[] arr = new int[10];

    public static void main(String[] args) throws FileNotFoundException {
        String s  = "1234567890abcdefghijk";
        byte[] bytes = s.getBytes();
        try {
            FileOutputStream outputStream = new FileOutputStream(new File("b.txt"));
            outputStream.write(bytes, 10, 10);
            outputStream.write(bytes, 0, 5);
        } catch (IOException e) {
            e.printStackTrace();
        }


        new FileInputStream("aa/b.txt");
    }
}
