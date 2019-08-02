# kafka安装(Linux版本)

## 1.下载&准备

### 1.1.下载zookeeper

[访问JAVA官方网站](),找到对应的版下载即可,如果想用系统一样的版[本请前往]()

### 1.2.下载zookeeper

[访问zookeeper官方网站](),找到对应的版下载即可,如果想用系统一样的版[本请前往]()

### 1.3.下载kafka

[访问kafka官方网站](http://kafka.apache.org/downloads),找到对应的版下载即可,如果想用系统一样的版[本请前往]()

### 1.4.前置准备(aquaman服务器)

> JDK: 1.8+
> Zookeeper: 3.4.11
> Kafka: 2.1.0

## 2.安装

### 2.1.解压

进入kafka文件目录,执行以下命令

```
tar -zxvf kafka_2.11-2.1.0.tgz
```

### 2.2.配置

1.进入kafka解压后的目录(若当前已在此目录请忽略)

```
cd /usr/local/kafka_2.11-2.1.0
```

2.配置zookeeper的dataDir目录,

```
vim config/zookeeper.properties
```

找到"dataDir"这一行,配置的路径需要根据自己的zookeeper配置保持一致即可
```
dataDir=/usr/local/zookeeper/dataDir
```

3.配置zookeeper的ip:port

```
vim config/server.properties
```

找到"zookeeper.connect"这一行,配置的路径需要根据自己的zookeeper配置保持一致即可

```
zookeeper.connect=localhost:2181
```


## 3.启动

1.进入kafka解压后的目录(若当前已在此目录请忽略)

```
cd /usr/local/kafka_2.11-2.1.0
```

2.执行以下命令,启动kafka服务

```
nohup bin/kafka-server-start.sh config/server.properties &
```

## 4.错误

### 4.1无法连接zookeeper

提示信息:

```
Timed out waiting for connection while in state: CONNECTING
```

解决办法:

问题1:进入zookeeper目录检查是否启动,执行命令

```
./zkServer.sh status
```

问题2:进入kafka目录,检查server.properties配置文件zookeeper连接地址是否正确,具行如下
```
zookeeper.connect=localhost:2181
```

### 4.2内存过小

提示信息:

```
cannot allocate memory
```

解决办法:

由于kafka默认最大和最小都是1G,如果服务器内存剩余空间过小,当无法启动,修改如下:

1.进入kafka-server-start.sh

```
vim bin/kafka-server-start.sh
```

2.修改配置

修改前

```
export KAFKA_HEAP_OPTS="-Xmx1G -Xms1G"
```

修改后

```
export KAFKA_HEAP_OPTS="-Xmx256M -Xms128M"
```