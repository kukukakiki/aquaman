package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.Dept;
import com.aquaman.security.admin.entity.domain.Menu;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
 * @since 2019-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DeptQuery extends PageQuery<Dept> implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    /**
     * 类型（集团、子公司、部门、小组）
     */
    private String type;

    private String status;

    /**
     * 部门地址
     */
    private String address;

    private String remark;

    private Long sort;

    private Long parentId;

    private String permissionCode;

    private Long gmtCreate;

    private Long gmtModify;


}
