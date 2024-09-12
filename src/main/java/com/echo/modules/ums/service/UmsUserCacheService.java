package com.echo.modules.ums.service;

import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsUser;

import java.util.List;

public interface UmsUserCacheService {

    /**
     * 根据 [用户ID] 删除后台用户缓存
     */
    void delUserCache(Long userId);

    /**
     * 根据 [用户名] 获取缓存后台用户信息
     */
    UmsUser getUserInfoFromCache(String userName);


    /**
     * 设置缓存后台用户信息
     */
    void setUserInfoInCache(UmsUser umsUser);

    /**
     * 根据 [用户id] 获取缓存后台用户资源列表
     */
    List<UmsResource> getUserResFromCache(String userId);

    /**
     * 设置后台后台用户资源列表
     */
    void setUserResInCache(String userId, List<UmsResource> resourceList);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResCacheByRole(Long roleId);

    /**
     * 当角色相关资源信息改变时删除相关后台用户缓存
     */
    void delResCacheByRoleIds(List<Long> roleIds);

    /**
     * 当资源信息改变时，删除资源项目后台用户缓存
     */
    void delResCacheByResource(Long resourceId);

    /**
     * 删除后台用户资源列表缓存
     */
    void delUserCacheByUserId(Long userId);


}
