# Spring Boot2.X 使用Redis

[spring-data-redis:2.1.3.RELEASE官方文档](https://docs.spring.io/spring-data/redis/docs/2.1.3.RELEASE/reference/html/#why-spring-redis)

## Rrdis笔记

[redis笔记目录]()

## 连接Redis

### Lettuce

**本文系统采用的是Lettuce方式创建连接**

>Lettuce是一个基于Netty的开源连接器

### Jedis

>Jedis是一个社区驱动的连接器

### RedisTemplate

### Redis启动

如果上下文中缺省配置启动工厂,spring boot会默认采用Lettuce连接配置

```
LettuceConnectionConfiguration
    @Bean
	@ConditionalOnMissingBean(RedisConnectionFactory.class)
	public LettuceConnectionFactory redisConnectionFactory(
			ClientResources clientResources) throws UnknownHostException {
		LettuceClientConfiguration clientConfig = getLettuceClientConfiguration(
				clientResources, this.properties.getLettuce().getPool());
		return createLettuceConnectionFactory(clientConfig);
	}
}
```

