## 异常信息收集

### 问题1

#### 异常信息

    java.net.ConnectException: Connection refused
    
#### 异常排查

    此问题主要出现于，dubbo链接zookeeper失败导致，所以需要排查dubbo配置zookeeper环节是否正确规范

#### 异常解决

1、注解方式时，配置注册中心信息如下，主要观察设置address属性时

``` java
@Bean
public RegistryConfig registryConfig() {
    RegistryConfig registryConfig = new RegistryConfig();
    registryConfig.setProtocol("zookeeper");
    registryConfig.setAddress("zookeeper://ip:端口");
    registryConfig.setPort(2181);
    return registryConfig;
}
``` 

### 问题2

#### 异常信息

    Failed to register dubbo://...省略异常栈

#### 异常排查

    这里的问题主要是服务端的zookeeper版本和客户端的zookeeper版本不同

#### 异常解决

1、zookeeper链接框架jar版本需要与zookeeper版本匹配，本系统zookeeper是3.4.x

``` pom
    <dependency>
        <groupId>org.apache.curator</groupId>
        <artifactId>curator-recipes</artifactId>
        <version>2.12.0</version>
    </dependency>
    <dependency>
        <groupId>org.apache.dubbo</groupId>
        <artifactId>dubbo-spring-boot-starter</artifactId>
        <version>2.7.3</version>
    </dependency>
``` 