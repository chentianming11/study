package com.chen.study.design.pattern.adapter.ac2dc;

/**
 * @author 陈添明
 * @date 2019/4/14
 */
public class AdapterTest {

    public static void main(String[] args) {
        DC5VAdapter dc5VAdapter = new DC5VAdapter(new AC220());
        dc5VAdapter.outputDC5V();
    }
}
