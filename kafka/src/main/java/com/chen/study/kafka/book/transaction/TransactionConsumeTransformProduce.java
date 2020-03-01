package com.chen.study.kafka.book.transaction;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.errors.ProducerFencedException;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author 陈添明
 * @date 2020/3/1
 */
public class TransactionConsumeTransformProduce {

    public static final String brokerList = "192.168.199.110:9092,192.168.199.189:9092,192.168.199.242:9092";

    public static Properties getConsumerProperties() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 关闭自动位移提交
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "groupid");
        return props;
    }

    public static Properties getProducerProperties() {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, brokerList);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactionalId");
        return props;
    }

    public static void main(String[] args) {
        //初始化生产者和消费者
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(getConsumerProperties());
        KafkaProducer<String, String> producer = new KafkaProducer<>(getProducerProperties());

        //初始化事务
        producer.initTransactions();

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
            if (!records.isEmpty()) {
                Map<TopicPartition, OffsetAndMetadata> offsets = new HashMap<>();
                //开启事务
                producer.beginTransaction();
                try {
                    for (TopicPartition partition : records.partitions()) {
                        List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                        for (ConsumerRecord<String, String> record : partitionRecords) {
                            // 业务逻辑处理
                            ProducerRecord<String, String> producerRecord =
                                    new ProducerRecord<>("topic-sink", record.key(), record.value());
                            //消费—生产模型
                            producer.send(producerRecord);
                        }
                        long lastConsumedOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                        offsets.put(partition, new OffsetAndMetadata(lastConsumedOffset + 1));
                    }
                    // 提交消费位移
                    producer.sendOffsetsToTransaction(offsets, "groupid");
                    //提交事务
                    producer.commitTransaction();
                } catch (ProducerFencedException e) {
                    //log the exception
                    //中止事务
                    producer.abortTransaction();
                }
            }
        }

    }
}
