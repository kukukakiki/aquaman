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

import com.fasterxml.jackson.annotation.JsonValue;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/4
 * @since 2019/3/4
 */
public enum DeptTypeEnum {

    /**
     * 集团
     */
    GROUP("group", "集团", 1),

    /**
     * 总公司
     */
    HEAD("head", "总公司", 2),

    /**
     * 分公司
     */
    BRANCH("branch", "分公司", 3),

    /**
     * 子公司
     */
    CHILD("child", "子公司", 3),

    /**
     * 部门
     */
    DEPT("dept", "部门", 4),

    /**
     * 组
     */
    TEAM("team", "组", 5),
    ;

    DeptTypeEnum(String key, String value, Integer level){
        this.key = key;
        this.value = value;
        this.level = level;
    }

    /**
     * mybatis-plus通用枚举，标记数据库存的值是value
     */
    // @EnumValue
    private String key;

    /**
     * 描述
     */
    private String value;

    /**
     * 级别
     */
    private int level;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonValue
    public String getKey() {
        return key;
    }

    public void setKey(String desc) {
        this.key = key;
    }

    /**
     * 菜单枚举转换map集合
     */
    public static HashMap<String , String> toMap(){
        HashMap<String, String> map = new HashMap();
        for(DeptTypeEnum menuTypeEnum : DeptTypeEnum.values()){
            map.put(menuTypeEnum.getKey(), menuTypeEnum.getValue());
        }
        return  map;
    }

    /**
     * 菜单枚举转换map集合
     */
    public static HashMap<String , Integer> keyLevelToMap(){
        HashMap<String, Integer> map = new HashMap();
        for(DeptTypeEnum menuTypeEnum : DeptTypeEnum.values()){
            map.put(menuTypeEnum.getKey(), menuTypeEnum.getLevel());
        }
        return  map;
    }

    /**
     * 通过级别获取菜单键， 查询不到返回空
     */
    public static String getKeyByLevel(int level) {
        for(DeptTypeEnum menuTypeEnum : DeptTypeEnum.values()){
            if(menuTypeEnum.getLevel() == level) {
                return menuTypeEnum.getKey();
            }
        }
        return null;
    }

    /**
     * 通过菜单键获取菜单级别，查询不到返回-1
     */
    public static int getLevelByKey(String key) {
        for(DeptTypeEnum menuTypeEnum : DeptTypeEnum.values()){
            if(StringUtils.equals(key, menuTypeEnum.getKey())) {
                return menuTypeEnum.getLevel();
            }
        }
        return -1;
    }
}
