package com.zhang.openApi.common.config.security;

import com.alibaba.fastjson.JSON;
import com.zhang.openApi.api.enums.ResultCode;
import com.zhang.openApi.common.vo.ResultVO;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AjaxAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        ResultVO resultVO = new ResultVO(ResultCode.UNAUTHORIZED_ERROR);
        httpServletResponse.getWriter().write(JSON.toJSONString(resultVO));
    }
}
