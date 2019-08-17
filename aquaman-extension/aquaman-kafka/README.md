# spring boot kafka 接入须知

[Spring kafka API](https://docs.spring.io/spring-kafka/docs/2.2.4.RELEASE/reference/html/)

### kafka问题记录

## 启动提示需要group.id

无法启动应用，错误信息如下

```
org.springframework.context.ApplicationContextException: Failed to start bean 'org.springframework.kafka.config.internalKafkaListenerEndpointRegistry'; nested exception is java.lang.IllegalStateException: No group.id found in consumer config, container properties, or @KafkaListener annotation; a group.id is required when group management is used.
```

> 原因

使用组管理将主题分区分配给消费者，因此需要一个组，需要在配置文件中指定"kafka.consumer.group-id"

> 解决

yml配置为例

```
# kafka 配置
kafka:
  consumer:
    group-id: test-consumer-group222
```


## 无法发送消息

spring boot发送消息至kafka，错误信息如下

```
org.apache.kafka.clients.networkclient broker may not be available
```

> 原因1

kafka默认没有开放外网访问权限

> 解决1

进入kafka的config配置目录，修改server.properties

```
advertised.listeners=PLAINTEXT://#you ip#:#you kafka port#
```

> 原因2

kafka没有启动

> 解决2

```
nohup bin/kafka-server-start.sh config/server.properties &
```
