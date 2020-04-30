package com.zhang.openApi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author zhangfeng
 * @date 2020/4/29 4:57 下午
 */
@Setter
@Getter
public class RefreshTokenDto {

    @NotEmpty(message = "clientId不能为空")
    private String clientId;

    @NotEmpty(message = "refreshToken不能为空")
    private String refreshToken;
}
