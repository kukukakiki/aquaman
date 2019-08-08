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
package com.aquaman.security.admin.handler.login;

import com.aquaman.security.admin.entity.domain.User;
import com.aquaman.security.admin.entity.dto.CurrentLoginUserDTO;
import com.aquaman.security.admin.entity.vo.ResultVO;
import com.aquaman.security.admin.exception.PrincipalToUserConversionException;
import com.aquaman.security.admin.service.IUserService;
import com.aquaman.security.common.constant.AquamanConstant;
import com.aquaman.security.common.util.JSONUtil;
import com.aquaman.security.common.enums.ResultCodeEnum;
import com.aquaman.security.redis.service.RedisSetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

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

    @Autowired
    private IUserService userService;

    @Autowired
    private ClientDetailsService clientDetailsService;

    @Autowired
    private RedisSetService redisOperationsService;

    @Autowired
    @Qualifier("defaultAuthorizationServerTokenServices")
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        // 消息头中获取授权码
        String header = request.getHeader("Authorization");
        // 截取消息头前缀值
        String prefixKey = "Basic ";
        if(header == null || !header.startsWith(prefixKey)) {
            throw new UnapprovedClientAuthenticationException("请求头中无client信息");
        }
        // 验证授权码
        String[] tokens = extractAndDecodeHeader(header, request);
        assert tokens.length == 2;
        String clientId = tokens[0];
        String clientSecret = tokens[1];
        ClientDetails clientDetails = clientDetailsService.loadClientByClientId(clientId);
        if(clientDetails == null) {
            throw new UnapprovedClientAuthenticationException("clientId信息不存在信息");
        } else if(!StringUtils.equals(clientSecret, clientDetails.getClientSecret())) {
            throw new UnapprovedClientAuthenticationException("clientSecret不匹配");
        }
        // 组装数据，用于创建accessToken使用
        TokenRequest tokenRequest = new TokenRequest(MapUtils.EMPTY_MAP, clientId, clientDetails.getScope(), "custom");
        OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);
        OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
        // 获取当前登录人的OAuth2AccessToken对象
        OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);

        // 获取登录成功对象
        if (authentication.getPrincipal() != null && authentication.getPrincipal() instanceof User && oAuth2AccessToken != null){
            // 获取当前登陆用户实体类
            User user = (User) authentication.getPrincipal();
            // 创建当前登陆会员DTO
            CurrentLoginUserDTO currentLoginUserDTO = new CurrentLoginUserDTO();
            // 设置token、刷新token、token过期时长、用户名、用户ID、用户头像、用户姓名
            currentLoginUserDTO.setToken(oAuth2AccessToken.getValue());
            currentLoginUserDTO.setRefreshToken(oAuth2AccessToken.getRefreshToken() == null ? null : oAuth2AccessToken.getRefreshToken().getValue());
            currentLoginUserDTO.setExpires(oAuth2AccessToken.getExpiresIn());
            currentLoginUserDTO.setAccount(user.getAccount());
            currentLoginUserDTO.setId(user.getId());
            currentLoginUserDTO.setName(user.getName());
            currentLoginUserDTO.setImageFileId(user.getImageFileId());
            // 将当前登陆用户信息存入redis中
            redisOperationsService.setKeyValue(user.getAccount(), JSONUtil.objectToJSONString(currentLoginUserDTO), currentLoginUserDTO.getExpires(), TimeUnit.SECONDS);
            // 组装返回VO
            ResultVO resultVO = new ResultVO(ResultCodeEnum.SUCCESS, user);
            resultVO.setResult(currentLoginUserDTO);
            // JSON格式返回
            response.getWriter().print(JSONUtil.objectToJSONString(resultVO));
            // 记录最后登录时间，就算失败也吃掉异常，记录错误日志
            try{
                userService.updateLoginTime(user.getId());
            }catch (Exception ex) {
                log.error("{}记录登录时间失败:{}-{}-{}", AquamanConstant.LOG_TAG,
                    ResultCodeEnum.INERT_LOGIN_TIME_ERROR.getCode(), ResultCodeEnum.INERT_LOGIN_TIME_ERROR.getMsg(), ex.getMessage());
            }
        } else {
            log.error("{}Principal转换异常{}", AquamanConstant.LOG_TAG, AquamanConstant.LOG_TAG);
            // 如果authentication.getPrincipal()为空或authentication.getPrincipal()无法转换成User，抛出异常
            ResultVO resultVO = new ResultVO(ResultCodeEnum.PRINCIPAL_TO_USER_CONVERSION);
            throw new PrincipalToUserConversionException(resultVO);
        }
    }

    /**
     *
     * @param header
     * @param request
     * @return
     * @throws IOException
     */
    private String[] extractAndDecodeHeader(String header, HttpServletRequest request)
            throws IOException {
        byte[] base64Token = header.substring(6).getBytes("UTF-8");
        byte[] decoded;
        try {
            decoded = Base64.getDecoder().decode(base64Token);
        }
        catch (IllegalArgumentException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }
        String token = new String(decoded, "UTF-8");
        int delim = token.indexOf(":");
        if (delim == -1) {
            throw new BadCredentialsException("Invalid basic authentication token");
        }
        return new String[] { token.substring(0, delim), token.substring(delim + 1) };
    }
}
