package com.chen.study.concurrent.concurrent3.juc.util.coll;

import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author 陈添明
 * @date 2019/9/1
 */
public class ConcurrentHashMapTest {

    @Test
    public void test() {
        ConcurrentHashMap map = new ConcurrentHashMap();
        map.put("", 1);

        Object o = map.get("");
        System.out.println(o);
    }
}
