package com.aquaman.security.admin.entity.query;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author wei wang
 * @since 2019-07-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class LogQuery implements Serializable {

    private static final long serialVersionUID = 2343144160055379253L;

    private Long id;

    /**
     * 系统ID
     */
    private Long systemId;

    /**
     * 请求地址
     */
    private String uri;

    /**
     * 目标对象
     */
    private String targetClass;

    /**
     * 目标方法
     */
    private String targetMethod;

    /**
     * 入参
     */
    private String inParameter;

    /**
     * 出参
     */
    private String outParameter;

    /**
     * 异常栈
     */
    private String errorMsg;

    private Long operationAccountId;

    private String operationAccount;

    /**
     * 状态（成功、失败、超时）
     */
    private String status;

    /**
     * 级别（预留字段）
     */
    private String level;

    /**
     * 类型（内部、外部）
     */
    private String type;

    /**
     * 执行时间
     */
    private Long executeTime;

    /**
     * 备注
     */
    private String remark;

    private String permissionCode;

    private Long gmtCreate;

    private Long gmtModify;

    /**
     * 是否删除(0:否;1:是)
     */
    private Integer isDeleted;


}
