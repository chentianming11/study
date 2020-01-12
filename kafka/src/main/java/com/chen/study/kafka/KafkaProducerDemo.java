package com.chen.study.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @author 陈添明
 * @date 2020/1/12
 */
public class KafkaProducerDemo {

    public static void main(String[] args) throws InterruptedException {
        // 发送者属性配置
        Properties properties = new Properties();
        // kafka集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.13.102:9092,192.168.13.103:9092,192.168.13.104:9092");
        // client-id
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer-study");
        // 分区策略配置
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.chen.study.kafka.CustomPartitioner");
        // 生产者的key序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        // 生产者的value序列化
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 实例化发送者
        KafkaProducer<String, String> producer = new KafkaProducer<>(properties);

        // 发送消息
        int num = 0;
        while (num < 20) {
            try {
                String msg = "kafka发送消息：" + num;
                //get 会拿到发送的结果
                //同步 get() -> Future()

                // 回调通知
                producer.send(new ProducerRecord<>("test", "key", msg), (metadata, exception) -> {
                    // callback
                    System.out.println(metadata.offset() + "->" + metadata.partition() + "->" + metadata.topic());
                });
                TimeUnit.SECONDS.sleep(2);
                ++num;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // 保证主线程不退出
        Thread.currentThread().join();
    }
}
