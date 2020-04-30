package com.zhang.openApi.api.controller;

import com.zhang.openApi.api.model.dto.CheckTokenDto;
import com.zhang.openApi.api.model.dto.ClientDto;
import com.zhang.openApi.api.model.dto.RefreshTokenDto;
import com.zhang.openApi.api.service.PlatClientService;
import com.zhang.openApi.common.utils.JsonUtil;
import com.zhang.openApi.common.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhangfeng
 * @date 2020/4/29 4:54 下午
 */
@RestController
@Api(tags = "token管理")
@RequestMapping("/auth")
public class TokenController {

    private static final Logger logger = LoggerFactory.getLogger(TokenController.class);

    @Autowired
    private PlatClientService platClientService;


    @ApiOperation("获取token")
    @PostMapping("/token")
    public ResultVO token(@RequestBody @Valid ClientDto dto, BindingResult bindingResult) {
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        String loggerF = "登陆";
        logger.info("{} -S 开始，参数 {}", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = platClientService.getToken(dto);
        logger.info("{} -E 结束，参数 {}", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("刷新token")
    @PostMapping("/refreshToken")
    public ResultVO refreshToken(@RequestBody @Valid RefreshTokenDto dto, BindingResult bindingResult) {
        // 如果有参数校验失败，会将错误信息封装成对象组装在BindingResult里
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        String loggerF = "刷新token";
        logger.info("{} -E 开始，参数 {}", loggerF, JsonUtil.beanToJson(dto));
        ResultVO resultVO = platClientService.refreshToken(dto);
        logger.info("{} -E 结束，参数 {}", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }

    @ApiOperation("验证token是否有效")
    @PostMapping("/checkToken")
    public ResultVO checkToken(@RequestBody @Valid CheckTokenDto checkTokenDto, BindingResult bindingResult) {
        for (ObjectError error : bindingResult.getAllErrors()) {
            return ResultVO.error(error.getDefaultMessage());
        }
        String loggerF = "验证token是否有效";
        logger.info("{} -E 开始，参数 {}", loggerF);
        ResultVO resultVO = platClientService.checkToken(checkTokenDto);
        logger.info("{} -E 结束，参数 {}", loggerF, JsonUtil.beanToJson(resultVO));
        return resultVO;
    }
}
