package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.Role;
import com.aquaman.security.admin.enums.StatusEnum;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wei wang
 * @since 2019-03-04
 */
@Data
public class RoleQuery extends PageQuery<Role> implements Serializable {

    private static final long serialVersionUID = 8559574684768622549L;

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
