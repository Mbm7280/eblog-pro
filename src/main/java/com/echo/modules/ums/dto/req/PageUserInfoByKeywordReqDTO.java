package com.echo.modules.ums.dto.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2024/4/19 14:16
 * 项目名称: {pro-cli}
 * 文件名称: PageUserInfoByKeywordReqDTO
 * 文件描述: [ 根据用户名或姓名分页获取用户列表 ]
 * version：1.0
 *
 ********************************************************/

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageUserInfoByKeywordReqDTO {


    private String userName;

    private String nickName;

}
