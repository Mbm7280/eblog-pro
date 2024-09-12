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
@TableName("ums_menu")
@ApiModel(value = "UmsMenu对象", description = "")
public class UmsMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("父级ID")
    private String parentId;

    @ApiModelProperty("菜单名称")
    private String menuTitle;

    @ApiModelProperty("菜单顺序")
    private Integer menuSort;

    @ApiModelProperty("菜单名称")
    private String menuName;

    @ApiModelProperty("菜单图标")
    private String menuIcon;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("创建时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private Date updateBy;

    @ApiModelProperty("修改时间")
    private Date updateTime;

    @ApiModelProperty("状态")
    private String status;


}
