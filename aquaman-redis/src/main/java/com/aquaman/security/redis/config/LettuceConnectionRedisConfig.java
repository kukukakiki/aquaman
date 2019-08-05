package com.aquaman.security.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

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

    /**
     * redis连接工厂
     * @return
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration("101.200.38.213", 6379);
        // redisStandaloneConfiguration.setPassword("leerzoom213");
        return new LettuceConnectionFactory(redisStandaloneConfiguration);
    }

}
