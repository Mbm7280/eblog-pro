package com.echo.modules.bus.model;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Getter
@Setter
@TableName("bus_friend_link")
@ApiModel(value = "BusFriendLink对象", description = "")
public class BusFriendLink implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("友站名称")
    private String firLinkName;

    @ApiModelProperty("友链头像")
    private String friLinkAvatar;

    @ApiModelProperty("友链地址")
    private String friLinkAddress;

    @ApiModelProperty("友链介绍")
    private String friLinkIntro;

    @ApiModelProperty("友链状态")
    private String friLinkStatus;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
