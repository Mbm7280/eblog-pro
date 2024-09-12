package com.echo.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
@TableName("ums_role")
@ApiModel(value = "UmsRole对象", description = "")
public class UmsRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String roleName;

    private String roleDesc;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private String status;


}
