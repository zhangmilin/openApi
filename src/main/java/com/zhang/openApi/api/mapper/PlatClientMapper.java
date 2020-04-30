package com.zhang.openApi.api.mapper;

import com.zhang.openApi.api.model.PlatClient;
import com.zhang.openApi.api.model.PlatClientExample;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface PlatClientMapper {
    int countByExample(PlatClientExample example);

    int deleteByExample(PlatClientExample example);

    int deleteByPrimaryKey(String clientId);

    int insert(PlatClient record);

    int insertSelective(PlatClient record);

    List<PlatClient> selectByExample(PlatClientExample example);

    PlatClient selectByPrimaryKey(String clientId);

    int updateByExampleSelective(@Param("record") PlatClient record, @Param("example") PlatClientExample example);

    int updateByExample(@Param("record") PlatClient record, @Param("example") PlatClientExample example);

    int updateByPrimaryKeySelective(PlatClient record);

    int updateByPrimaryKey(PlatClient record);
}