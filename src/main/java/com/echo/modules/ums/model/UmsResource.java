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
@TableName("ums_resource")
@ApiModel(value = "UmsResource对象", description = "")
public class UmsResource implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String resName;

    private String resDesc;

    private String resUrl;

    private Date createTime;

    private Date updateTime;

    private String createBy;

    private String updateBy;

    private String status;


}
