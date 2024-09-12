package com.echo.modules.ums.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("ums_role_resource_relation")
@ApiModel(value = "UmsRoleResourceRelation对象", description = "")
public class UmsRoleResourceRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("角色ID")
    private String roleId;

    @ApiModelProperty("资源ID")
    private String resourceId;


}
