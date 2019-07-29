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
@TableName("ad_dictionary_item")
public class DictionaryItem implements Serializable {

    private static final long serialVersionUID = 5084343400928259893L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 父表(ad_dictionary_group)主键ID
     */
    private Long groupId;

    /**
     * 字典项编码
     */
    private String code;

    /**
     * 字典项名称
     */
    private String name;

    /**
     * 字典项值
     */
    private String value;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

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


}
