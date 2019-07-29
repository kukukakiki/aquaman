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
package com.aquaman.security.admin.controller.user;

import com.aquaman.security.admin.annotation.AquamanLog;
import com.aquaman.security.admin.controller.base.BaseController;
import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.domain.UserRole;
import com.aquaman.security.admin.entity.query.RoleQuery;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.entity.query.UserRoleQuery;
import com.aquaman.security.admin.entity.vo.MenuTreeVO;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.StatusEnum;
import com.aquaman.security.admin.service.IMenuService;
import com.aquaman.security.admin.service.IRoleMenuService;
import com.aquaman.security.admin.service.IUserRoleService;
import com.aquaman.security.admin.service.IUserService;
import com.aquaman.security.admin.utils.CollectionUtil;
import com.aquaman.security.admin.utils.CurrentUserUtil;
import com.aquaman.security.common.enums.ResultCodeEnum;
import com.aquaman.security.common.view.DetailView;
import com.aquaman.security.common.view.ListView;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 用户操作Rest
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/27
 * @since 2019/2/27
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Autowired
    private IRoleMenuService roleMenuService;

    @Autowired
    private IMenuService menuService;

    /**
     * 获取用户列表
     * @param userQuery
     * @return
     */
    @GetMapping
    @AquamanLog
    public ResultVO<List<User>> getByPage(UserQuery userQuery){
        IPage<User> page1 = userService.page(userQuery);
        ResultVO<List<User>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, page1);
        return resultVO;
    }

    /**
     * 根据ID获取用户详情
     * @param id
     * @return
     */
    @JsonView(DetailView.class)
    @GetMapping("/{id:\\d+}")
    public ResultVO getById(@PathVariable Long id){
        User user = userService.getById(id);
        ResultVO<User> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, user);
        return resultVO;
    }

    /**
     * 根据当前登陆用户获取菜单
     * @return
     */
    @GetMapping("/menu")
    public ResultVO<List<MenuTreeVO>> getMenuByCurrentLoginUser() {
        ResultVO<List<MenuTreeVO>> resultVO = new ResultVO<>(ResultCodeEnum.SUCCESS);
        User user = CurrentUserUtil.getLoginUserInfo();
        if(user != null) {
            UserRoleQuery userRoleQuery = new UserRoleQuery();
            userRoleQuery.setUserId(user.getId());
            UserRole userRole = userRoleService.getOne(userRoleQuery.instanceQueryWrapper());
            List<String> list = Arrays.asList(userRole.getRoleIds().split(","));
            List<String> menuIds = roleMenuService.findMenuIdsByRoleIds(CollectionUtil.stringToLong(list));
            List<MenuTreeVO> menuTreeVOList = menuService.findMenuTreeVOByIds(CollectionUtil.stringToLong(menuIds));
            log.info("{}", menuTreeVOList);
            resultVO.setResult(menuTreeVOList);
        }

        return resultVO;
    }

    /**
     * 用户新增
     * @param user
     * @return
     */
    @PostMapping
    public ResultVO save(@Valid User user){
        encodePassword(user);
        boolean isSuccess = userService.save(user);
        if(!isSuccess){
            log.error("新增用户失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    /**
     * 用户修改
     * @param user
     * @return
     */
    @PutMapping("/{id:\\d+}")
    public ResultVO update(@Valid User user){
        encodePassword(user);
        boolean isSuccess = userService.updateById(user);
        if(!isSuccess){
            log.warn("修改用户失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    /**
     * 用户删除（逻辑）
     * @param id
     * @return
     */
    @DeleteMapping("/{id:\\d+}")
    public ResultVO delete(@PathVariable Long id){
        User user = new User();
        user.setId(id);
        user.setStatus(StatusEnum.STOP.getValue());
        boolean isSuccess = userService.updateById(user);
        if(!isSuccess){
            log.warn("修改用户失败，执行返回结果：", isSuccess);
            return unknownError();
        }
        return success();
    }

    /**
     * 密码非空则加密
     * @param user
     */
    private void encodePassword(User user){
        // 密码非空则加密
        if(StringUtils.isNotEmpty(user.getPassword())){
            String encodePwd = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePwd);
        }
    }
}
