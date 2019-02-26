package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.endity.domain.User;
import com.aquaman.security.admin.mapper.UserMapper;
import com.aquaman.security.admin.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

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
}
