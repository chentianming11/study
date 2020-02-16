package com.chen.study.kafka.book.producer;

import com.chen.study.kafka.book.producer.interceptor.ProducerinterceptorPrefix;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * @author 陈添明
 * @date 2020/2/16
 */
public class KafkaProducerAnalysis {

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
        ProducerRecord record = new ProducerRecord(topic, "hello, kafka");

        try {
            /**
             * 发后及忘，它只管往Kafka中发送消息而并不关心消 息是否正确到达。
             */
            producer.send(record);
            /**
             * 同步发送
             * 实际上send()方法本身就是异步的，send()方法返回的Future对象可以使调用方稍后获得发送的结果。
             * 示例中在执行send()方法之后直接链式调用了get()方法来阻塞等待Kaflca的响应，
             * 直到消息发送成功， 或者发生异常。 如果发生异常，那么就需要捕获异常并交由外层逻辑处理。
             */
            producer.send(record).get();


            /**
             * 异步发送
             * 异步发送的方式，一般是在send()方法里指定一个Callback 的回调函数，
             * Kafka 在返回响应时调用该函数来实现异步的发送确认。
             */
            producer.send(record, (metadata, exception) -> {
                if (exception != null) {
                    exception.printStackTrace();
                } else {
                    System.out.println(metadata.topic() + "-" + metadata.partition()
                            + ": " + metadata.offset());
                }
            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
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
        // 用来 设定KafkaProducer 对应的客户端过， 默认值为 “” 。 如果客户端不设置， 则KafkaProducer会
        // 自动生成一个非空字符串， 内容形式如"producer-I""producer-2", 即字符串"producer-" 与数字的拼接。
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "producer.client.id.demo");
        // 可重试次数
        // 对千可重试的异常，如果配置了retries 参数，那么只要在规定的重试次数内自行恢复 了，就不会抛出异常。
        properties.put(ProducerConfig.RETRIES_CONFIG, 10);
        // 配置自定义拦截器
        // KafkaProducer 中不仅可以指定一个拦截器，还可以指定多个拦截器以形成拦截链。拦截链 会按照 interceptor.classes 参数配置的拦截器的顺序来一一执行(配置的时候，各个拦 截器之间使用逗号隔开)。
        properties.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, ProducerinterceptorPrefix.class.getName());
        return properties;
    }
}
