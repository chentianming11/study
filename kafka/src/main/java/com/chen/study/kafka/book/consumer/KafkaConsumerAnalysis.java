package com.chen.study.kafka.book.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 陈添明
 * @date 2020/2/16
 */
public class KafkaConsumerAnalysis {

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

        /**
         * 消费者不仅可以通过KafkaConsumer.subscribe()方法订阅主题，还可以直接订阅某些主题的 特定分区，在KafkaConsumer中还提供了一个 assign()方法来实现这些功能
         */
        consumer.subscribe(Arrays.asList(topic));
        // consumer.assign(Arrays.asList(new TopicPartition("topic-demo", 0)));

        /**
         * 通过pa rtitionFor()方法的协助，我们可以通过ass ig顶)方法来实现订阅主题(全部分区)的 功能
         */
        /*List<TopicPartition> partitions = new ArrayList<>();
        List<PartitionInfo> partitionInfos = consumer.partitionsFor(topic);
        if (partitionInfos != null) {
            for (PartitionInfo tpInfo : partitionInfos) {
                partitions.add(new TopicPartition(tpInfo.topic(), tpInfo.partition()));
                consumer.assign(partitions);
            }
        }*/

        try {

            while (isRunning.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records) {
                    System.out.println("topic =" + record.topic()
                            + ", partition = " + record.partition()
                            + ", offset=" + record.offset());

                    System.out.println("key =" + record.key()
                            + ", value=" + record.value());

                    //do some thing to process record.
                }

                for (TopicPartition tp : records.partitions()) {
                    List<ConsumerRecord<String, String>> recordList = records.records(tp);
                    for (ConsumerRecord<String, String> record : recordList) {
                        System.out.println(record.partition() + " : " + record.value());
                    }
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

        return properties;
    }
}
