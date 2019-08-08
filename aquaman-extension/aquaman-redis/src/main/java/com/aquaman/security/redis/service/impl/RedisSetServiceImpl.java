package com.aquaman.security.redis.service.impl;

import com.aquaman.security.redis.service.RedisSetService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/5
 * @since 2019/8/5
 */
@Service
public class RedisSetServiceImpl implements RedisSetService {


    @Resource(name="stringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Resource(name="redisTemplate")
    private RedisTemplate redisTemplate;

    @Override
    public void setKeyValueForEver(String key, String value) {
        this.defaultSetKeyValue(key, value);
    }

    @Override
    public void setKeyValueForEver(String key, Object value) {
        this.defaultSetKeyValue(key, value);
    }

    @Override
    public void setKeyValue(String key, Object value, long timeout, TimeUnit unit) {
        this.defaultSetKeyValue(key, value, timeout, unit);
    }

    private void defaultSetKeyValue(String key, Object value) {
        this.validateKeyValue(key, value);
        if(value instanceof String) {
            stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
    }


    @Override
    public void setRenameKey(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    @Override
    public Boolean setRenameNewKeyNotExists(String oldKey, String newKey) {
        return redisTemplate.renameIfAbsent(oldKey, newKey);
    }

    @Override
    public Boolean setKeyForEver(String key) {
        return redisTemplate.persist(key);
    }

    @Override
    public Boolean setExpire(String key, long timeout, TimeUnit unit) {
        Assert.notNull(key, "Redis的key(键)不能为空");
        Assert.notNull(timeout, "指定过期时间长度timeout不能为空");
        Assert.notNull(unit, "指定过期时间格式unit不能为空");
        return redisTemplate.expire(key, timeout, unit);
    }

    @Override
    public Boolean setExpireByDate(String key, Date date) {
        Assert.notNull(key, "Redis的key(键)不能为空");
        Assert.notNull(date, "指定过期日期date不能为空");
        return redisTemplate.expireAt(key, date);
    }

    /**
     * 默认设置方法,更具value入参类型,动态调用{@link RedisTemplate}或{@link StringRedisTemplate}
     * @param key
     * @param value
     * @param timeout
     * @param unit
     */
    private void defaultSetKeyValue(String key, Object value, long timeout, TimeUnit unit) {
        this.validateKeyValue(key, value);
        if(value instanceof String) {
            stringRedisTemplate.opsForValue().set(key, String.valueOf(value), timeout, unit);
        } else {
            redisTemplate.opsForValue().set(key, value, timeout, unit);
        }
    }

    private void validateKeyValue(String key, Object value) {
        Assert.notNull(key, "Redis的key(键)不能为空");
        Assert.notNull(value, "Redis的value(值)不能为空");
    }
}
