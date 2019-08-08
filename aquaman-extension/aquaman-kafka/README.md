[Spring kafka API](https://docs.spring.io/spring-kafka/docs/2.2.4.RELEASE/reference/html/)

创建Topic

进入kafka安装目录
该命令结尾的"aquamanLogger"及是本次创建Topic名称

```
kafka>./bin/kafka-topics.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic aquamanLogger
Created topic "aquamanLogger".
```

查看Topic

```
kafka>./bin/kafka-topics.sh --list --zookeeper localhost:2181
```