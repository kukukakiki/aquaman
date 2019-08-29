package com.aquaman.security.admin.entity.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2019-07-31
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("ad_upload_file_info")
public class UploadFileInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 文件名称
     */
    private String name;

    /**
     * 文件路径
     */
    private String path;

    /**
     * 文件后缀
     */
    private String suffix;

    /**
     * 文件大小
     */
    private Long fileSize;

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
    @TableField(fill = FieldFill.INSERT)
    private Long gmtCreate;

    /**
     * 修改时间
     */
    @TableField(update = "now()", fill = FieldFill.INSERT_UPDATE)
    @DateTimeFormat(pattern = "yyyyMMddHHmmss")
    private Long gmtModify;


}
