package com.chen.study.kafka.book.consumer.deserializer;

import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

/**
 * @author 陈添明
 * @date 2020/2/16
 */
public class ProtostuffDeserializer<T> implements Deserializer<T> {


    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {

    }


    @Override
    public T deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }

        /**
         * 怎么拿到泛型对象，再反射实例化赋值
         */

        return null;
    }

    @Override
    public void close() {

    }
}
