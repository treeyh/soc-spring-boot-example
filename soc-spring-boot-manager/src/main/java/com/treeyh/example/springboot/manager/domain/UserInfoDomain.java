package com.treeyh.example.springboot.manager.domain;

import com.treeyh.common.exception.SysErrorException;
import com.treeyh.common.model.result.PageResult;
import com.treeyh.common.utils.JsonUtils;
import com.treeyh.example.springboot.api.model.base.resp.SysResultCode;
import com.treeyh.example.springboot.common.constants.SysConstants;
import com.treeyh.example.springboot.manager.bo.UserInfoBo;
import com.treeyh.example.springboot.manager.domain.repository.RepositoryContext;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 11:06
 */
public class UserInfoDomain {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoDomain.class);

    private RepositoryContext repositoryContext;

    public UserInfoDomain(RepositoryContext repositoryContext){
        this.repositoryContext = repositoryContext;
    }


    public UserInfoBo queryUserInfoById(Long id){

        String cacheKey = SysConstants.CACHE_KEY_PRE +"user:"+ id.toString();
        String json = repositoryContext.getRedisHelper().get(cacheKey);
        if(StringUtils.isEmpty(json)){
            UserInfoBo userInfoBo = repositoryContext.getUserInfoRepository().queryById(id);

            if(null != userInfoBo){
                json = JsonUtils.toJson(userInfoBo);
                repositoryContext.getRedisHelper().set(cacheKey, json, SysConstants.CACHE_TIME_OUT);
            }
            return userInfoBo;
        }

        return JsonUtils.fromJson(json, UserInfoBo.class);
    }

    public UserInfoBo queryUserInfoByName(String name){

        List<UserInfoBo> userInfoBos = repositoryContext.getUserInfoRepository().queryByName(name);
        if(null == userInfoBos || 0 == userInfoBos.size()){
            return null;
        }
        return userInfoBos.get(0);
    }

    /**
     * 创建对象
     * @param userInfoBo
     * @return
     */
    public int insertUserInfo(UserInfoBo userInfoBo) throws SysErrorException {
        if(userInfoBo == null){
            return 0;
        }
        userInfoBo.setCreateTime(new Date(System.currentTimeMillis()));
        userInfoBo.setUpdateTime(new Date(System.currentTimeMillis()));
        if(null != this.queryUserInfoByName(userInfoBo.getName())){
            throw new SysErrorException(SysResultCode.USER_INFO_EXIST);
        }
        return repositoryContext.getUserInfoRepository().insert(userInfoBo);
    }


    /**
     * 根据id删除对象
     * @param id
     * @return
     * @throws SysErrorException
     */
    public int deleteUserInfoById(long id) throws SysErrorException {

        if(null == this.queryUserInfoById(id)){
            throw new SysErrorException(SysResultCode.USER_INFO_NOT_EXIST);
        }

        return repositoryContext.getUserInfoRepository().deleteById(id);
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
        return repositoryContext.getUserInfoRepository().queryByPage(name, status, pageNum, pageSize);
    }

    /**
     * 更新对象
     * @param userInfoBo
     * @return
     */
    public int updateUserInfoById(UserInfoBo userInfoBo) throws SysErrorException {

        UserInfoBo userInfoBo1 = this.queryUserInfoById(userInfoBo.getId());

        if(null == userInfoBo1){
            throw new SysErrorException(SysResultCode.USER_INFO_NOT_EXIST);
        }

        return repositoryContext.getUserInfoRepository().updateById(userInfoBo);
    }
}
