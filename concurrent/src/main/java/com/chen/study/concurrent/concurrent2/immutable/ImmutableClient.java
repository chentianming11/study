package com.chen.study.concurrent.concurrent2.immutable;

import java.util.stream.IntStream;

/**
 * @author 陈添明
 * @date 2018/9/19
 */
public class ImmutableClient {

    public static void main(String[] args) {

        // 共享数据
        Person person = new Person("chen", "jiangxi");

        IntStream.range(0, 5).forEach(i -> {
            new UsePersonThread(person).start();
        });


    }
}
