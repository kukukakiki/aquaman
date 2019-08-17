# spring boot kafka 接入须知

[Spring kafka API](https://docs.spring.io/spring-kafka/docs/2.2.4.RELEASE/reference/html/)

### kafka问题记录

## 无法发送消息

spring boot发送消息至kafka，错误信息如下

```
org.apache.kafka.clients.networkclient broker may not be available
```

原因1

kafka默认没有开放外网访问权限

解决1

进入kafka的config配置目录，修改server.properties

```
advertised.listeners=PLAINTEXT://#you ip#:#you kafka port#
```

原因2

kafka没有启动

解决2

```
nohup bin/kafka-server-start.sh config/server.properties &
```
