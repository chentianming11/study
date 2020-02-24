package com.chen.study.kafka.book.consumer.rebelance;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author 陈添明
 * @date 2020/2/22
 */
public class RebelanceConsumerListenerDemo {

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
        Map<TopicPartition, OffsetAndMetadata> currentOffsets = new HashMap<>();
        consumer.subscribe(Arrays.asList(topic), new ConsumerRebalanceListener() {

            /**
             * 这个方法会在再均衡开始之前和消费者停止读取消息之后被调用。
             * 可以通过这个回调方法 来处理消费位移的提交，以此来避免 一 些不必要的重复消费现象的发生。
             * @param partitions
             */
            @Override
            public void onPartitionsRevoked(Collection<TopicPartition> partitions) {
                consumer.commitSync(currentOffsets);
            }

            /**
             * 这个方法会在重新分配分区之后和消费者开始读取消费之前被调用。
             * @param partitions
             */
            @Override
            public void onPartitionsAssigned(Collection<TopicPartition> partitions) {

            }
        });

        try {

            /**
             * 将消费位移暂存到一个局部变量currentOffsets中，这样在正常消费的时候 可以通过commitAsync()方法来异步提交消费位移，
             * 在发生再均衡动作之前可以通过再均衡监听 器的onPartitionsRevoked()回调执行commitSync()方法同步提交消费位移，以尽量避免 一 些不必
             要的重复消费。
             */
            while (isRunning.get()) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    // 执行业务逻辑

                    // 放到currentOffsets中
                    currentOffsets.put(new TopicPartition(record.topic(), record.partition())
                            , new OffsetAndMetadata(record.offset() + 1));
                }
                // 异步提交为位移
                consumer.commitAsync(currentOffsets, null);


            }

        } finally

        {
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
