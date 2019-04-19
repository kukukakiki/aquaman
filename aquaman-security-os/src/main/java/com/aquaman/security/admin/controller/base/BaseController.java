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
package com.aquaman.security.admin.controller.base;

import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.common.enums.ResultCodeEnum;
import org.slf4j.Logger;
import org.springframework.validation.BindingResult;

/**
 * 基础Rest类
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2018/7/30
 */
public class BaseController {

    /**
     * 操作成功
     * @return
     */
    public ResultVO success(){
        ResultVO resultVO = new ResultVO(ResultCodeEnum.SUCCESS);
        return resultVO;
    }

    /**
     * 未知异常
     * @return
     */
    public ResultVO error(ResultCodeEnum codeEnum){
        ResultVO resultVO = new ResultVO(codeEnum);
        return resultVO;
    }

    /**
     * 未知异常
     * @return
     */
    public ResultVO unknownError(){
        ResultVO resultVO = new ResultVO(ResultCodeEnum.UNKNOWN_ERROR);
        return resultVO;
    }

    /**
     * Valid校验异常信息打印控制台
     * @param resultErrors
     * @param log
     */
    protected void validatorErrorConsolePrint(BindingResult resultErrors, Logger log) {
        /*if(resultErrors.hasErrors()){
            resultErrors.getAllErrors().stream().forEach(
                    objectError -> log.error(objectError.getDefaultMessage()));
        }*/
    }
}
