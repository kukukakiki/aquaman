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
 * 
 * </p>
 *
 * @author wei wang
 * @since 2019-05-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_dept")
public class Dept implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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
