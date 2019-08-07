package com.aquaman.security.redis.service;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/5
 * @since 2019/8/5
 */
public interface RedisOperationsService {

    /**
     * 向redis放入key，value
     * 该方法入参都是{@link String}类型
     * @param key
     * @param value
     */
    void setKeyValue(String key, String value);

    /**
     * 向redis放入key，value
     * 入参key:{@link String}类型
     * 入参value:{@link Object}类
     * @param key
     * @param value
     */
    void setKeyValue(String key, Object value);
}
