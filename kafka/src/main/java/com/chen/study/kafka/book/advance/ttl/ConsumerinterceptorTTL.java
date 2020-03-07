package com.chen.study.kafka.book.advance.ttl;

import org.apache.kafka.clients.consumer.ConsumerInterceptor;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.Headers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 自定义的消费
 * 者 拦截器 ConsumerlnterceptorTTL使用消息的巨mes七amp字段来判定是否过期， 如果消息的
 * 时间戳与当前的时间戳相差超过10秒则判定为过期，那么 这条消息也就被过滤而不投递给具体 的消费者。
 *
 * @author 陈添明
 * @date 2020/2/22
 */
public class ConsumerinterceptorTTL implements ConsumerInterceptor<String, String> {


    @Override
    public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
        long now = System.currentTimeMillis();
        Map<TopicPartition, List<ConsumerRecord<String, String>>> newRecords = new HashMap<>();
        for (TopicPartition tp : records.partitions()) {
            List<ConsumerRecord<String, String>> recordList = records.records(tp);
            List<ConsumerRecord<String, String>> newTpRecords = new ArrayList<>();
            for (ConsumerRecord<String, String> record : recordList) {
                Headers headers = record.headers();
                long ttl = -1;
                //判断headers中是否有key为“ttl"的Header
                for (Header header : headers) {
                    if (header.key().equalsIgnoreCase("ttl")) {
                        ttl = BytesUtils.bytesToLong(header.value());
                    }
                }
                //消息超时判定
                if (ttl < 0 || now - record.timestamp() < ttl * 1000) {
                    // 未超时
                    newTpRecords.add(record);
                }
            }
            if (!newTpRecords.isEmpty()) {
                newRecords.put(tp, newTpRecords);
            }
        }
        return new ConsumerRecords<>(newRecords);
    }


    @Override
    public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
        offsets.forEach((tp, offset) -> {
            System.out.println(tp + ": " + offset);
        });
    }


    @Override
    public void close() {

    }


    @Override
    public void configure(Map<String, ?> configs) {

    }
}
