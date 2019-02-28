package com.aquaman.security.admin.handler;

import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.ResultCodeEnum;
import com.aquaman.security.admin.util.JSONUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录失败处理器
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
@Component("loginFailureHandler")
public class LoginFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
        ResultVO resultVO = new ResultVO(ResultCodeEnum.UNKNOWN_ERROR);
        // 如若为以下异常，这抛出密码错误信息
        if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
            resultVO = new ResultVO(ResultCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        response.getWriter().write(JSONUtil.objectToJSONString(resultVO));
    }
}
