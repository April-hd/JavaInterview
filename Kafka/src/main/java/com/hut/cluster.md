# zookeeper集群配置文件
dataDir=/home/kafka_2.13-3.2.1/zkdata
logDir=/home/kafka_2.13-3.2.1/zklogs
clientPort=2181
maxClientCnxns=0
admin.enableServer=false
Time=2000
initLimit=10
syncLimit=5
server.1=192.168.30.128:2888:3888
server.2=192.168.30.129:2888:3888
server.3=192.168.30.130:2888:3888

在对应机器文件下创建zkdata和zklogs目录
在对应机器下的zkdata创建myid的文件，分别写上1，2，3

# kafka集群配置文件
broker.id=0
log.dirs=/home/kafka_2.13-3.2.1/kafkadata
zookeeper.connect=kafka01:2181,kafka02:2181,kafka03:2181/kafka
delete.topic.enable=true

# 启动顺序
1、启动zookeeper
zookeeper-server-start.sh -daemon /home/kafka_2.13-3.2.1/config/zookeeper.properties

2、启动kafka
kafka-server-start.sh -daemon /home/kafka_2.13-3.2.1/config/server.properties

3、创建topic
kafka-topics.sh --bootstrap-server kafka01:9092 --topic mytopic --create --partitions 3 --replication-factor 3

4、查看topic
kafka-topics.sh --bootstrap-server kafka01:9092 --topic mytopic --describe

4、开启生产者测试
kafka-console-producer.sh --bootstrap-server kafka01:9092 --topic mytopic

5、开启消费者测试
kafka-console-consumer.sh --bootstrap-server kafka01:9092 --topic mytopic --from-beginning

其他命令
1、查看topic list
kafka-topics.sh --bootstrap-server kafka01:9092 --list

2、删除topic
kafka-topics.sh --bootstrap-server kafka01:9092 --topic mytopic --delete
