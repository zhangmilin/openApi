package com.zhang.openApi.common.config.security;

import com.alibaba.fastjson.JSON;
import com.zhang.openApi.api.enums.ResultCode;
import com.zhang.openApi.common.vo.ResultVO;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        ResultVO resultVO = new ResultVO(ResultCode.FORBIDDEN_ERROR);
        httpServletResponse.getWriter().write(JSON.toJSONString(resultVO));
    }
}
