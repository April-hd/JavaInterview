package com.hut.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 提高kafka吞吐量的一些参数
 */
public class CustomProducerParameters {

    public static void main(String[] args) {
        // 1、配置参数
        Properties properties = new Properties();
        // kafka集群地址
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.30.128:9092,192.168.30.128:9092,192.168.30.130:9092");

        // key,value序列化进行网络传输
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 缓冲区代销 默认32MB
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);

        // 批次大小 默认16k
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);

        // linger.ms 到了时间如果批次未满也会发送，提前满了也会发送
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1); // 1ms 生产环境5~100ms之间

        // 压缩发送数据, 那么每批次就能发送更多数据了
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy"); // 压缩类型有四种

        // 2、创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // 3、发送数据
        for (int i = 0; i < 5; i++) {
            kafkaProducer.send(new ProducerRecord<>("mytopic", 2, "table_name", "xhh" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    System.out.println("Topic:" + recordMetadata.topic() + ",Partition:" + recordMetadata.partition());
                }
            });
        }

        // 4、关闭资源
        kafkaProducer.close();
    }

}
