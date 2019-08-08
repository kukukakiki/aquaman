package com.aquaman.security.redis.service;

import org.springframework.util.Assert;

import java.util.concurrent.TimeUnit;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/8
 * @since 2019/8/8
 */
public interface RedisQueryService<T> {

    /**
     * redis的Key是否存在
     * @param key
     * @return
     */
    boolean existsByKey(String key);

    /**
     * 通过key获取对象,该对象为范型
     * @param key
     * @return
     */
    T queryByKey(String key);

    /**
     * 通过Key获取对应的剩余的过期时间.时间格式为毫秒
     * @param key
     * @return
     */
    default Long queryExpire(String key){
        Assert.notNull(key, "redis的key(键)不能为空");
        return this.queryExpire(key, TimeUnit.MICROSECONDS);
    }

    /**
     * 通过Key获取对应的剩余的过期时间,指定返回时间格式
     * @param key
     * @param unit
     * @return
     */
    Long queryExpire(String key, TimeUnit unit);
}
