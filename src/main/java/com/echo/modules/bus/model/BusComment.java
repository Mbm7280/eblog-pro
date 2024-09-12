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
@TableName("bus_comment")
@ApiModel(value = "BusComment对象", description = "")
public class BusComment implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("评论用户Id")
    private String userId;

    @ApiModelProperty("文章ID")
    private String acticleId;

    @ApiModelProperty("评论内容")
    private String commentContent;

    @ApiModelProperty("回复用户ID")
    private String replyUserId;

    @ApiModelProperty("父评论id")
    private String parentId;

    @ApiModelProperty("状态")
    private String status;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("评论时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
