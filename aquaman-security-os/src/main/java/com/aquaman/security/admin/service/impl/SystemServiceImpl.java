package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.System;
import com.aquaman.security.admin.entity.query.SystemQuery;
import com.aquaman.security.admin.mapper.RoleMapper;
import com.aquaman.security.admin.mapper.SystemMapper;
import com.aquaman.security.admin.service.ISystemService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wei wang
 * @since 2019-07-20
 */
@Service
public class SystemServiceImpl extends ServiceImpl<SystemMapper, System> implements ISystemService {

    @Autowired
    private SystemMapper systemMapper;

    @Override
    public IPage<System> page(SystemQuery query) {
        return systemMapper.selectPage(query, query.instanceQueryWrapper());
    }
}
