package com.hut.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * 保证精确一次：幂等性+事务+ACK设置为-1 + 分区副本数>=2 + ISR应答数 >= 2 精确一次 保证不丢不重
 */
public class CustomProducerTranscation {

    public static void main(String[] args) {

        // 1、配置信息
        Properties properties = new Properties();
        // kafka集群地址，不需要全部配置完
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "192.168.30.128:9092,192.168.30.128:9092,192.168.30.130:9092");
        // key,value的序列化
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        // 开启幂等性 默认就是true
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);

        // 指定事务id
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transaction_id_01"); // 使用事务时必须指定全局唯一ID

        // 2、创建kafka生产者对象
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);

        // 初始化事务
        kafkaProducer.initTransactions();

        // 开启事务
        kafkaProducer.beginTransaction();

        try {
            // 3、发送数据
            for (int i = 0; i < 5; i++) {
                kafkaProducer.send(new ProducerRecord<>("mytopic", "xhh" + i));
            }
            int i = 1 / 0;
            kafkaProducer.commitTransaction();
        } catch (Exception e) {
            // 回滚事务
            kafkaProducer.abortTransaction();
            e.printStackTrace();
        } finally {
            // 4、关闭资源
            kafkaProducer.close();
        }

    }

}
