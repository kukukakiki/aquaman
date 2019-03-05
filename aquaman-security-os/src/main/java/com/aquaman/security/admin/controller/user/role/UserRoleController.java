package com.aquaman.security.admin.controller.user.role;


import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.Role;
import com.aquaman.security.admin.entity.domain.UserRole;
import com.aquaman.security.admin.entity.query.UserRoleQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.service.IUserRoleService;
import com.aquaman.security.common.enums.ResultCodeEnum;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wei wang
 * @since 2019-03-05
 */
@Slf4j
@RestController
@RequestMapping("/user/role")
public class UserRoleController extends BaseController {

    @Autowired
    private IUserRoleService userRoleService;

    /**
     * 查询所有集合
     * @param query
     * @return
     */
    @GetMapping
    public ResultVO<List<UserRole>> get(UserRoleQuery query){
        List<UserRole> list = userRoleService.list(query.instanceQueryWrapper());
        ResultVO<List<UserRole>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, list);
        return resultVO;
    }

    @PostMapping
    public ResultVO save(UserRole userRole) {
        boolean isSuccess = userRoleService.save(userRole);
        if(!isSuccess){
            log.warn("修改用户失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    @PutMapping("/{id:\\d+}")
    public ResultVO update(@PathVariable Long id, UserRole userRole) {
        userRole.setId(id);
        boolean isSuccess = userRoleService.updateById(userRole);
        if(!isSuccess){
            log.warn("修改用户失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

}
