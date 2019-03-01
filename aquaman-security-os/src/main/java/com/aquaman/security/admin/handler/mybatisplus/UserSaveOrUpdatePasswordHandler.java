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

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/3/1
 * @since 2019/3/1
 */
@Component
@Slf4j
public class UserSaveOrUpdatePasswordHandler implements MetaObjectHandler {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("UserSaveOrUpdatePasswordHandler insert fill ....");
        // 获取原始对象
        Object originalObj = metaObject.getOriginalObject();
        // 原始对象不为空，并且为User类型
        if(originalObj != null && originalObj instanceof User){
            User user = (User) originalObj;
            if(StringUtils.isNotEmpty(user.getPassword())){
                encodePassword(metaObject, user.getPassword());
            }
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
                encodePassword(metaObject, user.getPassword());
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
