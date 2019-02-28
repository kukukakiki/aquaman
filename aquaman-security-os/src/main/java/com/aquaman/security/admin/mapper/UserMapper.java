/*
MIT License

Copyright (c) 2019 wei wang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
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