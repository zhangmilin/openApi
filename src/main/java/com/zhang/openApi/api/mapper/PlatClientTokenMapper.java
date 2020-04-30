package com.zhang.openApi.api.mapper;

import com.zhang.openApi.api.model.PlatClientToken;
import com.zhang.openApi.api.model.PlatClientTokenExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PlatClientTokenMapper {
    int countByExample(PlatClientTokenExample example);

    int deleteByExample(PlatClientTokenExample example);

    int deleteByPrimaryKey(String clientId);

    int insert(PlatClientToken record);

    int insertSelective(PlatClientToken record);

    List<PlatClientToken> selectByExampleWithBLOBs(PlatClientTokenExample example);

    List<PlatClientToken> selectByExample(PlatClientTokenExample example);

    PlatClientToken selectByPrimaryKey(String clientId);

    int updateByExampleSelective(@Param("record") PlatClientToken record, @Param("example") PlatClientTokenExample example);

    int updateByExampleWithBLOBs(@Param("record") PlatClientToken record, @Param("example") PlatClientTokenExample example);

    int updateByExample(@Param("record") PlatClientToken record, @Param("example") PlatClientTokenExample example);

    int updateByPrimaryKeySelective(PlatClientToken record);

    int updateByPrimaryKeyWithBLOBs(PlatClientToken record);

    int updateByPrimaryKey(PlatClientToken record);
}