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
package com.aquaman.security.admin.config;

import org.apache.dubbo.common.constants.CommonConstants;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ProviderConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-09-12
 * @since 2019-09-12
 * EnableDubbo用于指定dubbo扫描(API)服务所在的包
 */
@Configuration
public class AdminDubboConfig {

    @Bean
    public ProviderConfig providerConfig() {
        return new ProviderConfig();
    }

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("aquaman-admin");
        return applicationConfig;
    }

    /**
     * 服务注册配置bean
     * 设置注册中心的使用方式
     * 设置远程地址
     * 设置端口号
     * @return
     */
    @Bean
    public RegistryConfig registryConfig() {
       RegistryConfig registryConfig = new RegistryConfig();
       registryConfig.setProtocol("zookeeper");
       registryConfig.setAddress("zookeeper://101.200.38.213:2181");
       registryConfig.setPort(2181);
       return registryConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        // 服务默认采用dubbo协议
        protocolConfig.setName(CommonConstants.DEFAULT_PROTOCOL);
        // 20880端口监听服务
        protocolConfig.setPort(20880);
        return protocolConfig;
    }
}
