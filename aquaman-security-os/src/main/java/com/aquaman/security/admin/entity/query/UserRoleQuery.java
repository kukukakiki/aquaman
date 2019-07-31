package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.UserRole;
import com.aquaman.security.admin.enums.StatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wei wang
 * @since 2019-03-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserRoleQuery extends PageQuery<UserRole> implements Serializable {

    private static final long serialVersionUID = -571373893660883790L;

    private Long id;

    /**
     * 会员ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private String roleIds;

    /**
     * 状态
     */
    private StatusEnum status;

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
