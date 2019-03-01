package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author kukukakiki
 * @since 2019-03-01
 */
public interface IUserService extends IService<User>, UserDetailsService {

    @Override
    User loadUserByUsername(String account) throws UsernameNotFoundException;
}
