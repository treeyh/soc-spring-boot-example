package com.treeyh.example.springboot.manager.domain.repository;

import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.treeyh.common.model.result.PageResult;
import com.treeyh.common.utils.ClazzConverterUtils;
import com.treeyh.example.springboot.api.enums.DeleteEnum;
import com.treeyh.example.springboot.dao.UserInfoPoMapper;
import com.treeyh.example.springboot.dao.po.UserInfoPo;
import com.treeyh.example.springboot.manager.bo.UserInfoBo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 11:08
 */
@Repository
public class UserInfoRepository {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoRepository.class);

    @Autowired
    private UserInfoPoMapper userInfoPoMapper;


    /**
     * 根据id查询
     * @param id
     * @return
     */
    public UserInfoBo queryById(long id){
        UserInfoPo userInfoPo = userInfoPoMapper.selectById(id);
        return ClazzConverterUtils.converterClass(userInfoPo, UserInfoBo.class);
    }

    /**
     * 根据name查询
     * @param name
     * @return
     */
    public List<UserInfoBo> queryByName(String name){

        Map<String, Object> map = new HashMap<>();
        map.put("name", name);

        List<UserInfoPo> activeInfos = userInfoPoMapper.selectByMap(map);

        return (List<UserInfoBo>)ClazzConverterUtils.converterClass(activeInfos, UserInfoBo.class);
    }

    /**
     * 保存一条记录
     */
    public int insert(UserInfoBo userInfoBo){
        UserInfoPo userInfoPo = ClazzConverterUtils.converterClass(userInfoBo, UserInfoPo.class);
        if(userInfoPo == null){
            return 0;
        }

        userInfoPo.setDeleted(DeleteEnum.NO_DELETE.getCode());

        int rt = userInfoPoMapper.insert(userInfoPo);
//        if(rt > 0){
//            userInfoBo.setId(userInfoPo.getId());
//        }

        return rt;
    }


    /**
     * 删除对象
     * @param id
     * @return
     */
    public int deleteById(long id){
        return userInfoPoMapper.deleteById(id);
    }


    /**
     * 分页查询对象
     * @param name
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    public PageResult<UserInfoBo> queryByPage(String name, Integer status, Long pageNum, Long pageSize){

        QueryWrapper<UserInfoPo> wrapper = new QueryWrapper<>();

        if(StringUtils.isNotEmpty(name)){
            wrapper.likeRight("name", name);
        }
        if(null != status && 0 < status){
            wrapper.eq("status", status);
        }
        wrapper.orderByDesc("id"); // 设置排序方式


        Page<UserInfoPo> page = new Page<>(pageNum, pageSize);

        IPage<UserInfoPo> pageResult = userInfoPoMapper.selectPage(page, wrapper);

        if(null == pageResult || pageResult.getRecords().size() <= 0){
            return new PageResult<UserInfoBo>(pageResult.getTotal(), pageNum, pageSize, new ArrayList<UserInfoBo>());
        }

        IPage<UserInfoBo> pageResult2  =  ClazzConverterUtils.converterClass(pageResult, new TypeReference<Page<UserInfoBo>>(){});

        return new PageResult<UserInfoBo>(pageResult2.getTotal(), pageNum, pageSize, pageResult2.getRecords());

    }


    /**
     * 更新对象
     * @param userInfoBo
     * @return
     */
    public int updateById(UserInfoBo userInfoBo){

        UserInfoPo userInfoPo = new UserInfoPo();
        userInfoPo.setId(userInfoBo.getId());
        if(null != userInfoBo.getName()) {
            userInfoPo.setName(userInfoBo.getName());
        }
        if(null != userInfoBo.getStatus()) {
            userInfoPo.setStatus(userInfoBo.getStatus().intValue());
        }
        userInfoPo.setUpdateTime(new Date());

        QueryWrapper<UserInfoPo> wrapper = new QueryWrapper<>();

        wrapper.eq("id", userInfoBo.getId());

        return userInfoPoMapper.update(userInfoPo, wrapper);
    }

}
