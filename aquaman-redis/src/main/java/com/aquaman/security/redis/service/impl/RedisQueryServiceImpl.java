package com.aquaman.security.redis.service.impl;

import com.aquaman.security.redis.service.RedisQueryService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/8
 * @since 2019/8/8
 */
@Service
public class RedisQueryServiceImpl<T> implements RedisQueryService {


    @Resource(name="stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name="redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public boolean existsByKey(String key) {
        return redisTemplate.hasKey(key);
    }

    @Override
    public T queryByKey(String key) {
        T t = (T)redisTemplate.opsForValue().get(key);
        return t;
    }

    @Override
    public Long queryExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }
}
