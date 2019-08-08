package com.aquaman.sceutiry.redis;

import com.aquaman.security.redis.service.RedisSetService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/5
 * @since 2019/8/5
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@SpringBootConfiguration
@ComponentScan(basePackages = {"com.aquaman.security.redis"})
public class RedisTest {

    @Resource
    private RedisSetService redisOperationsService;

    @Test
    public void test(){
        redisOperationsService.setKeyValueForEver("test111", "测试一下可否");
    }

    @Test
    public void test2(){
        Object o = new Object();
        redisOperationsService.setKeyValueForEver("test111", o);
    }
}