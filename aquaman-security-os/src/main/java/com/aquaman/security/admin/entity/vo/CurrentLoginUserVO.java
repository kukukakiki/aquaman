package com.aquaman.security.admin.entity.vo;

import lombok.Data;

/**
 * 当前登录用户
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
@Data
public class CurrentLoginUserVO {

    /**
     * 用户名
     */
    private String account;
}
