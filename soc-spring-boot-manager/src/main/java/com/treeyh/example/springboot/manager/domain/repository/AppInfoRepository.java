package com.treeyh.example.springboot.manager.domain.repository;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.treeyh.common.utils.ClazzConverterUtils;
import com.treeyh.example.springboot.dao.AppInfoPoMapper;
import com.treeyh.example.springboot.dao.po.AppInfoPo;
import com.treeyh.example.springboot.manager.bo.AppInfoBo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-25 15:12
 */
@Repository
public class AppInfoRepository {

    private static final Logger logger = LoggerFactory.getLogger(AppInfoRepository.class);

    @Autowired
    private AppInfoPoMapper appInfoPoMapper;



    public AppInfoBo queryByAppId(String appId){
        if(StringUtils.isEmpty(appId)){
            return null;
        }

        QueryWrapper<AppInfoPo> wrapper = new QueryWrapper<>();
        wrapper.eq("app_id", appId);
        AppInfoPo appInfoPo = appInfoPoMapper.selectOne(wrapper);

        if(null == appInfoPo){
            return null;
        }

        return ClazzConverterUtils.converterClass(appInfoPo, AppInfoBo.class);
    }
}

