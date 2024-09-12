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
@TableName("ums_role")
@ApiModel(value = "UmsRole对象", description = "")
public class UmsRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("角色名称")
    private String roleName;

    @ApiModelProperty("角色介绍")
    private String roleDesc;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    @ApiModelProperty("状态")
    private String status;


}
