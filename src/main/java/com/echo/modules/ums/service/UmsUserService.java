package com.echo.modules.ums.service;

import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/****************************************************
 * 创建人：Echo
 * 项目名称: {pro-cli}
 * 文件名称: UmsUserService
 * 文件描述: [ UserService ]
 * version：1.0
 *
 ********************************************************/
public interface UmsUserService extends IService<UmsUser> {

    /**
     * 根据 [用户名] 获取用户信息
     *
     * @param useranme
     * @return
     */
    public UmsUser getUserInfoByUsername(String useranme);

    /**
     * 根据 [用户id] 获取该用户对应的资源信息
     *
     * @param userId
     * @return
     */
    public List<UmsResource> getResourceListByUserId(String userId);


    /**
     * 根据 [用户名] 获取用户信息及对应的角色及权限信息
     *
     * @param username
     * @return
     */
    public UserDetails loadUserByUsername(String username);



}
