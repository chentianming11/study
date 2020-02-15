package com.chen.study.kafka.book;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author 陈添明
 * @date 2020/2/9
 */
public class ProducerFastStart {

    /**
     * kafka集群地址
     */
    public static final String brokerList = "192.168.199.110:9092,192.168.199.189:9092,192.168.199.242:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers", brokerList);
        //自己直生产者客户端参数并创建 KafkaProducer 实例
        KafkaProducer<String, String> producer = new KafkaProducer(properties);
        //构建所需妥发送的消息
        ProducerRecord record = new ProducerRecord(topic, "hello, kafka");
        producer.send(record);
        //关闭生产者客户主自示告，1
        producer.close();
    }
}
