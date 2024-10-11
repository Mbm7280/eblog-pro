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
@TableName("bus_about")
@ApiModel(value = "BusAbout对象", description = "")
public class BusAbout implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("名称")
    private String aboutName;

    @ApiModelProperty("内容")
    private String content;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
