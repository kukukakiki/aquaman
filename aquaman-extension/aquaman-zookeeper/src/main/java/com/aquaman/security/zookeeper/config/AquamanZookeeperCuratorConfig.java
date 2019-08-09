package com.aquaman.security.zookeeper.config;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryOneTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/9
 * @since 2019/8/9
 */
@Configuration
public class AquamanZookeeperCuratorConfig {

    @Bean
    public CuratorFramework curatorFramework() {
        RetryPolicy retryPolicy = new RetryOneTime(3000);
        CuratorFramework client = CuratorFrameworkFactory.builder()
            .connectString("")
            .sessionTimeoutMs(10000)
            .retryPolicy(retryPolicy)
            .build();
        return client;
    }
}
