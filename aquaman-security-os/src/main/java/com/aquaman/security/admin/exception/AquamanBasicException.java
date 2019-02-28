package com.aquaman.security.admin.exception;

import com.aquaman.security.admin.entity.vo.ResultVO;

/**
 * 基本异常对象
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
public class AquamanBasicException extends RuntimeException {

    private ResultVO resultVO;

    public ResultVO getResultVO() {
        return resultVO;
    }

    public void setResultVO(ResultVO resultVO) {
        this.resultVO = resultVO;
    }

    public AquamanBasicException(ResultVO resultVO) {
        this.resultVO = resultVO;
    }
}
