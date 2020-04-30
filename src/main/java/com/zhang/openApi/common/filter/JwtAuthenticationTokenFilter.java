package com.zhang.openApi.common.filter;

import com.alibaba.fastjson.JSON;
import com.zhang.openApi.api.enums.ResultCode;
import com.zhang.openApi.api.service.PlatClientService;
import com.zhang.openApi.common.config.security.SecurityConfig;
import com.zhang.openApi.common.utils.URLUtil;
import com.zhang.openApi.common.vo.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;



@Service
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationTokenFilter.class);

    private static final String TOKEN_ = "token";

    public static final String[] NOT_INCLUDE_PATH = {
            "/auth/**",
            "/v2/api-docs/**",
            "/configuration/ui/**",
            "/swagger-resources",
            "/v2/api-docs",
            "/configuration/security/**",
            "/webjars/**",
            "/doc.html",
            "/favicon.ico/**",
            "/**/*.css",
            "/**/*.js",
    };

    @Autowired
    private PlatClientService platClientService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String queryParam = request.getQueryString();
        LOGGER.info("进入Filter url上获取的请求参数={}", queryParam);
        if (queryParam != null && queryParam.contains(TOKEN_)) {
            Map<String, Object> map = URLUtil.getUrlParams(queryParam);
            String token = map.get(TOKEN_).toString();
            boolean boo = platClientService.checkTokenValid(token);
            if (!boo) {
                LOGGER.info("token 失效==》直接返回", token);
                returnJson(response);
            } else {
                // 放行
                chain.doFilter(request, response);
            }
        } else {
            // 判断是否是未拦截的地址
            if (isFilterExcludeRequest(request)) {
                chain.doFilter(request, response);
            } else {
                returnJson(response);
            }
        }
    }

    /**
     * 判断是否是 过滤器直接放行的请求
     * <br/>主要用于静态资源的放行
     * @param
     * @return
     */
    private boolean isFilterExcludeRequest(HttpServletRequest request) {
        String url = request.getRequestURI();
        for (String ecludedUrl : NOT_INCLUDE_PATH) {
            if (ecludedUrl.startsWith("*.")) {
                // 如果配置的是后缀匹配, 则把前面的*号干掉，然后用endWith来判断
                if(url.endsWith(ecludedUrl.substring(1))){
                    return true;
                }
            } else if (ecludedUrl.endsWith("/**")) {
                if(!ecludedUrl.startsWith("/")) {
                    // 前缀匹配，必须要是/开头
                    ecludedUrl = "/" + ecludedUrl;
                }
                // 如果配置是前缀匹配, 则把最后的*号干掉，然后startWith来判断
                String prffixStr = request.getContextPath() + ecludedUrl.substring(0, ecludedUrl.length() - 2);
                if(url.startsWith(prffixStr)) {
                    return true;
                }
            } else {
                // 如果不是前缀匹配也不是后缀匹配,那就是全路径匹配
                if(!ecludedUrl.startsWith("/")) {
                    // 全路径匹配，也必须要是/开头
                    ecludedUrl = "/" + ecludedUrl;
                }
                String targetUrl = request.getContextPath() + ecludedUrl;
                if(url.equals(targetUrl)) {
                    return true;
                }
            }
        }
        return false;
    }

    private void returnJson(HttpServletResponse response){
        PrintWriter writer = null;
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        try {
            writer = response.getWriter();
            ResultVO resultVO = new ResultVO(ResultCode.TOKEN_IS_INVALID_FAILED);
            response.getWriter().write(JSON.toJSONString(resultVO));
        } catch (IOException e){
            e.printStackTrace();
        } finally {
            if(writer != null){
                writer.close();
            }
        }
    }
}
