package com.aquaman.security.admin.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

/**
 * 数据库通用状态枚举类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/4
 * @since 2019/3/4
 */
public enum StatusEnum {

    START("start", "启用"),
    STOP("stop", "停用"),
    ;

    StatusEnum(String value, String desc){
        this.value = value;
        this.desc = desc;
    }

    /**
     * mybatis-plus通用枚举，标记数据库存的值是value
     */
    // @EnumValue
    private String value;

    /**
     * 描述
     */
    private String desc;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @JsonValue
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public static HashMap<String , String> toMap(){
        HashMap<String, String> map = new HashMap();
        for(StatusEnum statusEnum : StatusEnum.values()){
            map.put(statusEnum.getValue(), statusEnum.getDesc());
        }
        return  map;
    }
}
