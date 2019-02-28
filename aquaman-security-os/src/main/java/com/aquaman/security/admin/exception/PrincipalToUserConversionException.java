package com.aquaman.security.admin.exception;

import com.aquaman.security.admin.entity.vo.ResultVO;

/**
 * Principal转换User对象异常
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
public class PrincipalToUserConversionException extends AquamanBasicException {
    
    public PrincipalToUserConversionException(ResultVO resultVO) {
        super(resultVO);
    }
}
