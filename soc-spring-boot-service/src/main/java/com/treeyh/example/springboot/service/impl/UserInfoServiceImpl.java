package com.treeyh.example.springboot.service.impl;

import com.treeyh.common.exception.SysErrorException;
import com.treeyh.common.model.LogModel;
import com.treeyh.common.model.result.CallResult;
import com.treeyh.common.model.result.PageResult;
import com.treeyh.common.utils.UuidUtils;
import com.treeyh.example.springboot.api.model.base.resp.SysResultCode;
import com.treeyh.example.springboot.common.constants.SysConstants;
import com.treeyh.example.springboot.manager.bo.UserInfoBo;
import com.treeyh.example.springboot.manager.domain.UserInfoDomain;
import com.treeyh.example.springboot.manager.domain.repository.RepositoryContext;
import com.treeyh.example.springboot.service.BaseService;
import com.treeyh.example.springboot.service.UserInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author TreeYH
 * @version 1.0
 * @description 描述
 * @create 2019-05-21 11:39
 */
@Service
public class UserInfoServiceImpl extends BaseService implements UserInfoService {

    private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private RepositoryContext repositoryContext;

    @Override
    public CallResult<UserInfoBo> queryById(Long id) {

        final LogModel lm = LogModel.newLogModel("UserInfoServiceImpl.queryById")
                .addMetaData("id", id);

        logger.info(lm.toJson(false));

        if (null == id || id < 1) {
            return this.makeResult(false, SysResultCode.PARAM_ERROR, null, new SysErrorException(SysResultCode.PARAM_ERROR_NAME, "id"));
        }

        final UserInfoDomain userInfoDomain = new UserInfoDomain(repositoryContext);
        UserInfoBo result = userInfoDomain.queryUserInfoById(id);

        lm.addMetaDataResult(result);
        logger.info(lm.toJson());
        return this.makeSuccessResult(result);
    }

    @Override
    public CallResult<UserInfoBo> createDemo(UserInfoBo userInfoBo) {
        final LogModel lm = LogModel.newLogModel("UserInfoServiceImpl.insertDemo")
                .addMetaData("userInfoBo", userInfoBo);
        logger.info(lm.toJson(false));

        if (null == userInfoBo) {
            return this.makeResult(false, SysResultCode.PARAM_ERROR, null, new SysErrorException(SysResultCode.PARAM_ERROR_NAME, "UserInfo"));
        }

        userInfoBo.setId(UuidUtils.getNewIdByLong());
        final UserInfoDomain userInfoDomain = new UserInfoDomain(repositoryContext);
        try {
            int id = userInfoDomain.insertUserInfo(userInfoBo);
        } catch (SysErrorException ex) {
            return this.makeFailCallResult(lm, ex.getResultCodeInfo(), null, ex);
        } catch (Exception ex) {
            return this.makeFailCallResult(lm, SysResultCode.SYS_ERROR, null, ex);
        }

        lm.addMetaDataResult(userInfoBo);
        logger.info(lm.toJson());

        return this.makeSuccessResult(userInfoBo);
    }


    @Override
    public CallResult<Integer> deleteById(Long id) {
        final LogModel lm = LogModel.newLogModel("UserInfoServiceImpl.deleteById")
                .addMetaData("id", id);
        logger.info(lm.toJson(false));

        final UserInfoDomain userInfoDomain = new UserInfoDomain(repositoryContext);

        int result;

        try {
            result = userInfoDomain.deleteUserInfoById(id);
        } catch (SysErrorException ex) {
            return this.makeFailCallResult(lm, ex.getResultCodeInfo(), null, ex);
        } catch (Exception ex) {
            return this.makeFailCallResult(lm, SysResultCode.SYS_ERROR, null, ex);
        }

        lm.addMetaDataResult(result);
        logger.info(lm.toJson());

        return this.makeSuccessResult(result);
    }

    @Override
    public CallResult<Integer> updateById(Long id, UserInfoBo userInfoBo) {

        userInfoBo.setId(id);

        final LogModel lm = LogModel.newLogModel("UserInfoServiceImpl.updateById")
                .addMetaData("id", id)
                .addMetaData("userInfoBo", userInfoBo);

        logger.info(lm.toJson(false));

        final UserInfoDomain userInfoDomain = new UserInfoDomain(repositoryContext);

        try {
            int result = userInfoDomain.updateUserInfoById(userInfoBo);

            lm.addMetaDataResult(result);
            logger.info(lm.toJson());

            return this.makeSuccessResult(result);
        } catch (SysErrorException ex) {
            return this.makeFailCallResult(lm, ex.getResultCodeInfo(), null, ex);
        } catch (Exception ex) {
            return this.makeFailCallResult(lm, SysResultCode.SYS_ERROR, null, ex);
        }

    }


    @Override
    public CallResult<PageResult<UserInfoBo>> queryByPage(String name, Integer status, Long pageNum, Long pageSize) {

        if (null == pageNum || pageNum <= 0) {
            pageNum = SysConstants.DEFAULT_PAGE_NUM;
        }
        if (null == pageNum || pageSize <= 0 || pageSize > SysConstants.MAX_PAGE_SIZE) {
            pageSize = SysConstants.DEFAULT_PAGE_SIZE;
        }
        final LogModel lm = LogModel.newLogModel("UserInfoServiceImpl.queryByPage")
                .addMetaData("name", name)
                .addMetaData("status", status)
                .addMetaData("pageNum", pageNum)
                .addMetaData("pageSize", pageSize);

        logger.info(lm.toJson(false));

        final UserInfoDomain userInfoDomain = new UserInfoDomain(repositoryContext);

        try {
            PageResult<UserInfoBo> returnPageResult = userInfoDomain.queryByPage(name, status, pageNum, pageSize);

            lm.addMetaDataResult(returnPageResult);
            logger.info(lm.toJson());

            return this.makeSuccessResult(returnPageResult);
        } catch (SysErrorException ex) {
            return this.makeFailCallResult(lm, ex.getResultCodeInfo(), null, ex);
        } catch (Exception ex) {
            return this.makeFailCallResult(lm, SysResultCode.SYS_ERROR, null, ex);
        }
    }


}