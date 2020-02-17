package com.chen.study.kafka.book.consumer.offset;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 陈添明
 * @date 2020/2/16
 */
public class KafkaConsumerOffsetDemo {

    /**
     * kafka集群地址
     */
    public static final String brokerList = "192.168.199.110:9092,192.168.199.189:9092,192.168.199.242:9092";
    public static final String topic = "topic-demo";
    private static final String groupId = "group.demo";

    public static final AtomicBoolean isRunning = new AtomicBoolean(true);


    public static void main(String[] args) {
        Properties props = initConfig();
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);

        try {
            TopicPartition topicPartition = new TopicPartition(topic, 0);
            consumer.assign(Arrays.asList(topicPartition));
            // 当前消费到位移
            long lastConsumedOffset = -1;
            while (isRunning.get()) {
                ConsumerRecords<String, String> records = consumer.poll(1000);
                if (records.isEmpty()) {
                    break;
                }
                List<ConsumerRecord<String, String>> partionRecords = records.records(topicPartition);

                lastConsumedOffset = partionRecords.get(partionRecords.size() - 1).offset();

                // 同步提交消费位移
                consumer.commitSync();
            }

            System.out.println("comsumed offset is " + lastConsumedOffset);

            OffsetAndMetadata committed = consumer.committed(topicPartition);
            System.out.println("commited offset is " + committed.offset());

            long posititon = consumer.position(topicPartition);
            System.out.println("the offset of t he next record is " + posititon);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumer.close();
        }
    }

    private static Properties initConfig() {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        //设置消货组的名称，具体的释义可以参见第 3 幸
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "consumer.client.id.demo");
        return properties;
    }
}
