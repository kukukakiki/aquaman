package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.mapper.UserMapper;
import com.aquaman.security.admin.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author kukukakiki
 * @since 2019-03-01
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User loadUserByUsername(String account) throws UsernameNotFoundException {
        return userMapper.loadUserByAccount(account);
    }

    @Override
    public IPage<User> page(UserQuery query) {
        return userMapper.selectPage(query, query .instanceQueryWrapper());
        // 需要判断入参属性非空，否则会查询空字符串
//        IPage<User> userIPage = userMapper.selectPage(query, new QueryWrapper<User>()
//                .eq(StringUtils.isNotEmpty(query.getAccount()),"account", query.getAccount())
//                .eq(query.getStatus() != null, "status", query.getStatus()));
//        return userIPage;
    }

    @Override
    public void updateLoginTime(Long id) {
        userMapper.updateLoginTime(id);
    }
}
