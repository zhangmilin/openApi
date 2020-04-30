package com.zhang.openApi.api.service.impl;

import com.zhang.openApi.api.enums.ClientStatusEnums;
import com.zhang.openApi.api.enums.ResultCode;
import com.zhang.openApi.api.mapper.PlatClientMapper;
import com.zhang.openApi.api.mapper.PlatClientTokenMapper;
import com.zhang.openApi.api.model.PlatClient;
import com.zhang.openApi.api.model.PlatClientToken;
import com.zhang.openApi.api.model.PlatClientTokenExample;
import com.zhang.openApi.api.model.dto.CheckTokenDto;
import com.zhang.openApi.api.model.dto.ClientDto;
import com.zhang.openApi.api.model.dto.RefreshTokenDto;
import com.zhang.openApi.api.service.PlatClientService;
import com.zhang.openApi.common.config.security.JwtUserDetails;
import com.zhang.openApi.common.exception.ClientExistException;
import com.zhang.openApi.common.exception.ClientLockedException;
import com.zhang.openApi.common.utils.RandomIdUtil;
import com.zhang.openApi.common.utils.jwt.JwtTokenUtil;
import com.zhang.openApi.common.vo.ResultVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* PlatClientService实现
* @author: zhangfeng
* @Date: 2020/4/29
*/
@Service
@Transactional
public class PlatClientServiceImpl implements PlatClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatClientServiceImpl.class);

    @Autowired
    PlatClientMapper platClientMapper;

    @Autowired
    PlatClientTokenMapper platClientTokenMapper;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public ResultVO getToken(ClientDto dto) {
        String token = generatorToken(dto);

        String refreshToken = RandomIdUtil.getRandomCode();
        Map result = new HashMap<>();
        result.put("token", token);
        result.put("refreshToken", refreshToken);
        // 更新token 到数据库中
        // 删除之前的记录
        platClientTokenMapper.deleteByPrimaryKey(dto.getClientId());
        // 保存新的
        savePlatClientToken(dto.getClientId(), token, refreshToken, refreshToken);
        return ResultVO.successData(result);
    }

    @Override
    public ResultVO refreshToken(RefreshTokenDto dto) {
        String clientId = dto.getClientId();
        PlatClientToken old = selectByClientIdAndRefreshToken(dto);
        if (old == null) {
            throw new ClientExistException("客户关联关系不存在，请先重新获取token");
        }
        String refreshToken = RandomIdUtil.getRandomCode();

        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(old.getClientId());

        PlatClient platClient = platClientMapper.selectByPrimaryKey(clientId);
        clientDto.setClientSecret(platClient.getClientSecret());
        String token = generatorToken(clientDto);

        Map result = new HashMap();
        result.put("token", token);
        result.put("refreshToken", refreshToken);

        savePlatClientToken(clientId, token, dto.getRefreshToken(), refreshToken);
        return ResultVO.successData(result);
    }

    @Override
    public ResultVO checkToken(CheckTokenDto dto) {
        boolean boo = checkTokenValid(dto);
        if (boo) {
            return ResultVO.success("token有效");
        }
        return ResultVO.error(ResultCode.TOKEN_IS_INVALID_FAILED);
    }

    /**
     * 查询token 是否有效
     * true 说明有效
     * false 说明无效
     * @param dto
     * @return
     */
    @Override
    public boolean checkTokenValid (CheckTokenDto dto) {
        String clientId = dto.getClientId();
        String token = dto.getToken();
        if (StringUtils.isBlank(clientId)) {
            clientId = jwtTokenUtil.getClientIdFromToken(token);
        }
        // 根据 clientId 查询数据库中的保存token 跟传入的值是否一致
        PlatClientToken platClientToken = platClientTokenMapper.selectByPrimaryKey(clientId);
        if (platClientToken !=null ) {
            // 判断是否相同，不相同直接返回过期
            if (platClientToken.getToken().equals(token)) {
                // 相同
                boolean boo = jwtTokenUtil.isTokenExpired(token);
                if (boo) {
                    // 过期
                    return false;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean checkTokenValid(String token) {
        String clientId = jwtTokenUtil.getClientIdFromToken(token);
        CheckTokenDto checkTokenDto = new CheckTokenDto();
        checkTokenDto.setClientId(clientId);
        checkTokenDto.setToken(token);
        return checkTokenValid(checkTokenDto);
    }

    /**
     * 生成token
     * @param dto
     * @return
     */
    public String generatorToken (ClientDto dto) {
        // 校验数据
        PlatClient platClient = selectByClientId(dto);
        // 生成token 并返回
        JwtUserDetails userDetails = new JwtUserDetails(dto.getClientId(), platClient.getClientName());

        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    /**
     * 保存platClientToken
     * @param clientId
     * @param token
     * @param oldRefreshToken
     * @param newRefreshToken
     */
    void savePlatClientToken(String clientId, String token, String oldRefreshToken, String newRefreshToken) {
        RefreshTokenDto refreshTokenDto = new RefreshTokenDto();
        refreshTokenDto.setClientId(clientId);
        refreshTokenDto.setRefreshToken(oldRefreshToken);
        PlatClientToken old = selectByClientIdAndRefreshToken(refreshTokenDto);
        if (old == null) {
            PlatClientToken platClientToken = new PlatClientToken();
            platClientToken.setClientId(clientId);
            platClientToken.setCreateDts(new Date());
            platClientToken.setToken(token);
            platClientToken.setRefreshToken(newRefreshToken);
            platClientTokenMapper.insertSelective(platClientToken);
        } else {
            old.setToken(token);
            old.setRefreshToken(newRefreshToken);
            old.setUpdateDts(new Date());
            platClientTokenMapper.updateByPrimaryKeyWithBLOBs(old);
        }

    }

    /**
     * 查询 PlatClientToken
     * @param dto
     * @return
     */
    PlatClientToken selectByClientIdAndRefreshToken(RefreshTokenDto dto) {
        String clientId = dto.getClientId();
        String refreshToken = dto.getRefreshToken();
        PlatClientTokenExample platClientTokenExample = new PlatClientTokenExample();
        PlatClientTokenExample.Criteria criteria = platClientTokenExample.createCriteria()
                .andClientIdEqualTo(clientId)
                .andRefreshTokenEqualTo(refreshToken);
        List<PlatClientToken> list = platClientTokenMapper.selectByExample(platClientTokenExample);
        return list.size()> 0? list.get(0):null;
    }

    /**
     * 查询 PlatClient
     * @param dto
     * @return
     */
    PlatClient selectByClientId(ClientDto dto) {
        String clientId = dto.getClientId();
        String clientSecret = dto.getClientSecret();
        if (StringUtils.isBlank(clientId)) {
            throw new ClientExistException("client不存在");
        }
        PlatClient platClient = platClientMapper.selectByPrimaryKey(clientId);
        if (platClient == null) {
            throw new ClientExistException("client不存在");
        }
        // 无效的
        if (ClientStatusEnums.INVALID.ordinal() == platClient.getStatus()) {
            throw new ClientLockedException("client状态有误");
        }
        if (!platClient.getClientSecret().equals(clientSecret)) {
            throw new ClientExistException("clientSecret不正确");
        }
        return platClient;
    }
}