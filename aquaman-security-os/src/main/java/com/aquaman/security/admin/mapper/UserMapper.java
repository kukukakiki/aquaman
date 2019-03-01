package com.aquaman.security.admin.mapper;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kukukakiki
 * @since 2019-03-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据account查询用户信息
     * @param account
     * @return
     */
    User loadUserByAccount(@Param("account") String account);

    /**
     * 分页查询用户信息
     * @param query
     * @return
     */
    Page<User> findUserByPage(UserQuery query);
}
