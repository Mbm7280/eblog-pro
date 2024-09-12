package com.echo.modules.ums.dto.res;


import com.echo.modules.ums.model.UmsMenu;
import com.echo.modules.ums.model.UmsRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2024/4/19 10:06
 * 项目名称: {pro-cli}
 * 文件名称: GetUserInfoResDTO
 * 文件描述: [ 获取当前登录用户信息DTO ]
 * version：1.0
 *
 ********************************************************/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetUserInfoResDTO {

    private String userName;

    private String icon;

    private List<UmsRole> roleList;

    private List<UmsMenu> menuList;

}
