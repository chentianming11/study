package com.chen.study.kafka.book.consumer.offset;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 陈添明
 * @date 2020/2/17
 */
public class ManualCommitOffsetDemo {


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

            while (isRunning.get()) {
                /**
                 * 每次拉取后手动提交
                 */
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    // do something
                }

                // commitSync()方法会根据 poll()方法拉取的最新位移来进行提交(注意提交的值对应于图 3-6 中 position 的位置〉
                consumer.commitSync();
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
