package com.aquaman.security.admin.handler;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.vo.CurrentLoginUserVO;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.ResultCodeEnum;
import com.aquaman.security.admin.exception.PrincipalToUserConversionException;
import com.aquaman.security.admin.util.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录成功后spring security将会调用该处理器
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
@Component("loginSuccessHandler")
@Slf4j
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        // 获取登录成功对象
        if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof User){
            User user = (User) authentication.getPrincipal();
            // 组装登录会员VO
            CurrentLoginUserVO currentLoginUserVO = new CurrentLoginUserVO();
            currentLoginUserVO.setAccount(user.getAccount());
            // 组装返回VO
            ResultVO resultVO = new ResultVO(ResultCodeEnum.SUCCESS, user);
            resultVO.setData(currentLoginUserVO);
            // JSON格式返回
            response.getWriter().print(JSONUtil.objectToJSONString(resultVO));
        } else {
            logger.error("---------------------------------Principal转换异常---------------------------------");
            // 如果authentication.getPrincipal()为空或authentication.getPrincipal()无法转换成User，抛出异常
            ResultVO resultVO = new ResultVO(ResultCodeEnum.PRINCIPAL_TO_USER_CONVERSION);
            throw new PrincipalToUserConversionException(resultVO);
        }
    }
}
