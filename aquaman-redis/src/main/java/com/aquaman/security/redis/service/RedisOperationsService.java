package com.aquaman.security.redis.service;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/5
 * @since 2019/8/5
 */
public interface RedisOperationsService {

    void addLink(String userId, String value);

    void addLink(String userId, Object value);
}
