package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.DeptUser;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wei wang
 * @since 2019-05-05
 */
@Data
@Deprecated
public class DeptUserQuery extends PageQuery<DeptUser> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long deptId;

    private String userIds;

    private String permissionCode;

    private Long gmtCreate;

    private Long gmtModify;


}
