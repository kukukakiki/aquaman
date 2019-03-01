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

import com.aquaman.security.admin.controller.base.BaseRest;
import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.query.UserQuery;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.ResultCodeEnum;
import com.aquaman.security.admin.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
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
@Slf4j
public class UserOperatorRest extends BaseRest {

    @Autowired
    private IUserService userService;

    /**
     * 获取用户列表
     * @param userQuery
     * @return
     */
    @GetMapping
    public ResultVO<List<User>> get(UserQuery userQuery){
        List<User> users = userService.list();
        ResultVO<List<User>> resultVO = new ResultVO(ResultCodeEnum.SUCCESS, users);
        return resultVO;
    }

    /**
     * 用户新增
     * @param user
     * @return
     */
    @PostMapping
    public ResultVO create(@Valid User user){
        boolean isSuccess = userService.save(user);
        if(isSuccess){
            log.warn("新增用户信息警告，因为新增数量小于1:{}", isSuccess);
        }
        return null;
    }

    /**
     * 用户修改
     * @param user
     * @return
     */
    @PutMapping("/id:\\d+")
    public ResultVO update(@Valid User user){
        return null;
    }
}
