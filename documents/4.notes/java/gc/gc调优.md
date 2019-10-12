## 1、JVM内存结构


![](https://github.com/kukukakiki/aquaman/blob/master/documents/4.notes/java/gc/images/JVM%E5%86%85%E5%AD%98%E7%BB%93%E6%9E%84.png?raw=true)

### 1.1、运行时数据区示意图

![](https://github.com/kukukakiki/aquaman/blob/master/documents/4.notes/java/gc/images/%E8%BF%90%E8%A1%8C%E6%97%B6%E6%95%B0%E6%8D%AE%E5%8E%BB.png?raw=true)

### 1.2、程序计数器PC Register

   JVM支持多线程同时执行，每一个线程都有自己的PC Register，线程正在执行的方法叫做当前方法，如果是Java代码，PC Register
里面存放的就是当前正在执行的***指令地址***，如果是C代码，则为空。

### 1.3、虚拟机栈JVM Stacks

   JAVA虚拟机栈（Java Virtual Machine Stacks）是线程私有的，它的生命周期与线程相同。虚拟机栈描述的是Java方法执行的
内存模型：每个方法在执行同时都会创建一个栈帧，用于存储局部变量表、操作数栈、动态链接、方法出口等信息。每一个方法调用直至
执行完成的过程，就对应着一个栈帧在虚拟机栈中入栈和出栈的过程。当程序发生异常打印的异常信息就是虚拟机栈

### 1.4、堆Heap

   Java堆（Java Heap）是Java虚拟机管理的内存中最大的一块。堆是被所有的线程共享的一块内存区域，在虚拟机启动时创建。此
内存区域唯一目的就是存放对象实例，几乎所有的对象实例都在这里分配内存。

   Java堆可以处于物理上不连续的内存空间中，只要逻辑上是连续的即可

### 1.5、方法区Method Area

   方法区与Java堆一样，是各个线程共享的内存区域，它用于存储已被虚拟机加载的类信息、常量、静态变量、即时编译后的代码等数。
虽然Java虚拟机规范把方法区描述为堆的一个逻辑部分，但是它却叫做Non-Heap（非堆），目的是与Java虚拟机堆区分开。

   JDK8就是：
   
   JDK6、7就是：
   
#### 1.6、常量池

## 2、非堆区

### 2.1、Metaspace

   Class、Package、Method、Field、字节码、常量池、符号引用等

### 2.2、CCS

   32为短指针的Class

### 2.3、CodeCache

   JIT编译后的本地代码、JNI使用的C代码

### 2.4、本地方法栈 

## 3、垃圾回收算法

### 3.1、思想

   枚举更节点，做可达性分析

### 3.2、根结点

   类加载器、Thread、虚拟机栈的本地变量表、static成员、常量引用、本地方法栈的变量等等
   
### 3.3、可达性示意图

其中根结点引用了对象1、2、3，对象4和5并不可达，所以垃圾回收启动时，将会回收对象4和5

![](https://github.com/kukukakiki/aquaman/blob/master/documents/4.notes/java/gc/images/%E5%8F%AF%E8%BE%BE%E6%80%A7%E7%A4%BA%E6%84%8F%E5%9B%BE.png?raw=true)

### 3.4、标记清除

#### 3.4.1、算法

   算法分为"标记"和"清除"两个阶段：首先标记出所有需要回收的对象，在标记完成后统一回收所有。

#### 3.4.2、优缺点

   效率不高。标记和清除两个过程效率都不高

   产生碎片。碎片太多会导致提前GC。

### 3.5、复制

#### 3.5.1、算法

   它将可用的内存空间划分为大小相同的两块，每一次只使用其中的一块。当这一块的内存用完了，就将还存活着的对象复制到另外一块上面，然后把已使用
过的内存空间一次清理掉。

#### 3.5.2、优缺点

   实现简单、运行高率、但是空间利用率低。

### 3.6、标记整理

#### 3.6.1、算法

   标记过程仍然和"标记清除"算法一样，但是后续步骤不是直接对可回收对象进行清理，而是让存活对象向一端移动，然后直接清理掉端边界以外的内存。

#### 3.6.2、优缺点

   没有内存碎片，但是整理内存比较耗时。

### 3.7、分带垃圾回收

Young区采用复制算法

Old区采用标记清除或标记整理

## +—说明

冒号后面的+-表示以上如下

+：启动

—：禁用

## 参数说明

    // 启用打印GC日志
    -XX:+PrintGCDetails
    // 启用GC时间 
    -XX:+PrintGCTimeStamps 
    // 启用GC日期
    -XX:+PrintGCDateStamps 
    // 启用记录gc日志的路径文件
    -Xloggc:/use/local/aquaman-admin-os-gc.log
    // 启用发生GC时把整个堆的情况输出打印
    -XX:PrintHeapAtGC 
    // 启用发生GC时打印yung区对象年龄分布信息
    -XX:+PrintTenuringDistribution
    // 启用Young区(年轻代)进升到Old区(老年代)需要6级
    -XX:MaxTenuringThreshold=6
    // 启用压缩类指针CCS
    -XX:+UseCompressedClassPointers
    // 启用压缩对象指针
    -XX:+UseCompressedClassPointers
    // 启用新生代GC
    -XX:+UseParNewGC

## 启用三种GC模式

### ParallelGC（JDK8默认）

    -XX:+UseParallelGC

### ConcMarkSweepGC

    -XX:+UseConcMarkSweepGC


### G1GC

    -XX:+UseG1GC


## 以上配置到Tomcat启动配置中

### Idea

Edit Configurations... --> Spring Boot --> Configuration页签 --> Environment中的VM options项

### Tomcat

进入Tomcat目录

    $>vi ./bin/catalina.sh
    
编辑catalina.sh文件

    JAVA_OPTS="-XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+PrintGCDateStamps -Xloggc:/aquaman/aquaman-admin/gclog/aquaman-admin-os-gc.log"

### Spring Boot

## 三种GC解析

### ParallelGC（JDK8默认）

示例：aquaman-admin-os-parallelgc.log

### ConcMarkSweepGC

### G1GC（JDK9默认）

