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
package com.aquaman.security.admin.enums;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * 统一返回编码枚举
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
public enum  ResultCodeEnum {

    SUCCESS("0000", "操作成功"),
    LOGOUT_SUCCESS("0001", "登出成功"),

    USER_NOT_LOGIN("1000", "用户未登录"),
    UNKNOWN_ERROR("9999", "未知异常，请联系管理员"),
    PRINCIPAL_TO_USER_CONVERSION("9001","底层数据转换异常"),
    LOGIN_PASSWORD_ERROR("9002", "用户名密码错误"),
    ;

    ResultCodeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    private String code;

    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static HashMap<String , String> toMap(){
        HashMap<String, String> map = new HashMap();
        for(ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()){
            map.put(resultCodeEnum.getCode(), resultCodeEnum.getMsg());
        }
        return  map;
    }

    public static String getMsgByCode(String code){
        HashMap<String, String> map = new HashMap();
        for(ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()){
            if(StringUtils.equals(resultCodeEnum.getCode(), code)){
                return resultCodeEnum.getMsg();
            }
        }
        return null;
    }
}
