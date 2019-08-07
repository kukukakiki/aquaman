package com.aquaman.security.admin.entity.domain;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 
 * </p>
 *
 * @author wei wang
 * @since 2019-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_dictionary_group")
public class DictionaryGroup implements Serializable {

    private static final long serialVersionUID = -7095809866821122305L;
    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 字典组编码
     */
    private String code;

    /**
     * 字典组名称
     */
    private String name;

    /**
     * 字典组类型
     */
    private String type;

    /**
     * 转改
     */
    private String status;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 权限表示（暂未启用）
     */
    private String permissionCode;

    /**
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
