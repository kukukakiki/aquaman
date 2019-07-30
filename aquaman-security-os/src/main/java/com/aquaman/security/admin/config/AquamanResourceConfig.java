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

import com.aquaman.security.admin.handler.login.LoginFailureHandler;
import com.aquaman.security.admin.handler.login.LoginSuccessHandler;
import com.aquaman.security.admin.handler.logout.LogoutSuccessHandler;
import com.aquaman.security.admin.properties.security.AquamanSecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;

/**
 * 资源服务器配置类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/26
 * @since 2019/2/26
 */
@Configuration
@EnableResourceServer
public class AquamanResourceConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private LoginSuccessHandler loginSuccessHandler;

    @Autowired
    private LoginFailureHandler loginFailureHandler;

    @Autowired
    private LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    private AquamanSecurityProperties aquamanSecurityProperties;

    /**
     * 声明一个Bean，该Bean主要用于spring security拿到用户对象password后进行matches匹配密码是否正确
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
            // 采用form方式登录
            .formLogin()
            // 登录处理URI
            .loginPage(aquamanSecurityProperties.getAuthorization().getLoginPage())
            // 登录成功处理器
            .successHandler(loginSuccessHandler)
            // 登录失败处理器
            .failureHandler(loginFailureHandler)
            .and()
            // 登出设置
            .logout()
            // 登出成功处理器
            .logoutSuccessHandler(logoutSuccessHandler)
            .and()
            // 请求授权
            .authorizeRequests()
            // 过滤OPTIONS请求
            .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
            // 过滤该URL
            .antMatchers(aquamanSecurityProperties.getAuthorization().getLoginPage(), "/file/upload").permitAll()
            // 任何请求
            .anyRequest().authenticated()
            // 取消跨站请求防护
            .and().csrf().disable();
    }
}
