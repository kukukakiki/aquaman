package com.aquaman.security.admin.controller;

import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.ResultCodeEnum;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
@RestController
public class LoginRest {

    @RequestMapping("/login")
    public ResultVO login() {
        ResultVO resultVO = new ResultVO(ResultCodeEnum.USER_NOT_LOGIN);
        return resultVO;
    }
}
