package com.chen.study.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author 陈添明
 * @date 2020/1/12
 */
public class KafkaConsumerDemo {

    public static void main(String[] args) {
        Properties properties = new Properties();
        // kafka集群地址
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.13.102:9092,192.168.13.103:9092,192.168.13.104:9092");
        // client-id
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer-test");
        // 消费组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "gp-gid1");
        // 会话超时
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        // 自动提交
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000"); //自动提交(批量确认)
        // key的反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // value的反序列化
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 偏移量重置配置
        // earliest: 每次新的group-id的消费者会从头开始消费消息
        // latest：每次新的group-id的消费者会从上次消费之后的消息开始消费
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); //这个属性. 它能够消费昨天发布的数据
        KafkaConsumer<String, String> consumer = new KafkaConsumer(properties);
        // 订阅topic
        consumer.subscribe(Collections.singleton("test"));

        // 循环拉数据
        while (true) {
            ConsumerRecords<String, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            consumerRecords.forEach(record -> {
                //null->gp kafka practice msg:0->63
                System.out.println(record.key() + "->" + record.value() + "->" + record.offset());
            });
        }
    }
}
