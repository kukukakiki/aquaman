package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.mapper.UserMapper;
import com.aquaman.security.admin.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
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
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public User loadUserByUsername(String account) throws UsernameNotFoundException {
        return userMapper.loadUserByAccount(account);
    }
}
