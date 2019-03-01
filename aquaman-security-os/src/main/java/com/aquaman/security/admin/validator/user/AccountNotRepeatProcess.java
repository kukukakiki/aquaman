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
package com.aquaman.security.admin.validator.user;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.ResultCodeEnum;
import com.aquaman.security.admin.exception.ValidatorException;
import com.aquaman.security.admin.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 用户名不能重复校验处理器
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
@Slf4j
public class AccountNotRepeatProcess implements ConstraintValidator<AccountNotRepeat, String> {

    @Autowired
    private IUserService userService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        User user = userService.loadUserByUsername(value);
        if(user != null){
            log.error("用户名{}已在数据库存在", value);
            ResultVO resultVO = new ResultVO(ResultCodeEnum.USER_ACCOUNT_REPEAT);
            throw new ValidatorException(resultVO);
        }
        return true;
    }
}
