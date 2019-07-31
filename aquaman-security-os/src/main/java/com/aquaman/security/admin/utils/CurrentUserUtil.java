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

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.experimental.UtilityClass;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 当前登陆用户
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/31
 * @since 2019/3/31
 */
@UtilityClass
public class CurrentUserUtil {

    private ConcurrentHashMap<String, User> loginUserMap = new ConcurrentHashMap<>();

    /**
     * 获取当前登陆用户
     * TODO 改造编号201903311114
     * @return
     */
    public static User getLoginUserInfo() {
        IUserService userService = SpringUtil.getBean(IUserService.class);
        String account =  (String) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
        User loginUser = loginUserMap.get(account);
        if(loginUser != null) {
            return loginUser;
        }
        loginUser =  userService.loadUserByUsername(account);
        if(loginUser != null){
            loginUserMap.put(loginUser.getAccount(), loginUser);
        }
        return loginUser;
    }
}
