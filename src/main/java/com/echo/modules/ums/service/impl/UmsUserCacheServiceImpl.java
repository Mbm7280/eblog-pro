package com.echo.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echo.common.RedisService;
import com.echo.modules.ums.mapper.UmsUserMapper;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsUser;
import com.echo.modules.ums.model.UmsUserRoleRelation;
import com.echo.modules.ums.service.UmsUserCacheService;
import com.echo.modules.ums.service.UmsUserRoleRelationService;
import com.echo.modules.ums.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UmsUserCacheServiceImpl implements UmsUserCacheService {

    @Value("${redis.database}")
    private String REDIS_DATABASE;

    @Value("${redis.key.admin}")
    private String REDIS_KEY_ADMIN;

    @Value("${redis.expire.common}")
    private Long REDIS_EXPIRE;

    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;

    @Autowired
    private RedisService redisService;

    @Autowired
    private UmsUserMapper userMapper;


    @Autowired
    private UmsUserRoleRelationService userRoleRelationService;

    @Autowired
    private UmsUserService userService;

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserCacheServiceImpl
     * 方法名称：delUserCache
     * 方法描述：{ 根据 [用户ID] 删除后台用户缓存 }
     * param：userId
     * return：void
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public void delUserCache(Long userId) {
        UmsUser user = userService.getById(userId);
        if(ObjUtil.isNotEmpty(user)){
            String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + user.getUserName();
            redisService.del(key);
        }
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserCacheServiceImpl
     * 方法名称：getUserInfoFromCache
     * 方法描述：{ 根据 [用户名] 获取缓存后台用户信息 }
     * param：username
     * return：UmsUser
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public UmsUser getUserInfoFromCache(String userName) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + userName;
        return (UmsUser) redisService.get(key);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserCacheServiceImpl
     * 方法名称：setUserInfoInCache
     * 方法描述：{ 设置缓存后台用户信息 }
     * param：user
     * return：void
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public void setUserInfoInCache(UmsUser user) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_ADMIN + ":" + user.getUserName();
        redisService.set(key, user, REDIS_EXPIRE);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserCacheServiceImpl
     * 方法名称：getUserResFromCache
     * 方法描述：{ 根据 [用户id] 获取缓存后台用户资源列表 }
     * param：userId
     * return：List<UmsResource>
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public List<UmsResource> getUserResFromCache(Long userId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + userId;
        return (List<UmsResource>) redisService.get(key);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserCacheServiceImpl
     * 方法名称：setUserResInCache
     * 方法描述：{ 设置后台后台用户资源列表 }
     * param：userId resourceList
     * return：void
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public void setUserResInCache(Long userId, List<UmsResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + userId;
        redisService.set(key, resourceList, REDIS_EXPIRE);
    }


    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserCacheServiceImpl
     * 方法名称：delResCacheByRole
     * 方法描述：{ 当角色相关资源信息改变时删除相关后台用户缓存 }
     * param：roleId
     * return：void
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public void delResCacheByRole(Long roleId) {
        QueryWrapper<UmsUserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsUserRoleRelation::getRoleId, roleId);
        List<UmsUserRoleRelation> relationList = userRoleRelationService.list(wrapper);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getUserId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserCacheServiceImpl
     * 方法名称：delResCacheByRoleIds
     * 方法描述：{ 当角色相关资源信息改变时删除相关后台用户缓存 }
     * param：roleIds
     * return：void
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public void delResCacheByRoleIds(List<Long> roleIds) {
        QueryWrapper<UmsUserRoleRelation> wrapper = new QueryWrapper<>();
        wrapper.lambda().in(UmsUserRoleRelation::getRoleId, roleIds);
        List<UmsUserRoleRelation> relationList = userRoleRelationService.list(wrapper);
        if (CollUtil.isNotEmpty(relationList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = relationList.stream().map(relation -> keyPrefix + relation.getUserId()).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserCacheServiceImpl
     * 方法名称：delResCacheByResource
     * 方法描述：{ 当资源信息改变时，删除资源项目后台用户缓存 }
     * param：resourceId
     * return：void
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public void delResCacheByResource(Long resourceId) {
        List<Long> userIdList = userMapper.getUserIdList(resourceId);
        if (CollUtil.isNotEmpty(userIdList)) {
            String keyPrefix = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":";
            List<String> keys = userIdList.stream().map(adminId -> keyPrefix + adminId).collect(Collectors.toList());
            redisService.del(keys);
        }
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserCacheServiceImpl
     * 方法名称：delUserCacheByUserId
     * 方法描述：{ 删除后台用户资源列表缓存 }
     * param：userId
     * return：void
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public void delUserCacheByUserId(Long userId) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + userId;
        redisService.del(key);
    }

}
