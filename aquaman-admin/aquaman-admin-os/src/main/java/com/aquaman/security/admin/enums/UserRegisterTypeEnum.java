package com.aquaman.security.admin.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;

/**
 * 用户注册类型
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/4
 * @since 2019/3/4
 */
public enum UserRegisterTypeEnum {

    /**
     * 网页
     */
    WEB("web", "网页"),

    /**
     * 微信
     */
    WEIXIN("weixin", "微信"),

    /**
     * 支付宝
     */
    ALIPAY("alipay", "支付宝"),

    /**
     * GitHub
     */
    GITHUB("github", "GitHub")
    ;

    UserRegisterTypeEnum(String value, String desc){
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

    /**
     * 注册渠道枚举转map
     */
    public static HashMap<String , String> toMap(){
        HashMap<String, String> map = new HashMap();
        for(UserRegisterTypeEnum userTypeEnmu : UserRegisterTypeEnum.values()){
            map.put(userTypeEnmu.getValue(), userTypeEnmu.getDesc());
        }
        return  map;
    }
}
