package com.echo.modules.ums.model;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Echo
 * @since 2024-04-16
 */
@Getter
@Setter
@TableName("ums_menu")
@ApiModel(value = "UmsMenu对象", description = "")
public class UmsMenu implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private Long parentId;

    private String menuTitle;

    private Integer menuLevel;

    private Integer menuSort;

    private String menuName;

    private String menuIcon;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private Date updateBy;

    private String status;


}
