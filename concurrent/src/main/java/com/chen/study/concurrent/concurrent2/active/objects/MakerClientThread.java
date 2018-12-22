package com.chen.study.concurrent.concurrent2.active.objects;

/**
 * @author 陈添明
 * @date 2018/10/1
 */
public class MakerClientThread extends Thread {

    private final ActiveObject activeObject;
    private final char fillChar;


    public MakerClientThread(String name ,ActiveObject activeObject) {
        super(name);
        this.activeObject = activeObject;
        this.fillChar = name.charAt(0);
    }


    @Override
    public void run() {
        try {
            for (int i = 1; true; i++) {
                Result result = activeObject.makeString(i, fillChar);
                Thread.sleep(100);
                String  value = String.valueOf(result.getResultValue());
                System.out.println(Thread.currentThread().getName() + "生成字符串value=" + value);

            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
