package com.chen.study.kafka.book.consumer.offset;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author 陈添明
 * @date 2020/2/16
 */
public class KafkaProducerOffsetDemo {

    /**
     * kafka集群地址
     */
    public static final String brokerList = "192.168.199.110:9092,192.168.199.189:9092,192.168.199.242:9092";
    public static final String topic = "topic-demo";

    public static void main(String[] args) {
        Properties props = initConfig();
        //自己直生产者客户端参数并创建 KafkaProducer 实例
        KafkaProducer<String, String> producer = new KafkaProducer(props);
        //构建所需妥发送的消息
        ProducerRecord record = new ProducerRecord(topic, 0, null, "hello, kafka");

        try {
            /**
             * 发后及忘，它只管往Kafka中发送消息而并不关心消 息是否正确到达。
             */
            for (int i = 0; i < 100; i++) {
                producer.send(record);
            }


        } finally {
            /**
             * close()方法会阻塞等待之前所有的发送请求完成后再关闭KafkaProducer。
             */
            producer.close();
        }


    }

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        // 用来 设定KafkaProducer 对应的客户端过， 默认值为 “” 。 如果客户端不设置， 则KafkaProducer会
        // 自动生成一个非空字符串， 内容形式如"producer-I""producer-2", 即字符串"producer-" 与数字的拼接。
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        // 可重试次数
        // 对千可重试的异常，如果配置了retries 参数，那么只要在规定的重试次数内自行恢复 了，就不会抛出异常。
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);
        // 配置自定义拦截器
        return properties;
    }
}
