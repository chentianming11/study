package com.chen.study.kafka.book.producer.interceptor;

import org.apache.kafka.clients.producer.*;

import java.util.Map;

/**
 * @author 陈添明
 * @date 2020/2/16
 */
public class ProducerinterceptorPrefix implements ProducerInterceptor<String, String> {

    private volatile long sendSuccess = 0;
    private volatile long sendFailure = 0;


    /**
     * KafkaProducer在将消息序列化和计算分区之前会调用生产者拦截器的 onSend()方法来对消 息进行相应 的定制化操作。
     *
     * @param record
     * @return
     */
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        String modifiedValue = "prefixl-" + record.value();
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(record.topic(), record.partition(), record.timestamp(),
                record.key(), modifiedValue, record.headers());
        return producerRecord;
    }


    /**
     * KafkaProducer 会在消息被应答( Acknowledgement)之前或消息发送失败时调用生产者拦截器的 onAcknowledgement()方法，优先于用户设定的 Callback 之前执行。
     *
     * @param metadata
     * @param e
     */
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception e) {
        if (e == null) {
            sendSuccess++;
        } else {
            sendFailure++;
        }
    }


    @Override
    public void close() {

        double successRatio = (double) sendSuccess / (sendFailure + sendSuccess);
        System.out.println("发送成功率：" + successRatio * 100 + "%");

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
