package com.aquaman.security.redis.config;

import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.annotation.Resource;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/5
 * @since 2019/8/5
 */
@Configuration
public class RedisTemplateConfig {

    @Resource
    private LettuceConnectionFactory lettuceConnectionFactory;

    /**
     *
     * @return
     */
    @Bean
    public RedisTemplate redisTemplate() {
        RedisTemplate redisTemplate = new RedisTemplate();
        // lettuce连接redis
        redisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //key序列化
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        //value序列化
        redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * redis字符串模板
     * @return
     */
    @Bean
    public StringRedisTemplate stringRedisTemplate() {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        // lettuce连接redis
        stringRedisTemplate.setConnectionFactory(lettuceConnectionFactory);
        //key序列化
        stringRedisTemplate.setKeySerializer(new StringRedisSerializer());
        //value序列化
        stringRedisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        stringRedisTemplate.afterPropertiesSet();
        return stringRedisTemplate;
    }
}
