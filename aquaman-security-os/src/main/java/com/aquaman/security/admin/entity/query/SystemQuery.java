package com.aquaman.security.admin.entity.query;

import com.aquaman.security.admin.entity.domain.System;
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
 * @since 2019-07-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class SystemQuery extends PageQuery<System> implements Serializable {

    private static final long serialVersionUID = 7746064556980496975L;

    private Long id;

    /**
     * 系统编码
     */
    private String code;

    /**
     * 系统名称
     */
    private String name;

    /**
     * 域名
     */
    private String domain;

    /**
     * 协议http,https
     */
    private String deal;

    /**
     * 系统端口
     */
    private Integer port;

    /**
     * 服务器ip
     */
    private String serverIp;

    /**
     * 语言(Java,C#,PHP...)字典表获取
     */
    private String language;

    /**
     * 系统版本
     */
    private String version;

    private String status;

    /**
     * 运行状态
     */
    private Integer runStatus;

    private String permissionCode;

    private Long gmtCreate;

    private Long gmtModify;

    /**
     * 服务器系统
     */
    private String serverSystem;

    private String remarks;

    /**
     * 是否删除(0:否;1:是)
     */
    private Integer isDeleted;


}
