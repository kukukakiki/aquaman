package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    /**
     * 用户分页查询
     * @param query
     * @return
     */
    IPage<User> page(UserQuery query);

    /**
     * 更新最后登录时间
     * @param id
     */
    void updateLoginTime(Long id);

    /**
     * 根据部门ID分页查询用户信息
     * @param deptId
     * @return
     */
    IPage<User> findUserByDeptId(Long deptId);
}
