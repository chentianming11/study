package com.chen.study.kafka.book.advance.ttl;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Headers;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.apache.kafka.common.header.internals.RecordHeaders;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author 陈添明
 * @date 2020/2/16
 */
public class KafkaProducerDemo {

    /**
     * kafka集群地址
     */
    public static final String brokerList = "192.168.199.110:9092,192.168.199.189:9092,192.168.199.242:9092";
    public static final String topic = "topic-ttl";

    public static void main(String[] args) {
        Properties props = initConfig();
        //自己直生产者客户端参数并创建 KafkaProducer 实例
        KafkaProducer<String, String> producer = new KafkaProducer(props);

        try {

            // 第一条消息：ttl=20
            Headers headers1 = new RecordHeaders()
                    .add(new RecordHeader("ttl", BytesUtils.longToBytes(20)));
            ProducerRecord<String, String> record1 =
                    new ProducerRecord<>(topic, null, System.currentTimeMillis(),
                            null, "msg_ttl_1", headers1);

            // 第二条消息：ttl=5，设置发送时间为5s前，因此该消息是过期消息
            Headers headers2 = new RecordHeaders()
                    .add(new RecordHeader("ttl", BytesUtils.longToBytes(5)));
            ProducerRecord<String, String> record2 =
                    new ProducerRecord<>(topic, null, System.currentTimeMillis() - 5000,
                            null, "msg_ttl_1", headers2);

            // 第一条消息：ttl=30
            Headers headers3 = new RecordHeaders()
                    .add(new RecordHeader("ttl", BytesUtils.longToBytes(30)));
            ProducerRecord<String, String> record3 =
                    new ProducerRecord<>(topic, null, System.currentTimeMillis(),
                            null, "msg_ttl_1", headers3);

            producer.send(record1).get();
            producer.send(record2).get();
            producer.send(record3).get();

        } catch (Exception e) {
            e.printStackTrace();
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
        return properties;
    }
}
