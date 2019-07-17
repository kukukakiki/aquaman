package com.aquaman.security.admin.controller.role.menu;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.RoleMenu;
import com.aquaman.security.admin.entity.domain.UserRole;
import com.aquaman.security.admin.entity.query.RoleMenuQuery;
import com.aquaman.security.admin.entity.query.UserRoleQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.service.IRoleMenuService;
import com.aquaman.security.admin.service.IUserRoleService;
import com.aquaman.security.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色菜单设置Rest
 *
 * @author wei wang
 * @since 2019-03-05
 */
@Slf4j
@RestController
@RequestMapping("/role_menu")
public class RoleMenuController extends BaseController {

    @Autowired
    private IRoleMenuService roleMenuService;

    /**
     * 查询所有集合
     * @param query
     * @return
     */
    @GetMapping
    public ResultVO<List<RoleMenu>> get(RoleMenuQuery query){
        List<RoleMenu> list = roleMenuService.list(query.instanceQueryWrapper());
        ResultVO<List<RoleMenu>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, list);
        return resultVO;
    }

    @GetMapping("/role_id/{roleId:\\d+}")
    public ResultVO<RoleMenu> getByRoleId(@PathVariable Long roleId) {
        RoleMenu roleMenu = roleMenuService.findMenuIdsByRoleId(roleId);
        ResultVO<RoleMenu> resultVO = new ResultVO<>(ResultCodeEnum.SUCCESS, roleMenu);
        return resultVO;
    }

    @PostMapping
    public ResultVO save(RoleMenu roleMenu) {
        boolean isSuccess = roleMenuService.save(roleMenu);
        if(!isSuccess){
            log.warn("修改用户失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    @PutMapping("/{id:\\d+}")
    public ResultVO update(RoleMenu roleMenu) {
        boolean isSuccess = roleMenuService.saveOrUpdate(roleMenu);
        if(!isSuccess){
            log.warn("修改用户失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

}
