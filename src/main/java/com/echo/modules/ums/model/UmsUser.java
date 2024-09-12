package com.echo.modules.ums.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
@TableName("ums_user")
@ApiModel(value = "UmsUser对象", description = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UmsUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private String userName;

    private String password;

    private String icon;

    private String email;

    private String nickName;

    private String note;

    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date createTime;

    private String createBy;

    // 2024/06/18 20:12:03
    // 2024-06-18 20:12:03
    // yyyy-MM-dd HH:mm:ss
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date updateTime;

    private String updateBy;

    @ApiModelProperty(value = "状态 0 存在  1 删除")
    private String status;


    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    private Date loginTime;

}
