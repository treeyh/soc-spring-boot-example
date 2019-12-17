package com.treeyh.example.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.deploy.ui.AppInfo;
import com.treeyh.common.model.result.CallResult;
import com.treeyh.common.model.result.PageResult;
import com.treeyh.example.springboot.manager.bo.UserInfoBo;

/**
 * @author TreeYH
 * @version 1.0
 * @description 用户service
 * @create 2019-05-21 11:33
 */
public interface UserInfoService {

    /**
     * 根据id查询对象
     * @param id
     * @return
     */
    CallResult<UserInfoBo> queryById(Long id);

    /**
     * 创建对象
     * @param userInfoBo
     * @return
     */
    CallResult<UserInfoBo> createDemo(UserInfoBo userInfoBo);


    /**
     * 删除对象
     * @param id
     * @return
     */
    CallResult<Integer> deleteById(Long id);

    /**
     * 更新对象
     * @param id
     * @param userInfoBo
     * @return
     */
    CallResult<Integer> updateById(Long id, UserInfoBo userInfoBo);

    /**
     * 分页查询
     * @param code
     * @param status
     * @param pageNum
     * @param pageSize
     * @return
     */
    CallResult<PageResult<UserInfoBo>> queryByPage(String code, Integer status, Long pageNum, Long pageSize);

}
