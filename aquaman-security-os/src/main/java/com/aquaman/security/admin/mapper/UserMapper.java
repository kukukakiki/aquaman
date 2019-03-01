package com.aquaman.security.admin.mapper;

import com.aquaman.security.admin.entity.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Flush;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author kukukakiki
 * @since 2019-03-01
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据account查询用户信息
     * @param account
     * @return
     */
    // @Select("select id, account, password, nick_name, mobile, email, dept_id, status, type, login_time, permission_code, gmt_create, gmt_modify where account = #{account}")
    User loadUserByAccount(@Param("account") String account);
}
