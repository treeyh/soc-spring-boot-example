package com.treeyh.example.springboot.dao;

import com.treeyh.example.springboot.dao.po.UserInfoPo;
import com.treeyh.example.springboot.dao.po.UserInfoPoExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserInfoPoMapper {
    int countByExample(UserInfoPoExample example);

    int deleteByExample(UserInfoPoExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UserInfoPo record);

    int insertSelective(UserInfoPo record);

    List<UserInfoPo> selectByExample(UserInfoPoExample example);

    UserInfoPo selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UserInfoPo record, @Param("example") UserInfoPoExample example);

    int updateByExample(@Param("record") UserInfoPo record, @Param("example") UserInfoPoExample example);

    int updateByPrimaryKeySelective(UserInfoPo record);

    int updateByPrimaryKey(UserInfoPo record);
}