package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.Role;
import com.aquaman.security.admin.entity.domain.System;
import com.aquaman.security.admin.entity.query.RoleQuery;
import com.aquaman.security.admin.entity.query.SystemQuery;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wei wang
 * @since 2019-07-20
 */
public interface ISystemService extends IService<System> {

    /**
     * 角色分页查询
     * @param query
     * @return
     */
    IPage<System> page(SystemQuery query);

}
