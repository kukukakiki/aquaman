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
package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.mapper.UserMapper;
import com.aquaman.security.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

/**
 * 用户服务实现类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/26
 * @since 2019/2/26
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(User user) {
        Assert.notNull(user.getId(), "用户ID不能为空");
        Assert.notNull(user.getAccount(), "用户名不能为空");
        encodePassword(user);
        return userMapper.insert(user);
    }

    @Override
    public int insertSelective(User user) {
        Assert.notNull(user.getId(), "用户ID不能为空");
        Assert.notNull(user.getAccount(), "用户名不能为空");
        encodePassword(user);
        return userMapper.insertSelective(user);
    }

    @Override
    public User selectByPrimaryKey(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(User user) {
        Assert.notNull(user.getId(), "用户ID不能为空");
        Assert.notNull(user.getAccount(), "用户名不能为空");
        encodePassword(user);
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public int updateByPrimaryKey(User user) {
        Assert.notNull(user.getId(), "用户ID不能为空");
        Assert.notNull(user.getAccount(), "用户名不能为空");
        encodePassword(user);
        return userMapper.updateByPrimaryKey(user);
    }



    /**
     * 密码加密
     * @param user
     * @return
     */
    private User encodePassword(User user){
        if(StringUtils.isNotBlank(user.getPassword())){
            // 将密码加密（该加密方式使用spring security的PasswordEncoder）后存入数据库中
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return user;
    }

    /**
     * spring security自定义登录需要通过该方法验证登录
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return userMapper.loadUserByAccount(s);
    }

    @Override
    public List<User> findUserByPage(UserQuery query) {
        return userMapper.findUserByPage(query);
    }
}
