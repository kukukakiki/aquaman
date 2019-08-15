# zookeeper命令

"[]" 表示可选参数

## 查询类命令

### ls

获取当前节点的子节点

> ls path [watch]

``` console
[zk: localhost:2181(CONNECTED) 0] ls /
[zookeeper]
```

### stat

获取节点状态

> stat path [watch]

``` console
[zk: localhost:2181(CONNECTED) 0] stat /
cZxid = 0x0
ctime = Thu Jan 01 08:00:00 CST 1970
mZxid = 0x0
mtime = Thu Jan 01 08:00:00 CST 1970
pZxid = 0x28d
cversion = 11
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 0
numChildren = 13
```

内容介绍

>cZxid: 当前节点创建后zk分配的ID
>ctime: 当前节点创建时间
>mZxid: 当前节点修改后zk分配的ID
>mtime: 当前节点修改时间
>pZxid: 子节点ID
>cversion: 字节点版本
>dataVersion: 当前节点数据的版本号
>aclVersion: 权限版本
>ephemeralOw: 永久节点为0x0，临时节点则非0x0
>dataLength: 数据长度
>numChildren: 当前节点的子节点个数


### ls2

ls和ls2的区别是,ls2相当于ls+stat命令合集

``` console
[zk: localhost:2181(CONNECTED) 0] ls2 /
[zookeeper]
cZxid = 0x0
ctime = Thu Jan 01 08:00:00 CST 1970
mZxid = 0x0
mtime = Thu Jan 01 08:00:00 CST 1970
pZxid = 0x28d
cversion = 11
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 0
numChildren = 13
```

### get

获取当前节点的数据值

ps:若当前节点没有值,则第一行为空

``` console
[zk: localhost:2181(CONNECTED) 0] get /

cZxid = 0x0
ctime = Thu Jan 01 08:00:00 CST 1970
mZxid = 0x0
mtime = Thu Jan 01 08:00:00 CST 1970
pZxid = 0x28d
cversion = 11
dataVersion = 0
aclVersion = 0
ephemeralOwner = 0x0
dataLength = 0
numChildren = 13
```

## 创建类命令

客户端与服务端之间连接存在会话

每个会话都可以设置一个超时时间，长时间不操作则断开会话连接

心跳结束，session则过期，客户端会不断向服务端发送指令，表示客户端还在连接服务器，一旦停发送指令一段时间，服务端则会断开会话连接

session过期，则通过该session创建的所有临时节点znode将会删除

心跳机制：客户端向服务端的ping包请求

### create

通过create命令可以创建永久节点和临时节点

> create [-s] [-e] path data acl

>create 创建命令
>-s 创建一个有序节点
>-e 创建一个临时节点
>path 创建路径
>data 节点数据
>acl 节点权限

- 创建一个永久节点

``` console
[zk: localhost:2181(CONNECTED) 0] create /aquaman aquamanData
Created /aquaman
```

- 创建一个临时节点

``` console
[zk: localhost:2181(CONNECTED) 0] create -e /aquaman/temp tempData
Created /aquaman/temp
```

- 创建一个永久并有序的节点

执行完命令后，可以看到后面自动补上了“0000000001”，在次执行create -s命令发现是递增的效果

``` console
[zk: localhost:2181(CONNECTED) 0] create -s /aquaman/seq seqData
Created /aquaman/seq0000000001
[zk: localhost:2181(CONNECTED) 1] create -s /aquaman/seq seqData2
Created /aquaman/seq0000000002
[zk: localhost:2181(CONNECTED) 2] ls /aquaman
[seq0000000001, seq0000000002]
```

## 监听类命令

## 权限类命令