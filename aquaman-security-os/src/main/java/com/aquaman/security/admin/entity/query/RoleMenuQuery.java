package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.RoleMenu;
import com.aquaman.security.admin.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 角色菜单
 *
 * @author wei wang
 * @since 2019-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class RoleMenuQuery extends PageQuery<RoleMenu> implements Serializable {

    private static final long serialVersionUID = 7104589547762390619L;

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
    private StatusEnum status;

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
