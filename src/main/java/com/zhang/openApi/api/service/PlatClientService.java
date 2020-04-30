package com.zhang.openApi.api.service;

import com.zhang.openApi.api.model.dto.CheckTokenDto;
import com.zhang.openApi.api.model.dto.ClientDto;
import com.zhang.openApi.api.model.dto.RefreshTokenDto;
import com.zhang.openApi.common.vo.ResultVO;

/**
* PlatClientService接口
* @author: zhangfeng
* @Date: 2020/4/29
*/
public interface PlatClientService {

    /**
     * 获取token
     * @param dto
     * @return
     */
    ResultVO getToken(ClientDto dto);

    ResultVO refreshToken(RefreshTokenDto dto);

    ResultVO checkToken(CheckTokenDto token);

    boolean checkTokenValid (CheckTokenDto dto);

    boolean checkTokenValid (String token);
}