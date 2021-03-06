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
package com.aquaman.security.admin;

import com.aquaman.security.admin.utils.SpringUtil;
import com.aquaman.security.common.config.DemoApplicationRunner;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
// import org.springframework.kafka.annotation.EnableKafka;

/**
 * 引导类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/26
 * @since 2019/2/26
 */
@MapperScan("com.aquaman.security.admin.mapper")
@SpringBootApplication
@ComponentScan(basePackages = {"com.aquaman.security"})
@EnableDubbo(scanBasePackages = "com.aquaman.security.admin.remote")
// @EnableKafka
public class AquamanAdminOsApplication {

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(AquamanAdminOsApplication.class, args);
        SpringUtil.setApplicationContext(applicationContext);
    }

//    @Bean
//    public DemoApplicationRunner demoApplicationRunner() {
//        return new DemoApplicationRunner("Aquaman");
//    }

}
