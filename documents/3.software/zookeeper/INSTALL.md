# zookeeper配置安装

### 1.下载

访问zookeeper官网,[下载zookeeper](http://zookeeper.apache.org/releases.html#download)

### 2.目录

将下载好的zookeeper进行解压操作

```
local>tat -zxvf zookeeper-3.4.11.tar.gz
```

进入目录,目录结构如下

```lua
zookeeper-3.4.11
└── bin -- 存放zookeeper运行命令
    └── zkServer.sh -- zookeeper服务端命令
    └── zkCli.sh -- zookeeper客户端命令
    └── *.sh -- 其余sh文件
└── conf -- 配置文件目录
├── contrib -- zookeeper附件功能
├── dist-maven -- maven打包编译后的jar包
├── docs -- zookeeper文档
├── lib  -- 所需依赖jar包
├── recipes -- 案例代码
└── src  -- 源码
	 
```

### 3.配置环境变量

修改环境配置文件,增加zookeepe配置,命令如下

```
local>vim /etc/profile
```

增加如下内容

```
export ZOOKEEPER_HOME=/usr/local/zookeeper-3.4.11
export PATH=$PATH:$ZOOKEEPER_HOME/bin:$JAVA_HOME/bin
```

### 4.配置zoo.cfg

将原始zoo_sample.cfg复制重命名zoo.cfg

```
>cp zoo_sample.cfg zoo.cfg
```

### 5.配置项介绍

>tickTime

用于计算时间单元.例如session超时时间:N*tickTime
默认2000毫秒=2秒

>initLimit

用于集群,允许从节点连接并同步到master节点的初始化连接时间,以tickTime的倍数来表示
默认是10,公式:N*tickTime=10*2000

>syncLimit

用于集群,master主节点与从节点之间发送消息,请求和应答时间长度,如果超过设置的时间点,从节点将会被抛弃
默认是5,公式:N*tickTime=5*2000

>dataDir

zookeeper数据存放目录地址

>dataLogDir

日志目录,缺省着和dataDir公用

>clientPort

客户端连接端口,默认2181





