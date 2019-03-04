package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.Role;
import com.aquaman.security.admin.entity.query.RoleQuery;
import com.aquaman.security.admin.mapper.RoleMapper;
import com.aquaman.security.admin.service.IRoleService;
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
 * @since 2019-03-04
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public IPage<Role> page(RoleQuery query) {
        return roleMapper.selectPage(query, query.instanceQueryWrapper());
    }
}