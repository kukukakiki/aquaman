package com.aquaman.security.admin.mapper;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;

import java.util.List;

/**
 * 用户Mapper
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/26
 * @since 2019/2/26
 */
public interface UserMapper {

    /**
     * 根据ID删除具体数据
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入数据
     * @param user
     * @return
     */
    int insert(User user);

    /**
     * 插入非空数据
     * @param user
     * @return
     */
    int insertSelective(User user);

    /**
     * 根据ID查询具体数据
     * @param id
     * @return
     */
    User selectByPrimaryKey(Long id);

    /**
     * 根据主键更新非空字段
     * @param user
     * @return
     */
    int updateByPrimaryKeySelective(User user);

    /**
     * 根据主键更新数据
     * @param user
     * @return
     */
    int updateByPrimaryKey(User user);

    /**
     * 根据用户名（Account）查询用户
     * @param account
     * @return
     */
    User loadUserByAccount(String account);

    /**
     * 根据query对象查询用户集合
     * @param query
     * @return
     */
    List<User> findUserByPage(UserQuery query);
}