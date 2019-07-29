/*
MIT License

Copyright (c) 2019 wei wang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.aquaman.security.admin.entity.vo;

import com.aquaman.security.common.enums.ResultCodeEnum;
import com.aquaman.security.common.view.BaseView;
import com.fasterxml.jackson.annotation.JsonView;
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
    @JsonView(BaseView.class)
    private String code;

    /**
     * 返回信息
     */
    @JsonView(BaseView.class)
    private String msg;

    /**
     * 返回数据
     */
    @JsonView(BaseView.class)
    private T result;

    public ResultVO() {
        super();
    }

    public ResultVO(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
    }

    public ResultVO(ResultCodeEnum resultCodeEnum, T result) {
        this.code = resultCodeEnum.getCode();
        this.msg = resultCodeEnum.getMsg();
        this.result = result;
    }

    public ResultVO(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResultVO(String code, String msg, T result) {
        this.code = code;
        this.msg = msg;
        this.result = result;
    }
}
