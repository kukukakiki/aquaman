package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.DictionaryItem;
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
 * @since 2019-07-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class DictionaryItemQuery extends PageQuery<DictionaryItem> implements Serializable {

    private static final long serialVersionUID = -810286374312713476L;

    /**
     * 主键
     */
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
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModify;


}
