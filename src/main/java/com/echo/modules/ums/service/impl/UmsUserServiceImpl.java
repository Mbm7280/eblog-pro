package com.echo.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echo.common.utils.GenegateIDUtil;
import com.echo.config.api.IErrorCode;
import com.echo.config.api.Result;
import com.echo.config.exception.ApiException;
import com.echo.config.exception.Asserts;
import com.echo.dto.AdminUserDetails;
import com.echo.modules.ums.dto.req.LoginReqDTO;
import com.echo.modules.ums.dto.req.RegisterReqDTO;
import com.echo.modules.ums.dto.res.LoginResDTO;
import com.echo.modules.ums.mapper.UmsResourceMapper;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsUser;
import com.echo.modules.ums.mapper.UmsUserMapper;
import com.echo.modules.ums.model.UmsUserRoleRelation;
import com.echo.modules.ums.service.UmsRoleService;
import com.echo.modules.ums.service.UmsUserCacheService;
import com.echo.modules.ums.service.UmsUserRoleRelationService;
import com.echo.modules.ums.service.UmsUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.utils.JwtTokenUtil;
import org.apache.ibatis.binding.BindingException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.echo.common.constant.CommonConstant.STR_ZERO;
import static com.echo.common.constant.CommonConstant.ZERO;
import static com.echo.config.api.ResultCode.THE_USERNAME_HAS_REGISTERED;
import static com.echo.config.api.ResultCode.THE_USER_NEED_ALLOW_RESOURCES;

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

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UmsUserRoleRelationService userRoleRelationService;

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
     * 方法名称：register
     * 方法描述：{ 用户注册 }
     * param：[registerReqDTO]
     * return：com.echo.config.api.Result<com.echo.modules.ums.model.UmsUser>
     * 创建人：@author Echo
     * 创建时间：2023/10/21 17:56
     * version：1.0
     */
    @Override
    public Result<UmsUser> register(RegisterReqDTO registerReqDTO) {
        UmsUser umsUser = new UmsUser();
        BeanUtils.copyProperties(registerReqDTO, umsUser);
        umsUser.setId(GenegateIDUtil.generateUserID());
        umsUser.setCreateTime(new Date());
        umsUser.setUpdateTime(new Date());
        umsUser.setCreateBy(umsUser.getId());
        umsUser.setUpdateBy(umsUser.getId());

        // 查询是否有相同用户名的用户
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsUser::getUserName, umsUser.getUserName());
        List<UmsUser> umsAdminList = list(wrapper);

        if (!umsAdminList.isEmpty()) {
            return Result.success(THE_USERNAME_HAS_REGISTERED);
        }

        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsUser.getPassword());
        umsUser.setPassword(encodePassword);

        boolean saveUserResult = save(umsUser);

        if(saveUserResult) {
            // 分配基本角色
            UmsUserRoleRelation umsUserRoleRelation = new UmsUserRoleRelation();
            umsUserRoleRelation.setId(GenegateIDUtil.generateURID());
            umsUserRoleRelation.setUserId(umsUser.getId());
            umsUserRoleRelation.setRoleId("ROLEb90f0bfc-3fc5-4048-a5b6-07ea3e6343841726189909430231684");
            userRoleRelationService.save(umsUserRoleRelation);
        }
        return  Result.success();
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：login
     * 方法描述：{ 登录 }
     * param：[loginReqDTO]
     * return：com.echo.config.api.Result<com.echo.modules.ums.dto.res.LoginResDTO>
     * 创建人：@author Echo
     * 创建时间：2023/10/21 18:21
     * version：1.0
     */
    @Override
    public Result<LoginResDTO> login(LoginReqDTO loginReqDTO) {
        LoginResDTO loginResDTO = new LoginResDTO();

        // 获取用户信息
        UserDetails userDetails = loadUserByUsername(loginReqDTO.getUsername());

        // 密码校验
        if (!passwordEncoder.matches(loginReqDTO.getPassword(), userDetails.getPassword())) {
            Asserts.fail("密码不正确");
        }

        // 将认证信息放到上下文中
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 生成Token
        String token = jwtTokenUtil.generateToken(userDetails);

        // 校验Token生成
        if (StrUtil.isBlank(token)) {
            return Result.failed("用户名或密码错误");
        }

        // 返回
        loginResDTO.setToken(token);
        loginResDTO.setTokenHead(tokenHead);

        return Result.success(loginResDTO);
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
