package com.hut.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.consumer.RoundRobinAssignor;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 订阅主题消费，如果在一个消费者组里只有一个消费者，就消费所有分区数据
 * 如果一个消费者里有多个消费者，就使用分区分配策略再平衡，默认Range
 */
public class CustomConsumer1 {

    public static void main(String[] args) {

        // 1、配置信息
        Properties properties = new Properties();
        // 集群地址
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.30.128:9092,192.168.30.128:9092,192.168.30.130:9092");
        // key,value反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test");

        // 配置分区分配策略
        // RangeAssignor(默认) RoundRobinAssignor StickyAssignor CooperativeStickyAssignor
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, RoundRobinAssignor.class.getName());

        // 2、创建kafka消费者对象
        KafkaConsumer kafkaConsumer = new KafkaConsumer<>(properties);

        // 3、订阅主题
        ArrayList<String> topics = new ArrayList<>();
        topics.add("mytopic");
        kafkaConsumer.subscribe(topics);

        // 4、消费数据
        while (true) {

            ConsumerRecords consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100)); // 在100ms的时间里拉取数据
            for (Object consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }

        }

    }

}
