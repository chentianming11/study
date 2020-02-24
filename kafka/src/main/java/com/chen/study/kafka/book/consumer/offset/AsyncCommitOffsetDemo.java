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
 * @date 2020/2/18
 */
public class AsyncCommitOffsetDemo {

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
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));

                for (ConsumerRecord<String, String> record : records) {
                    // 业务处理
                }
                /**
                 * 异步位移提交 - 异常时重复提交可能会导致重复消费
                 * commitAsync()提交的时候同样会有失败的情况发生，那么我们应该怎么处理呢?读者有可 能想到的是重试，问题的关键也就在这里了。
                 * 如果某一次异步提交的消费位移为 x， 但是提交 失败了，然后下一次又异步提交了消费位移为 x+y，这次成功了。
                 * 如果这里引入了重试机制， 前一次 的异步提交的消费位移在重试的时候提交成功了，那么此时的消费位移又变为了 x。
                 */
                consumer.commitAsync(((offsets, exception) -> {
                    if (exception == null) {
                        System.out.println(offsets);
                    } else {
                        System.out.println("提交位移失败，offset=" + offsets);
                    }
                }));

                /**
                 * 为此我们可以 设置一个递增的序号来维护异步提交的顺序，每次位移提交之后就增加序号 相对应的值。
                 * 在遇到位移提交失败需要重试的时候，可以检查所提交的位移和序号的值的大 小，
                 */
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
