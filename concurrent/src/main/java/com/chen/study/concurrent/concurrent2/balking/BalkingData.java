package com.chen.study.concurrent.concurrent2.balking;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Balking: 放弃
 * @author 陈添明
 * @date 2018/9/23
 */
public class BalkingData {

    private String fileName;

    private String context;

    private boolean changed;

    public BalkingData(String fileName, String context) {
        this.fileName = fileName;
        this.context = context;
        this.changed = true;
    }

    public synchronized void change(String newContext){
        System.out.println(Thread.currentThread().getName() + "修改：" + newContext);
        this.context = newContext;
        this.changed = true;
    }

    public synchronized void save() throws IOException {
        if (!changed){
            return;
        }
        doSave();
        this.changed = false;
    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + "调用了save方法: " + context);
        try (FileWriter writer = new FileWriter(fileName, true);){
            writer.write(context + "\n");
            writer.flush();
        }
    }
}
