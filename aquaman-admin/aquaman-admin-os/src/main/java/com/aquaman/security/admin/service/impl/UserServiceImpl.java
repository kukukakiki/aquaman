package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.mapper.UserMapper;
import com.aquaman.security.admin.service.IUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
