package com.chen.study.kafka.book.consumer.offset;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 陈添明
 * @date 2020/2/17
 */
public class ManualBatchCommitOffsetDemo {


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

            /**
             * 批量处理+批量提交
             */
            final int minBatchSize = 200;
            List<ConsumerRecord> buffer = new ArrayList<>();
            while (isRunning.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    buffer.add(record);
                }
                if (buffer.size() >= minBatchSize) {
                    // 其它处理
                    consumer.commitSync();
                    buffer.clear();
                }
            }


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
        // 关闭自动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
        return properties;
    }
}
