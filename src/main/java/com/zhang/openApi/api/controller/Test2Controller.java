package com.zhang.openApi.api.controller;

import com.zhang.openApi.api.model.dto.ClientDto;
import com.zhang.openApi.common.utils.JsonUtil;
import com.zhang.openApi.common.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangfeng
 * @date 2020/4/30 11:07 上午
 */

@RestController
@Api(tags = "test")
@RequestMapping("/test2")
public class Test2Controller {

    private static final Logger logger = LoggerFactory.getLogger(Test2Controller.class);

    @ApiOperation("v1")
    @GetMapping("/v1")
    public ResultVO v1() {
        logger.info("get 不带参数");
        return ResultVO.success();
    }

    @ApiOperation("v2")
    @GetMapping("/v2")
    public ResultVO v2(@RequestParam String token) {
        logger.info("get 带一个参数 token={}", token);
        return ResultVO.success();
    }

    @ApiOperation("v3")
    @GetMapping("/v3")
    public ResultVO v3(@RequestParam String param, @RequestParam String param2) {
        logger.info("get 带两个参数 param={}, param2={}", param, param2);
        return ResultVO.success();
    }

    @ApiOperation("v4")
    @PostMapping("/v4")
    public ResultVO v4() {
        logger.info("post 无参数 ");
        return ResultVO.success();
    }

    @ApiOperation("v5")
    @PostMapping("/v5")
    public ResultVO v5(@RequestBody ClientDto dto) {
        logger.info("post 有参数 dto={}", JsonUtil.beanToJson(dto));
        return ResultVO.success();
    }

}
