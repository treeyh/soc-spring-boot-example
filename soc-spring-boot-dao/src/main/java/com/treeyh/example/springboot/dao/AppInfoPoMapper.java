package com.treeyh.example.springboot.dao;

import com.treeyh.example.springboot.dao.po.AppInfoPo;
import com.treeyh.example.springboot.dao.po.AppInfoPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AppInfoPoMapper {
    int countByExample(AppInfoPoExample example);

    int deleteByExample(AppInfoPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(AppInfoPo record);

    int insertSelective(AppInfoPo record);

    List<AppInfoPo> selectByExample(AppInfoPoExample example);

    AppInfoPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") AppInfoPo record, @Param("example") AppInfoPoExample example);

    int updateByExample(@Param("record") AppInfoPo record, @Param("example") AppInfoPoExample example);

    int updateByPrimaryKeySelective(AppInfoPo record);

    int updateByPrimaryKey(AppInfoPo record);


    AppInfoPo selectByAppId(String appId);
}