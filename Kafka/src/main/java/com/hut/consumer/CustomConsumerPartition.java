package com.hut.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 指定topic的分区进行消费，订阅主题的一个或多个分区
 */
public class CustomConsumerPartition {

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

        // 2、创建kafka消费者对象
        KafkaConsumer kafkaConsumer = new KafkaConsumer<>(properties);

        // 3、指定分区号消费
        ArrayList<TopicPartition> partitions = new ArrayList<>();
        partitions.add(new TopicPartition("mytopic", 0)); // 指定topic的分区号进行消费
        kafkaConsumer.assign(partitions);

        // 4、消费数据
        while (true) {

            ConsumerRecords consumerRecords = kafkaConsumer.poll(Duration.ofMillis(100)); // 拉去100ms的数据
            for (Object consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }

        }

    }

}
