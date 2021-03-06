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
package com.aquaman.security.admin.entity.dto;

import com.aquaman.security.admin.enums.StatusEnum;
import com.aquaman.security.admin.enums.UserRegisterTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 用户详情DTO
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019-07-19
 * @since 2019-07-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserFullInfoDTO {

    /**
     * 主键ID
     */
    private Long id;


    /**
     * 用户名
     */
    private String account;

    /**
     * 用户姓名
     */
    private String name;


    /**
     * 昵称
     */
    private String nickName;


    /**
     * 手机
     */
    private String mobile;


    /**
     * 邮箱
     */
    private String email;


    /**
     * 所属部门ID
     */
    private Long deptId;


    /**
     * 状态
     */
    private StatusEnum status;


    /**
     * 类型
     */
    private UserRegisterTypeEnum type;


    /**
     * 最后登录时间
     */
    private Long loginTime;


    /**
     * 权限Code
     */
    private String permissionCode;


    /**
     * 创建时间
     */
    private Long gmtCreate;


    /**
     * 修改时间
     */
    private Long gmtModify;
}
