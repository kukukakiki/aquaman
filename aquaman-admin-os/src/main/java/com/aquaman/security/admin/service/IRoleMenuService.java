package com.aquaman.security.admin.service;

import com.aquaman.security.admin.entity.domain.RoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wei wang
 * @since 2019-03-05
 */
public interface IRoleMenuService extends IService<RoleMenu> {

    /**
     * 通过roleId集合查询菜单集合
     * @param roleIds
     */
    List<String> findMenuIdsByRoleIds(List<Long> roleIds);

    /**
     * 通过roleId查询菜单
     * @param roleId
     */
    RoleMenu findMenuIdsByRoleId(Long roleId);
}
