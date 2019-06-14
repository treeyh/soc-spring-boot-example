package com.treeyh.example.springboot.manager.domain.repository;

import com.treeyh.common.model.result.PageResult;
import com.treeyh.common.utils.ClazzConverterUtils;
import com.treeyh.example.springboot.api.enums.DeleteEnum;
import com.treeyh.example.springboot.dao.UserInfoPoMapper;
import com.treeyh.example.springboot.dao.po.UserInfoPo;
import com.treeyh.example.springboot.dao.po.UserInfoPoExample;
import com.treeyh.example.springboot.manager.bo.UserInfoBo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        UserInfoPo userInfoPo = userInfoPoMapper.selectByPrimaryKey(id);
        return ClazzConverterUtils.converterClass(userInfoPo, UserInfoBo.class);
    }

    /**
     * 根据name查询
     * @param name
     * @return
     */
    public List<UserInfoBo> queryByName(String name){

        UserInfoPoExample userInfoPoExample = new UserInfoPoExample();
        userInfoPoExample.createCriteria().andNameEqualTo(name);

        List<UserInfoPo> activeInfos = userInfoPoMapper.selectByExample(userInfoPoExample);

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

        int rt = userInfoPoMapper.insertSelective(userInfoPo);
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
        return userInfoPoMapper.deleteByPrimaryKey(id);
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

        UserInfoPoExample example = new UserInfoPoExample();

        UserInfoPoExample.Criteria criteria = example.createCriteria();
        if(StringUtils.isNotEmpty(name)){
            criteria.andNameLike(name + "%");
        }
        if(null != status && 0 < status){
            criteria.andStatusEqualTo(status);
        }
        example.setOrderByClause("id desc"); // 设置排序方式

        int offset = (pageNum.intValue() - 1) * pageSize.intValue();

        example.setOffset(offset);
        example.setLimit(pageSize.intValue());

        Integer total = userInfoPoMapper.countByExample(example);
        List<UserInfoPo> userInfoPos = userInfoPoMapper.selectByExample(example);

        if(null == userInfoPos || userInfoPos.size() <= 0){
            return new PageResult<UserInfoBo>(Long.parseLong(total.toString()), pageNum, pageSize, new ArrayList<UserInfoBo>());
        }

        List<UserInfoBo> demoModels = (List<UserInfoBo>) ClazzConverterUtils.converterClass(userInfoPos, UserInfoBo.class);

        return new PageResult<UserInfoBo>(Long.parseLong(total.toString()), pageNum, pageSize, demoModels);

    }


    /**
     * 更新对象
     * @param userInfoBo
     * @return
     */
    public int updateById(UserInfoBo userInfoBo){

        UserInfoPo activeInfo = new UserInfoPo();
        activeInfo.setId(userInfoBo.getId());
        if(null != userInfoBo.getName()) {
            activeInfo.setName(userInfoBo.getName());
        }
        if(null != userInfoBo.getStatus()) {
            activeInfo.setStatus(userInfoBo.getStatus().intValue());
        }
        activeInfo.setUpdateTime(new Date());

        UserInfoPoExample example = new UserInfoPoExample();
        example.createCriteria().andIdEqualTo(userInfoBo.getId());

        return userInfoPoMapper.updateByExampleSelective(activeInfo, example);
    }

}
