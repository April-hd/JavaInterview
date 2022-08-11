package com.hut.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.*;

/**
 * 指定时间段t进行消费
 * 平时默认是从最新的数据开始消费 lateset（从最新的（最后一条）开始消费）
 * 还有earliest（从头开始消费）、none（没找到之前的offset抛出异常）
 */
public class CustomConsumerSeekTime {

    public static void main(String[] args) {

        // 1、配置信息
        Properties properties = new Properties();
        // 集群地址
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.30.128:9092,192.168.30.128:9092,192.168.30.130:9092");
        // key,value反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 指定消费者组
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        // 2、创建kafak消费者
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);

        // 3、订阅topic
        ArrayList<String> topics = new ArrayList<>();
        topics.add("mytopic");
        kafkaConsumer.subscribe(topics);

        // 因为消费者分区分配策略需要时间，必须给消费者分区分配完毕才能拿到assignment，不然是空
        Set<TopicPartition> assignment = kafkaConsumer.assignment(); // 获取topic的分区集合
        while (assignment.size() == 0) {
            kafkaConsumer.poll(Duration.ofMillis(1)); // 主动拉取一下数据，加速一下分区分配的完成
            assignment = kafkaConsumer.assignment(); // 重新获取一次完成的分区集合
        }

        // 把时间转为offset
        HashMap<TopicPartition, Long> topicPartitionLongHashMap = new HashMap<>();
        // 封装集合数据
        for (TopicPartition topicPartition : assignment) {
            topicPartitionLongHashMap.put(topicPartition, System.currentTimeMillis() - 1 * 24 * 60 * 60 * 1000); // 指定一天的时间
        }
        // 得到每个分区一天前的offset
        Map<TopicPartition, OffsetAndTimestamp> topicPartitionOffsetAndTimestampMap = kafkaConsumer.offsetsForTimes(topicPartitionLongHashMap);
        // 指定offset进行消费
        for (TopicPartition topicPartition : assignment) {
            OffsetAndTimestamp offsetAndTimestamp = topicPartitionOffsetAndTimestampMap.get(topicPartition);
            kafkaConsumer.seek(topicPartition, offsetAndTimestamp.offset()); // 让每个分区从一天前的offset开始消费
        }

        // 4、消费数据
        while (true) {

            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }

        }

    }

}
