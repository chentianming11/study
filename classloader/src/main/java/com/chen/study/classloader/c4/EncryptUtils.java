package com.chen.study.classloader.c4;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author 陈添明
 * @date 2018/10/14
 */
public abstract class EncryptUtils {

    public static final byte ENCRYPT_FACTOR = (byte) 0xaf;

    /**
     *
     * @param source  原文件路径
     * @param target    加密后目标文件路径
     */
    public static void doEncrypt(String source, String target){
        try(FileInputStream fis = new FileInputStream(source);
            FileOutputStream fos = new FileOutputStream(target)) {
            // 一次读一个byte
            int data;
            while ((data = fis.read()) != -1){
                fos.write(data ^ ENCRYPT_FACTOR);
                fos.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        EncryptUtils.doEncrypt("/Users/mac/work/git/demo/src/test/java/com/study/demo/classloader/c4/b.txt", "/Users/mac/work/git/demo/src/test/java/com/study/demo/classloader/c4/c.txt");
    }


}
