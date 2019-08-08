package com.aquaman.security.redis.config;

import io.lettuce.core.ReadFrom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * 通过Lettuce方式连接redis
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/5
 * @since 2019/8/5
 */
@Configuration
@Order(Ordered.HIGHEST_PRECEDENCE)
public class LettuceConnectionRedisConfig {

    @Autowired
    private RedisProperties redisProperties;

    /**
     * redis连接工厂
     * @return
     */
    /*@Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        // 写主,读从设置
        *//*LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
        .readFrom(ReadFrom.SLAVE_PREFERRED)
        .build();*//*
        // 哨兵方式
        *//*RedisSentinelConfiguration sentinelConfig = new RedisSentinelConfiguration();
        sentinelConfig.setPassword("leerzoom213");
        sentinelConfig.sentinel("101.200.38.213", 6379);*//*
        // 普通方式
        RedisStandaloneConfiguration serverConfig = new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort());
        serverConfig.setPassword(redisProperties.getPassword());
        redisProperties.getLettuce().getPool();
        return new LettuceConnectionFactory(serverConfig);
    }*/

}
