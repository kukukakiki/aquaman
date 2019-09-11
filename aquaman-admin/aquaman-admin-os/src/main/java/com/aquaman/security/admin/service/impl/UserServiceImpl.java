package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.mapper.UserMapper;
import com.aquaman.security.admin.service.IUserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
    public void updateLoginTime(Long id) {
        userMapper.updateLoginTime(id);
    }

    @Override
    public IPage<User> findUserByDeptId(Long deptId) {
        Assert.notNull(deptId, "部门ID不能为空");
        UserQuery query = new UserQuery();
        query.setDeptId(deptId);
        return userMapper.findUserByDeptId(query);
    }
}
