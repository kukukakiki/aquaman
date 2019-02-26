package com.aquaman.security.admin.service;

import com.aquaman.security.admin.endity.domain.User;

/**
 * 用户服务接口
 *
 * @author wei.wang
 * @since 2019年02月26日
 */
public interface UserService {

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
}
