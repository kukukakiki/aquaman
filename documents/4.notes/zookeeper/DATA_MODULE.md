# Zookeeper基本数据模型

1. zk以树形结构展示
2. 每个节点都称为znode，它可以有子节点，也可以有数据（目录数据）
3. 每个节点分为临时节点和永久节点，临时节点在客户端断开链接后消失
4. 每一个zk节点都有各自的版本号，可以通过命令行显示节点信息
5. 当节点数据发生变化，该节点的版本号会累加（乐观锁）
6. 删除/修改过时节点，版本号不匹配则会报错
7. 每个zk节点存储数据不宜过大（官方推荐）
8. 节点可以设置权限acl，可以通过权限来限制用户访问

>进入zk客户端

```
>cd /usr/local/zookeeper-3.4.11/bin/
>./zkCli.sh
.....忽略输出
WATCHER::

WatchedEvent state:SyncConnected type:None path:null
[zk: localhost:2181(CONNECTED) 0] help
ZooKeeper -server host:port cmd args
	stat path [watch]
	set path data [version]
	ls path [watch]
	delquota [-n|-b] path
	ls2 path [watch]
	setAcl path acl
	setquota -n|-b val path
	history
	redo cmdno
	printwatches on|off
	delete path [version]
	sync path
	listquota path
	rmr path
	get path [watch]
	create [-s] [-e] path data acl
	addauth scheme auth
	quit
	getAcl path
	close
	connect host:port
[zk: localhost:2181(CONNECTED) 1] ls /
[zookeeper]
[zk: localhost:2181(CONNECTED) 2] ls /zookeeper
[quota]
[zk: localhost:2181(CONNECTED) 3] ls /zookeeper/quota
[]
```

>退出zk客户端

键盘ctrl+c

zookeeper作用

1. 主节点（master节点）选举，主节点挂了以后，从节点就会接手主节点工作，并且保证这个节点是唯一的，这也是所谓的首脑模式，
从而保证我们的集群是高可用的
2. 统一配置文件管理，即只需要部署一台服务器，则可以把相同的配置文件同步更新到其它所有服务器，此操作在云计算中用的比较多
3. 发布与订阅，类似消息队列MQ，dubbo发布者就吧数据存在znode上，订阅者会读取这个数据
4. 提供分布式锁，分布式环境中不同进程之间争夺资源，类似多线程中的锁
5. 集群管理，集群中保存数据的强一致性