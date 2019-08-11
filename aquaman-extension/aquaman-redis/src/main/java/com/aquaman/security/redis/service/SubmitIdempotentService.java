package com.aquaman.security.redis.service;

/**
 *
 */
public interface SubmitIdempotentService {

    /**
     * 创建UUID并保存至redis中
     * @return
     */
    String createIdempotentUUIDToRedis();

    /**
     * 通过UUID查询redis是否存在
     * @param uuid
     */
    void checkIdempotentUUID(String uuid);

}
