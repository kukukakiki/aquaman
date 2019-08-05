package com.aquaman.sceutiry.redis;

import com.aquaman.security.redis.service.RedisOperationsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;
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
    private RedisOperationsService redisOperationsService;

    @Test
    public void test(){
        redisOperationsService.addLink("test111", "测试一下可否");
    }
}