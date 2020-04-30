package com.zhang.openApi.api.model.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * @author zhangfeng
 * @date 2020/4/30 10:09 上午
 */
@Getter
@Setter
public class CheckTokenDto {

    private String clientId;

    @NotEmpty(message = "token 不能为空")
    private String token;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
