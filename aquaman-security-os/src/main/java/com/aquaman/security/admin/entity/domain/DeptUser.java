package com.aquaman.security.admin.entity.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 表结构改造后废弃,部门ID字段数据已经冗余{@link User}中
 * </p>
 *
 * @author wei wang
 * @since 2019-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_dept_user")
@Deprecated
public class DeptUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long deptId;

    private String userIds;

    private String permissionCode;

    private Long gmtCreate;

    private Long gmtModify;


}
