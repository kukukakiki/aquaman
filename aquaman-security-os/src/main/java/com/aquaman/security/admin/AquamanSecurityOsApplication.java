package com.aquaman.security.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 引导类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/26
 * @since 2019/2/26
 */
@ComponentScan("com.aquaman.security.admin")
@MapperScan("com.aquaman.security.admin.mapper")
@SpringBootApplication
public class AquamanSecurityOsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AquamanSecurityOsApplication.class, args);
    }

}
