package com.aquaman.security.admin.entity.query;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/27
 * @since 2019/2/27
 */
@Data
public class UserQuery implements Serializable {

    private static final long serialVersionUID = 8874511002284155516L;
    /**
     * 主键ID
     */
    private Long id;


    /**
     * 用户名
     */
    private String account;


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
    private String status;


    /**
     * 类型
     */
    private String type;


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
