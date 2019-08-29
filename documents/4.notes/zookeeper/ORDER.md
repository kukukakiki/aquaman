### 启动服务

```
zookeeper/bin>./zkServer.sh start
```

### 重启服务

若服务未启动，则会启动

若服务已启动，则先会停止服务，然后启动服务

```
zookeeper/bin>./zkServer.sh restart
```

### 停止服务

```
zookeeper/bin>./zkServer.sh stop
```

### 查询状态

输出如下信息，其中"standalone"表示单机模式

```
zookeeper/bin>./zkServer.sh status
ZooKeeper JMX enabled by default
Using config: /usr/local/zookeeper-3.4.11/bin/../conf/zoo.cfg
Mode: standalone
```