package com.hut.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 带回调方法的异步发送
 * 指定分区号发送数据
 */
public class CustomProducerCallbackPartition {

    public static void main(String[] args) {

        // 1、配置信息
        Properties properties = new Properties();
        // kafka集群地址，不需要全部配置完
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.30.128:9092,192.168.30.128:9092,192.168.30.130:9092");
        // key,value的序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 2、创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // 3、发送数据
        for (int i = 0; i < 5; i++) {
            // 发送到指定分区
            kafkaProducer.send(new ProducerRecord<>("mytopic", 1,"","xhh" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e == null) {
                        System.out.println("Topic:" + recordMetadata.topic() + ", Partition:" + recordMetadata.partition());
                    }
                }
            });
        }

        // 4、关闭资源
        kafkaProducer.close();

    }

}
