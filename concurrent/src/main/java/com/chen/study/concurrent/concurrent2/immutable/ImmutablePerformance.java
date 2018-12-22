package com.chen.study.concurrent.concurrent2.immutable;

/**
 * @author 陈添明
 * @date 2018/9/19
 */
public class ImmutablePerformance {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
//        SyncObj syncObj = new SyncObj();
//        syncObj.setName("chen");

        ImmutableObj immutableObj = new ImmutableObj("chen");
        for (int i = 0; i < 100000; i++) {
//            System.out.println(syncObj);
            System.out.println(immutableObj);
        }
        long end = System.currentTimeMillis();
        // 879  781
        System.out.println("耗时：" + (end - start));

    }


}

class ImmutableObj {

    private final String name ;

    public ImmutableObj(String name) {

        this.name = name;
    }

    @Override
    public String toString() {
        return "ImmutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}

class SyncObj {
    private String name;

    public String getName() {
        return name;
    }

    public synchronized void setName(String name) {
        this.name = name;
    }

    @Override
    public synchronized String toString() {
        return "MutableObj{" +
                "name='" + name + '\'' +
                '}';
    }
}
