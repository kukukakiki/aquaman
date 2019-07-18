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
@RequestMapping("/user_role")
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

    /**
     * 根据用户ID获取用户角色关系
     * @param userId
     * @return
     */
    @GetMapping("/user_id/{userId:\\d+}")
    public ResultVO<UserRole> getByUserId(@PathVariable Long userId) {
        UserRoleQuery query = new UserRoleQuery();
        query.setUserId(userId);
        UserRole userRole = userRoleService.getOne(query.instanceQueryWrapper());
        ResultVO<UserRole> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, userRole);
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
    public ResultVO update(UserRole userRole) {
        boolean isSuccess = userRoleService.updateById(userRole);
        if(!isSuccess){
            log.warn("修改用户失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

}
