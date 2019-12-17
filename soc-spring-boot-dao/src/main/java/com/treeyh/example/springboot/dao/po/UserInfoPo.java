package com.treeyh.example.springboot.dao.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@TableName("user_info")
@EqualsAndHashCode
@Accessors(chain = true)
public class UserInfoPo {

    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.INPUT)
    @ApiModelProperty(value = "主键")
    private Long id;

    /**
     * 用户名
     */
    private String name;

    /**
     * 性别，0未知，1男，2女
     */
    private Integer sex;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 体重
     */
    private Double weight;

    /**
     * 状态，1可用，2不可用
     */
    private Integer status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 是否删除，1删除，2未删除
     */
    private Integer deleted;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后更新时间
     */
    private Date updateTime;


}
