package com.aquaman.security.redis.service.impl;

import com.aquaman.security.redis.service.RedisOperationsService;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/5
 * @since 2019/8/5
 */
@Service
public class RedisOperationsServiceImpl implements RedisOperationsService {


    @Resource(name="redisTemplate")
    private RedisTemplate<String, String> listOps;

    @Override
    public void addLink(String userId, String value) {
        listOps.opsForValue().set(userId, value);
    }
}
