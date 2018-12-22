package com.chen.study.concurrent.concurrent2.gate;

/**
 * 共享资源
 * @author 陈添明
 * @date 2018/9/16
 */
public class Gate {
    private int counter = 0;
    private String name = "noBody";
    private String address = "noWhere";

    /**
     * 临界值
     * @param name
     * @param address
     */
    public void pass(String name, String  address){
        this.counter ++;
        this.name = name;
        this.address = address;

        verity();
    }

    private void verity() {
        if (name.charAt(0) != address.charAt(0)){
            System.out.println("========姓名和地址不一致=========" + toString());
        }
    }

    @Override
    public String toString() {
        return "Gate{" +
                "counter=" + counter +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
