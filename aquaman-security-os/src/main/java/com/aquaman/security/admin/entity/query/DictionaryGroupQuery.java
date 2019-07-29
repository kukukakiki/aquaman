package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.DictionaryGroup;
import lombok.Data;
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
@Accessors(chain = true)
public class DictionaryGroupQuery extends PageQuery<DictionaryGroup> implements Serializable  {

    private static final long serialVersionUID = -2597302103935306480L;

    /**
     * 主键ID
     */
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
    private String remark;

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
