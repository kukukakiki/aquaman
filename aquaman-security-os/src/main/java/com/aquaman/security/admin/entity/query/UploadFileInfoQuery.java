package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.UploadFileInfo;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author wei wang
 * @since 2019-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UploadFileInfoQuery extends PageQuery<UploadFileInfo> implements Serializable {

    private static final long serialVersionUID = -6238595223611827475L;

    /**
     * 主键ID
     */
    private Long id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    private Long fileSize;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 附件上传的来源系统ID(查询ad_system表ID)
     */
    private Long originSystemId;

    /**
     * 是否删除(0:否;1:是)
     */
    private Integer isDeleted;

    private Long createUserId;

    private String createUserAccount;

    /**
     * 创建时间
     */
    private Long gmtCreate;

    /**
     * 修改时间
     */
    private Long gmtModify;


}
