package com.chen.study.kafka.book.transaction;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author 陈添明
 * @date 2020/2/9
 */
public class ProducerTransationTest {

    /**
     * kafka集群地址
     */
    public static final String brokerList = "192.168.199.110:9092,192.168.199.189:9092,192.168.199.242:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        // 事务id配置
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactionid");
        //自己直生产者客户端参数并创建 KafkaProducer 实例
        KafkaProducer<String, String> producer = new KafkaProducer(properties);

        // 初始化事务
        producer.initTransactions();
        // 开启事务
        producer.beginTransaction();

        try {
            //处理业务逻辑并创建 ProducerRecord
            ProducerRecord record1 = new ProducerRecord(topic, "msg1");
            producer.send(record1);
            ProducerRecord record2 = new ProducerRecord(topic, "msg2");
            producer.send(record2);
            ProducerRecord record3 = new ProducerRecord(topic, "msg3");
            producer.send(record3);

            //处理一些其他逻辑

            producer.commitTransaction();

        } finally {
            producer.close();
        }
    }
}
