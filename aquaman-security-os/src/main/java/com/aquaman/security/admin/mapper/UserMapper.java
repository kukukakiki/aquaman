package com.aquaman.security.admin.mapper;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.dto.UserFullInfoDTO;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
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
    @Deprecated
    IPage<User> findUserByPage(UserQuery query);

    /**
     * 更新最后登录时间
     * @param id
     */
    void updateLoginTime(Long id);

    /**
     * 根据部门ID分页查询用户信息
     * @param query
     * @return
     */
    IPage<User> findUserByDeptId(UserQuery query);

    UserFullInfoDTO findUserFullInfoByAccount(String account);
}
