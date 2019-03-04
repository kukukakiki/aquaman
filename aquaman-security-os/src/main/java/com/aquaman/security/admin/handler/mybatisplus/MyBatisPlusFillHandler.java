package com.aquaman.security.admin.handler.mybatisplus;

import com.aquaman.security.admin.entity.domain.User;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 用于MyBatis-Plus框架自动填充功能
 * 1、用户密码加密
 * 2、操作时间设置
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/1
 * @since 2019/3/1
 */
@Component
@Slf4j
public class MyBatisPlusFillHandler implements MetaObjectHandler {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("UserSaveOrUpdatePasswordHandler insert fill ....");
        // insert操作设置gmt_create和gmt_modify时间
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String newDateTime = simpleDateFormat.format(new Date());
        // 获取原始对象
        Object originalObj = metaObject.getOriginalObject();
        // 反射获取公共属性ps：由于setFieldValByName方式无法将值设置到对象中。官网参考：https://mp.baomidou.com/guide/auto-fill-metainfo.html
        try {
            Method createMethod = originalObj.getClass().getMethod("setGmtCreate", Long.class);
            createMethod.invoke(originalObj, Long.parseLong(newDateTime));
            Method modifyMethod = originalObj.getClass().getMethod("setGmtModify", Long.class);
            modifyMethod.invoke(originalObj, Long.parseLong(newDateTime));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 原始对象不为空，并且为User类型、密码为空
        if(originalObj != null && originalObj instanceof User && StringUtils.isNotEmpty(((User) originalObj).getPassword())){
            encodePassword(metaObject, ((User) originalObj).getPassword());
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("UserSaveOrUpdatePasswordHandler update fill ....");
        // 获取原始对象
        Object originalObj = metaObject.getOriginalObject();
        // 修改方法是原始对象是MapperMethod.ParamMap类型
        if(originalObj != null && originalObj instanceof MapperMethod.ParamMap){
            MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap)originalObj;
            // 拿到param1属性对象
            Object object =  paramMap.get("param1");
            if(object != null && object instanceof User){
                User user = (User) object;
                if(StringUtils.isNotEmpty(user.getPassword())) {
                    encodePassword(metaObject, user.getPassword());
                } else {
                    this.setFieldValByName("password", null, metaObject);
                }
            }
        }

    }



    /**
     * 密码加密
     * @param metaObject
     * @return
     */
    private void encodePassword(MetaObject metaObject, String password){
        if(StringUtils.isNotEmpty(password)){
            // 新增或修改操作如果密码不为空，都需要加密明文密码
            this.setFieldValByName("password", passwordEncoder.encode(password), metaObject);
        }
    }
}
