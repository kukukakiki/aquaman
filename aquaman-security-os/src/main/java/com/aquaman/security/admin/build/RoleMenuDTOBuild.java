/*
MIT License

Copyright (c) 2019 wei wang

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.aquaman.security.admin.build;

import com.aquaman.security.admin.entity.domain.Role;
import com.aquaman.security.admin.entity.domain.RoleMenu;
import com.aquaman.security.admin.entity.dto.RoleMenuDTO;
import org.springframework.util.Assert;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/4/25
 * @since 2019/4/25
 */
public class RoleMenuDTOBuild {

    public static Role buildRoleByDTO(RoleMenuDTO roleMenuDTO) {
        Assert.notNull(roleMenuDTO, "角色，角色菜单关系DTO对象不能为空");
        Role role = new Role();
        role.setId(roleMenuDTO.getId());
        role.setName(roleMenuDTO.getName());
        role.setCode(roleMenuDTO.getCode());
        role.setStatus(roleMenuDTO.getStatus());
        return role;
    }

    public static RoleMenu buildRoleMenuByDTO(RoleMenuDTO roleMenuDTO) {
        Assert.notNull(roleMenuDTO, "角色，角色菜单关系DTO对象不能为空");
        RoleMenu roleMenu = new RoleMenu();
        roleMenu.setId(roleMenuDTO.getRoleMenuId());
        roleMenu.setRoleId(roleMenuDTO.getId());
        roleMenu.setMenuIds(roleMenuDTO.getMenuIds());
        roleMenu.setStatus(roleMenuDTO.getStatus());
        return roleMenu;
    }

    public static RoleMenuDTO buildDTOByRoleAndRoleMenu(Role role, RoleMenu roleMenu) {
        Assert.notNull(role, "角色对象不能为空");
        Assert.notNull(roleMenu, "角色菜单关系对象不能为空");
        RoleMenuDTO roleMenuDTO = new RoleMenuDTO();
        roleMenuDTO.setId(role.getId());
        roleMenuDTO.setName(role.getName());
        roleMenuDTO.setCode(role.getCode());
        roleMenuDTO.setStatus(role.getStatus());
        roleMenuDTO.setRoleMenuId(roleMenu.getId());
        roleMenuDTO.setMenuIds(roleMenu.getMenuIds());
        return roleMenuDTO;
    }
}
