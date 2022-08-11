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
 * 自动提交
 * 默认是true
 * 5秒提交一次
 * 提前之前消费者挂了导致没有及时更新offset，被其他消费者重复消费数据了
 */
public class CustomConsumerAutoOffset {

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

        // 自动提交 默认 true
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);

        // 自动提交offset时间间隔 默认5s提交一次，下面改为1s
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, 1000); // 1000ms

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
