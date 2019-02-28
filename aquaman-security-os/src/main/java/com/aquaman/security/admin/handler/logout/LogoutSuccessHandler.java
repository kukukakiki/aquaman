package com.aquaman.security.admin.handler.logout;

import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.enums.ResultCodeEnum;
import com.aquaman.security.admin.util.JSONUtil;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登出成功处理器
 * @author 创建者 wei.wang
 * @author 修改者 wei.wang
 * @version 2019/2/28
 * @since 2019/2/28
 */
@Component("logoutSuccessHandler")
public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=UTF-8");
            // 组装返回VO
            ResultVO resultVO = new ResultVO(ResultCodeEnum.LOGOUT_SUCCESS);
            // JSON格式返回
            response.getWriter().print(JSONUtil.objectToJSONString(resultVO));
    }
}
