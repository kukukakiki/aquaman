package com.aquaman.security.admin.controller.user;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.ResultCodeEnum;
import com.aquaman.security.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户操作Rest
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/27
 * @since 2019/2/27
 */
@RestController
@RequestMapping("/user")
public class UserOperatorRest {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResultVO<List<User>> get(UserQuery userQuery){
        List<User> users = userService.findUserByPage(userQuery);
        ResultVO<List<User>> resultVO = new ResultVO<List<User>>(ResultCodeEnum.SUCCESS, users);
        return resultVO;
    }
}
