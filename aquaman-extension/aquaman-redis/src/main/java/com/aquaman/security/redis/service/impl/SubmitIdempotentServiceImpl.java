/*
MIT License

Copyright (c) 2019 wei wang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.aquaman.security.redis.service.impl;

import com.aquaman.security.redis.service.RedisDeleteService;
import com.aquaman.security.redis.service.RedisQueryService;
import com.aquaman.security.redis.service.RedisSetService;
import com.aquaman.security.redis.service.SubmitIdempotentService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * 提交幂等服务实现类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-08-11
 * @since 2019-08-11
 */
@Service
public class SubmitIdempotentServiceImpl implements SubmitIdempotentService {

    /**
     * 幂等UUID存入redis前缀
     */
    private static final transient String IDEMPOTENT_PREFIX = "idempotent_";

    @Autowired
    private RedisSetService redisSetService;

    @Autowired
    private RedisQueryService<String> redisQueryService;

    @Autowired
    private RedisDeleteService redisDeleteService;

    @Override
    public String createIdempotentUUIDToRedis() {
        String uuid = UUID.randomUUID().toString();
        String key = IDEMPOTENT_PREFIX + uuid;
        redisSetService.setKeyValueForEver(uuid, uuid);
        return key;
    }

    @Override
    public void checkIdempotentUUID(String uuid) {
        if(!redisQueryService.existsByKey(uuid)) {
            // 抛出提交重复异常
        }
        if(!redisDeleteService.deleteByKey(uuid)) {
            // 抛出提交重复异常
        }
    }
}
