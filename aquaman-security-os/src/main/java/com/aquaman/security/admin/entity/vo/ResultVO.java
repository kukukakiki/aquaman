package com.aquaman.security.admin.entity.vo;

import com.aquaman.security.admin.enums.ResultCodeEnum;
import lombok.Data;

/**
 * 统一返回VO
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
@Data
public class ResultVO<T> {

    /**
     * 返回编码
     */
    private String code;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 返回数据
     */
    private T data;

    public ResultVO() {
        super();
    }

    public ResultVO(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
    }

    public ResultVO(ResultCodeEnum resultCodeEnum, T data) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
        this.data = data;
    }

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
