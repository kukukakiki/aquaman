package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.User;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kukukakiki
 * @since 2019-03-01
 */
public interface IUserService extends IService<User>, UserDetailsService {

    /**
     * 根据account查询用户信息
     * @param account
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    User loadUserByUsername(String account) throws UsernameNotFoundException;
}
