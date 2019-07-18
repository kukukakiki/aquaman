package com.aquaman.security.admin.entity.dto;

import lombok.Data;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/7/18
 * @since 2019/7/18
 */
@Data
public class RoleDTO {

    private Long id;

    /**
     * 编码
     */
    private String code;

    /**
     * 名称
     */
    private String name;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remarks;

    private String permissionCode;

    /**
     * ad_user_role表主键
     */
    private Long userRoleId;
}
