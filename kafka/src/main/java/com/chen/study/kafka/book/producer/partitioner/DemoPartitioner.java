package com.chen.study.kafka.book.producer.partitioner;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.utils.Utils;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 除了使用 Kafka 提供的默认分区器进行分区分配，还可以使用自定义的分区器，只需同
 * DefaultPartitioner一样实现 Partitioner接口即可。默认的分区器在 key为 null时不会选择非可用 的分区，
 * 我们可以通过自 定义的分区器 DemoPartitioner来打破这一限制，具体的实现可以参考 下面的示例代码
 *
 * @author 陈添明
 * @date 2020/2/16
 */
public class DemoPartitioner implements Partitioner {

    private final AtomicInteger counter = new AtomicInteger(0);


    @Override
    public int partition(String topic, Object key, byte[] keyBytes, Object value, byte[] valueBytes, Cluster cluster) {
        List<PartitionInfo> partitions = cluster.partitionsForTopic(topic);
        int numPartitions = partitions.size();
        if (null == keyBytes) {
            return counter.getAndIncrement() % numPartitions;
        } else {
            return Utils.toPositive(Utils.murmur2(keyBytes)) % numPartitions;
        }
    }

    @Override
    public void close() {

    }

    @Override
    public void configure(Map<String, ?> configs) {

    }
}
