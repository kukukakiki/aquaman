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
public enum MenuTypeEnum {

    GROUP("group", "菜单组", 1),
    MENU("menu", "菜单", 2),
    BUTTON("button", "按钮", 3),
    PAGE("page", "弹出页", 2)
    ;

    MenuTypeEnum(String key, String value, int level){
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

    private int level;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
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

    public static HashMap<String , String> toMap(){
        HashMap<String, String> map = new HashMap();
        for(MenuTypeEnum menuTypeEnum : MenuTypeEnum.values()){
            map.put(menuTypeEnum.getKey(), menuTypeEnum.getValue());
        }
        return  map;
    }

    public static String getKeyByLevel(int level) {
        for(MenuTypeEnum menuTypeEnum : MenuTypeEnum.values()){
            if(menuTypeEnum.getLevel() == level) {
                return menuTypeEnum.getKey();
            }
        }
        return null;
    }

    public static int getLevelByKey(String key) {
        for(MenuTypeEnum menuTypeEnum : MenuTypeEnum.values()){
            if(StringUtils.equals(key, menuTypeEnum.getKey())) {
                return menuTypeEnum.getLevel();
            }
        }
        return -1;
    }
}
