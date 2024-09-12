package com.echo.modules.ums.model;

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
@TableName("ums_resource")
@ApiModel(value = "UmsResource对象", description = "")
public class UmsResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("资源名称")
    private String resName;

    @ApiModelProperty("资源介绍")
    private String resDesc;

    @ApiModelProperty("资源url")
    private String resUrl;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("状态")
    private String status;


}
