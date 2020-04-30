package com.zhang.openApi.api.service.impl;

import com.zhang.openApi.api.mapper.PlatClientTokenMapper;
import com.zhang.openApi.api.service.PlatClientTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* PlatClientTokenService实现
* @author: zhangfeng
* @Date: 2020/4/29
*/
@Service
@Transactional
public class PlatClientTokenServiceImpl implements PlatClientTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PlatClientTokenServiceImpl.class);

    @Autowired
    PlatClientTokenMapper platClientTokenMapper;

}