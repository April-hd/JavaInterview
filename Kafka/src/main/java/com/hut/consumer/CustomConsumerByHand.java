package com.hut.consumer;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Properties;

/**
 * 手动提交
 * 1、同步提交 -- 会阻塞直到offset提交成功
 * 2、异步提交 -- 不会则色，提交了offset立马就去拉取下一批数据，失败了的话有自动重试提交
 */
public class CustomConsumerByHand {

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

        // 自动提交 默认 true，置为fasle进行手动提交
        properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);

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

            kafkaConsumer.commitSync(); // 同步提交
//            kafkaConsumer.commitAsync(); // 异步提交
        }

    }

}
