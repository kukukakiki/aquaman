package com.aquaman.security.oss.aliyun.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aquaman.security.oss.aliyun.properties.AliYunOSSProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/8/7
 * @since 2019/8/7
 */
@Configuration
public class AliYunSSOConfig {

    @Autowired
    private AliYunOSSProperties properties;

    @Bean
    public OSS aliyunOSSClient() {
        return new OSSClientBuilder().build(properties.getManagement().getEndpoint(),
        properties.getManagement().getAccessKeyId(), properties.getManagement().getAccessKeySecret());
    }
}
