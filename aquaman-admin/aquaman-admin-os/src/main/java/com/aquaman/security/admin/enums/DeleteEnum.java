package com.aquaman.security.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

/**
 * 请填写类注释
 *
 * @author wei.wang
 * @since 2019年09月03日
 */
public enum DeleteEnum {

    /**
     * 启用
     */
    NO(0, "否"),

    /**
     * 停用
     */
    YES(1, "是"),
    ;

    DeleteEnum(int value, String desc){
        this.value = value;
        this.desc = desc;
    }

    /**
     * mybatis-plus通用枚举，标记数据库存的值是value
     */
    // @EnumValue
    private int value;

    /**
     * 描述
     */
    private String desc;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @JsonValue
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    /**
     * 是否删除枚举转map
     */
    public static HashMap<Integer , String> toMap(){
        HashMap<Integer, String> map = new HashMap();
        for(DeleteEnum deleteEnum : DeleteEnum.values()){
            map.put(deleteEnum.getValue(), deleteEnum.getDesc());
        }
        return  map;
    }
}
