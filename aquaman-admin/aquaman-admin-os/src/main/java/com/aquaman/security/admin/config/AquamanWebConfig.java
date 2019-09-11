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

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/6
 * @since 2019/3/6
 */
@Configuration
public class AquamanWebConfig {

    /**
     * 使用spring4.5+的跨域请求设置
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration originHeaders = new CorsConfiguration();
        //List<String> corsDomains = adminProperties.getSecurity().getCorsDomain();
        // 设置多域
        /*ArrayList<String> originURLs = new ArrayList<>();
        originURLs.add("http://admin.leerzoom.com:8888");*/
        //originHeaders.setAllowedOrigins(corsDomains);
        originHeaders.addAllowedOrigin("*");
        originHeaders.setAllowCredentials(true);
        originHeaders.addAllowedHeader("*");
        originHeaders.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", originHeaders);

        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        //配置CorsFilter优先级
        bean.setOrder(Integer.MIN_VALUE);
        return bean;
    }
}
