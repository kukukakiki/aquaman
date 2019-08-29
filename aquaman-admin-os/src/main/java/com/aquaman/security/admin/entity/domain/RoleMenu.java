package com.aquaman.security.admin.entity.domain;

import com.aquaman.security.admin.enums.StatusEnum;
import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 角色菜单
 *
 * @author wei wang
 * @since 2019-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_role_menu")
public class RoleMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 角色ID
     */
    private Long roleId;

    /**
     * 菜单ID集合
     */
    private String menuIds;

    /**
     * 状态
     */
    private String status;

    /**
     * 权限Code
     */
    private String permissionCode;

    /**
     * 处理逻辑查阅{@link com.aquaman.security.admin.handler.mybatisplus.MyBatisPlusFillHandler}
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    /**
     * 修改时间
     */
    @TableField(update = "now()", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Long gmtModify;

    /**
     * 是否删除(0:否;1:是)
     */
    private Integer isDeleted;


}
