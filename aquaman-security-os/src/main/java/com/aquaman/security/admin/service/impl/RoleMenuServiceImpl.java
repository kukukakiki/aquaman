package com.aquaman.security.admin.service.impl;

import com.aquaman.security.admin.entity.domain.RoleMenu;
import com.aquaman.security.admin.entity.query.RoleMenuQuery;
import com.aquaman.security.admin.mapper.RoleMenuMapper;
import com.aquaman.security.admin.service.IRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wei wang
 * @since 2019-03-05
 */
@Service
@Slf4j
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<String> findMenuIdsByRoleIds(List<Long> roleIds) {
        Assert.notNull(roleIds, "角色ID集合不能为空");
        RoleMenuQuery query = new RoleMenuQuery();
        // 通过角色ID集合查询所有菜单ID
        List<RoleMenu> list = roleMenuMapper.selectList(query.instanceQueryWrapper().in("role_id", roleIds));
        // 判断并且遍历查询所有的角色菜单对象
        if(CollectionUtils.isNotEmpty(list)) {
            StringBuffer sb = new StringBuffer();
            for(RoleMenu roleMenu : list){
                // 拼接菜单id
                sb.append(roleMenu.getMenuIds()).append(",");
            }
            // 返回集合
            return Arrays.asList(sb.toString().split(","));
        }
        log.warn("角色集{}，查询菜单为空", roleIds);
        return null;
    }

    @Override
    public RoleMenu findMenuIdsByRoleId(Long roleId) {
        RoleMenuQuery query = new RoleMenuQuery();
        query.setRoleId(roleId);
        RoleMenu roleMenu = roleMenuMapper.selectOne(query.instanceQueryWrapper());
        return roleMenu;
    }
}
