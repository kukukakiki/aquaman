package com.aquaman.security.admin.handler.mybatisplus;

import com.aquaman.security.admin.entity.domain.User;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

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
    }

    @Override
    public void updateFill(MetaObject metaObject) {
    }
}
