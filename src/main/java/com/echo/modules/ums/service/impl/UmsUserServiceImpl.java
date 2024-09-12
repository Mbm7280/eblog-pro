package com.echo.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.echo.dto.AdminUserDetails;
import com.echo.modules.ums.mapper.UmsResourceMapper;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsUser;
import com.echo.modules.ums.mapper.UmsUserMapper;
import com.echo.modules.ums.service.UmsUserCacheService;
import com.echo.modules.ums.service.UmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.echo.common.constant.CommonConstant.ZERO;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements UmsUserService {

    @Autowired
    private UmsResourceMapper resourceMapper;

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getUserInfoByUsername
     * 方法描述：{ 根据 [用户名] 获取用户信息 }
     * param：userName
     * return：UmsUser
     * 创建人： Echo
     * 创建时间：2024/9/12
     * version：1.0
     */
    @Override
    public UmsUser getUserInfoByUsername(String userName) {
        UmsUser umsUser = getCacheService().getUserInfoFromCache(userName);
        if (ObjUtil.isNotEmpty(umsUser)) {
            return umsUser;
        }

        List<UmsUser> umsUserList = list(new LambdaQueryWrapper<UmsUser>()
                .eq(UmsUser::getUserName, userName)
        );

        if (CollUtil.isNotEmpty(umsUserList)) {
            umsUser = umsUserList.get(ZERO);
            getCacheService().setUserInfoInCache(umsUser);
            return umsUser;
        }

        return null;
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getResourceListByUserId
     * 方法描述：{ 根据 [用户id] 获取该用户对应的资源信息 }
     * param：userId
     * return：List<UmsResource>
     * 创建人： Echo
     * version：1.0
     */
    @Override
    public List<UmsResource> getResourceListByUserId(String userId) {
        List<UmsResource> resourceList = getCacheService().getUserResFromCache(userId);

        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }

        resourceList = resourceMapper.getResourceList(userId);

        if (CollUtil.isNotEmpty(resourceList)) {
            getCacheService().setUserResInCache(userId, resourceList);
        }

        return resourceList;
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：loadUserByUsername
     * 方法描述：{ 根据 [用户名] 获取用户信息及对应的角色及权限信息 }
     * param：userName
     * return：UserDetails
     * 创建人： Echo
     * version：1.0
     */
    public UserDetails loadUserByUsername(String userName) {
        UmsUser umsUser = this.getUserInfoByUsername(userName);
        if (ObjUtil.isNotEmpty(umsUser)) {
            // 获取该用户的资源信息
            List<UmsResource> resourceList = getResourceListByUserId(umsUser.getId());
            return new AdminUserDetails(umsUser, resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getCacheService
     * 方法描述：{ 获取 用户缓存Service }
     * param：
     * return：UmsUserCacheService
     * 创建人： Echo
     * version：1.0
     */
    private UmsUserCacheService getCacheService() {
        return SpringUtil.getBean(UmsUserCacheService.class);
    }
}
