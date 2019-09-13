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
package com.aquaman.security.admin.remote;

import com.aquaman.security.admin.api.remote.UserRemote;
import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.mapper.UserMapper;
import com.aquaman.security.common.util.JSONUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-09-12
 * @since 2019-09-12
 */
@Service
public class UserRemoteImpl implements UserRemote {

    @Autowired
    private UserMapper userMapper;

    @Override
    public String queryUserByAccount(String account) {
        User user = userMapper.loadUserByAccount(account);
        try {
            return JSONUtil.objectToJSONString(user);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
