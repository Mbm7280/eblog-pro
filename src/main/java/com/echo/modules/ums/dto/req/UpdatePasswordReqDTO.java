package com.echo.modules.ums.dto.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2024/4/19 15:00
 * 项目名称: {pro-cli}
 * 文件名称: UpdatePasswordReqDTO
 * 文件描述: [ 修改指定用户密码 ]
 * version：1.0
 *
 ********************************************************/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdatePasswordReqDTO {

    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;

    @NotEmpty
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword;

    @NotEmpty
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;

}
