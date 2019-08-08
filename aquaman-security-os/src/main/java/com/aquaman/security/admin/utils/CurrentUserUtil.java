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
package com.aquaman.security.admin.utils;

import com.aquaman.security.admin.entity.dto.CurrentLoginUserDTO;
import com.aquaman.security.common.util.JSONUtil;
import com.aquaman.security.redis.service.RedisQueryService;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import com.aquaman.security.admin.handler.login.LoginSuccessHandler;

/**
 * 当前登陆用户
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/31
 * @since 2019/3/31
 */
@UtilityClass
@Slf4j
public class CurrentUserUtil {

    /**
     * 获取当前登陆用户,用户登录存放Redis(具体实现查阅{@link LoginSuccessHandler}),用户名作为Redis的key,值为{@link CurrentLoginUserDTO}对象
     * @return
     */
    public static CurrentLoginUserDTO getLoginUserInfo() {
        RedisQueryService<String> redisQueryService = SpringUtil.getBean(RedisQueryService.class);
        String account =  (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        CurrentLoginUserDTO dto = null;
        String jsonToken =  redisQueryService.queryByKey(account);
        Long expire = redisQueryService.queryExpire(account, TimeUnit.SECONDS);
        // token过期时间
        log.info("Redis中key的过期时间{}", expire);
        if(StringUtils.isNotEmpty(jsonToken)) {
            try {
                dto = JSONUtil.resolverObjectIncludeArrayByJSON(jsonToken, CurrentLoginUserDTO.class);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        } else {
            // TODO 未能在redis中查找到到当前登录用户
        }
        return dto;
    }
}
