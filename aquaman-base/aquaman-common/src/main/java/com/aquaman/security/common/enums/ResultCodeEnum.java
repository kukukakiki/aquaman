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
package com.aquaman.security.common.enums;

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


    /**------------------------------业务成功编码【0***】------------------------------------------*/

    SUCCESS("0000", "操作成功"),

    LOGOUT_SUCCESS("0000", "登出成功"),

    /**------------------------------用户管理业务异常编码【1***】------------------------------------------*/

    USER_NOT_LOGIN("1000", "用户未登录"),

    USER_ACCOUNT_REPEAT("1001", "用户名称不能重复"),

    USER_INVALID_TOKEN("1002", "用户名Token失效"),

    USER_ADD_ERROR("1003","用户新增异常"),

    USER_UPDATE_ERROR("1004","用户修改异常"),

    USER_DELETE_ERROR("1005","用户删除异常"),

    /**------------------------------业务处理异常编码【9***】------------------------------------------*/

    UNKNOWN_ERROR("9999", "未知异常，请联系管理员"),

    PRINCIPAL_TO_USER_CONVERSION("9001","底层数据转换异常"),

    LOGIN_PASSWORD_ERROR("9002", "用户名密码错误"),

    INERT_LOGIN_TIME_ERROR("9003", "记录用户登录时间异常"),

    /**------------------------------角色业务异常编码【3***】------------------------------------------*/

    ROLE_SAVE_ERROR("3001", "角色新增异常"),

    ROLE_DELETE_ERROR("3005", "角色删除异常"),

    ROLE_UPDATE_ERROR("3006", "角色修改异常"),

    ROLE_MENU_SAVE_ERROR("3002", "角色权限对应关系新增异常"),

    ROLE_GET_BY_ID_ERROR("3003", "角色根据ID获取详情异常"),

    ROLE_MENU_UPDATE_ERROR("3004", "角色权限对应关系修改异常"),

    /**------------------------------菜单业务异常编码【4***】------------------------------------------*/

    MENU_GET_BY_ID_ERROR("4001", "根据菜单ID获取菜单详情异常"),

    MENU_GET_ERROR("4002", "根据查询条件获取菜单详情异常"),

    MENU_SAVE_ERROR("4003", "菜单新增异常"),

    MENU_UPDATE_ERROR("4004", "菜单修改异常"),

    MENU_DELETE_ERROR("4005", "菜单删除异常"),

    MENU_GET_ENUM_KEY_LEVEL_MAP_ERROR("4006", "获取菜单枚举中编码和级别map异常"),

    /**------------------------------部门业务异常编码【5***】------------------------------------------*/

    DEPT_TREE_GET_ALL_ERROR("5001", "获取部门树异常"),

    DEPT_SAVE_ERROR("5002", "部门新增异常"),

    DEPT_UPDATE_ERROR("5003", "部门修改异常"),

    /**------------------------------字典业务异常编码【6***】------------------------------------------*/

    DICTIONARY_GROUP_DELETE_HAVE_ITEM_ERROR("6001", "字典组删除时不能含有字典项"),
    ;

    ResultCodeEnum(String code, String msg){
        this.code = code;
        this.msg = msg;
    }

    /**
     * 编码
     */
    private String code;

    /**
     * 内容
     */
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

    /**
     * 枚举转换成map对象
     * @return
     */
    public static HashMap<String , String> toMap(){
        HashMap<String, String> map = new HashMap();
        for(ResultCodeEnum resultCodeEnum : ResultCodeEnum.values()){
            map.put(resultCodeEnum.getCode(), resultCodeEnum.getMsg());
        }
        return  map;
    }

    /**
     * 通过code获取错误内容
     * @param code
     * @return
     */
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
