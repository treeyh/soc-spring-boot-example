package com.treeyh.example.springboot.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@TableName("app_info")
@EqualsAndHashCode
@Accessors(chain = true)
public class AppInfoPo  {

    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 应用编号
     */
    private String appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 秘钥1
     */
    private String appSecret1;

    /**
     * 秘钥2，秘钥替换时使用
     */
    private String appSecret2;

    /**
     * 负责人
     */
    private String owner;

    /**
     * 项目url
     */
    private String appUrl;

    /**
     * 是否进行签名校验，1校验，2不校验，3不允许接口调用
     */
    private Integer checkStatus;

    /**
     * 状态，1可用，2停止使用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除，1已删除，2未删除
     */
    private Integer deleted;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 更新时间
     */
    private Date updateTime;


}