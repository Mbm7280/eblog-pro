package com.echo.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.config.exception.Asserts;
import com.echo.dto.AdminUserDetails;
import com.echo.modules.ums.dto.req.LoginReqDTO;
import com.echo.modules.ums.dto.req.PageUserInfoByKeywordReqDTO;
import com.echo.modules.ums.dto.req.RegisterReqDTO;
import com.echo.modules.ums.dto.req.UpdatePasswordReqDTO;
import com.echo.modules.ums.dto.res.GetUserInfoResDTO;
import com.echo.modules.ums.dto.res.LoginResDTO;
import com.echo.modules.ums.dto.res.RefreshTokenResDTO;
import com.echo.modules.ums.mapper.UmsMenuMapper;
import com.echo.modules.ums.mapper.UmsResourceMapper;
import com.echo.modules.ums.mapper.UmsRoleMapper;
import com.echo.modules.ums.mapper.UmsUserMapper;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsUser;
import com.echo.modules.ums.service.UmsUserCacheService;
import com.echo.modules.ums.service.UmsUserService;
import com.echo.utils.JwtTokenUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.Date;
import java.util.List;

import static com.echo.common.constant.CommonConstant.*;
import static com.echo.config.api.ResultCode.*;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Echo
 * @since 2024-04-16
 */
@Service
public class UmsUserServiceImpl extends ServiceImpl<UmsUserMapper, UmsUser> implements UmsUserService {

    @Autowired
    private UmsResourceMapper resourceMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    @Autowired
    private UmsRoleMapper roleMapper;

    @Autowired
    private UmsMenuMapper menuMapper;

    @Override
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
     * 方法名称：getResourceListByUserId
     * 方法描述：{ 根据 [用户id] 获取该用户对应的资源信息 }
     * param：userId
     * return：List<UmsResource>
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public List<UmsResource> getResourceListByUserId(Long userId) {
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
     * 方法名称：getUserInfoByUsername
     * 方法描述：{ 根据 [用户名] 获取用户信息 }
     * param：useranme
     * return：UmsUser
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Autowired
    private UmsUserMapper userMapper;

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
     * 方法名称：getCacheService
     * 方法描述：{ 获取 用户缓存Service }
     * param：
     * return：UmsUserCacheService
     * 创建人： Echo
     * 创建时间：2024/4/16
     * version：1.0
     */
    @Override
    public UmsUserCacheService getCacheService() {
        return SpringUtil.getBean(UmsUserCacheService.class);
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
        umsUser.setCreateTime(new Date());
        umsUser.setUpdateTime(new Date());
        umsUser.setCreateBy(umsUser.getNickName());
        umsUser.setUpdateBy(umsUser.getNickName());
        umsUser.setStatus(STR_ZERO);

        // 查询是否有相同用户名的用户
        QueryWrapper<UmsUser> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(UmsUser::getUserName, umsUser.getUserName());
        List<UmsUser> umsAdminList = list(wrapper);

        if (!umsAdminList.isEmpty()) {
            return Result.success(THE_USER_HAS_REGISTERED);
        }

        //将密码进行加密操作
        String encodePassword = passwordEncoder.encode(umsUser.getPassword());
        umsUser.setPassword(encodePassword);

        return save(umsUser) ? Result.success() : Result.failed();
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

        // 校验用户账号是否被禁用
        if (!userDetails.isEnabled()) {
            Asserts.fail("帐号已被禁用");
        }

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
        loginResDTO.setTokenHead(tokenHeader);

        return Result.success(loginResDTO);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：refreshToken
     * 方法描述：{ 刷新token }
     * param：request
     * return：Result<RefreshTokenResDTO>
     * 创建人： Echo
     * 创建时间：2024/4/19
     * version：1.0
     */
    @Override
    public Result<RefreshTokenResDTO> refreshToken(HttpServletRequest request) {
        RefreshTokenResDTO refreshTokenResDTO = new RefreshTokenResDTO();

        String oldToken = request.getHeader(tokenHeader);
        String newToken = jwtTokenUtil.refreshHeadToken(oldToken);

        if (StrUtil.isBlank(newToken)) {
            return Result.failed("token已经过期！");
        }

        refreshTokenResDTO.setToken(newToken);
        refreshTokenResDTO.setTokenHead(tokenHeader);

        return Result.success(refreshTokenResDTO);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getUserInfo
     * 方法描述：{ 获取当前登录用户信息 }
     * param：principal
     * return：Result<GetUserInfoResDTO>
     * 创建人： Echo
     * 创建时间：2024/4/19
     * version：1.0
     */
    @Override
    public Result<GetUserInfoResDTO> getUserInfo(Principal principal) {
        GetUserInfoResDTO getUserInfoResDTO = new GetUserInfoResDTO();

        UmsUser umsUser = getUserInfoByUsername(principal.getName());

        if (ObjUtil.isEmpty(umsUser)) {
            return Result.failed(THE_USER_QUERY_FAILED);
        }

        getUserInfoResDTO = getUserInfoResDTO.builder()
                .userName(umsUser.getUserName())
                .icon(umsUser.getIcon())
                .roleList(roleMapper.getRoleListByUserId(umsUser.getId()))
                .menuList(menuMapper.getMenuListByUserId(umsUser.getId()))
                .build();

        return Result.success(getUserInfoResDTO);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：pageUserInfoByKeyword
     * 方法描述：{ 根据用户名或姓名分页获取用户列表 }
     * param：reqDTO
     * pageSize
     * pageNum
     * return：Result<PageInfo<UmsUser>>
     * 创建人： Echo
     * 创建时间：2024/4/19
     * version：1.0
     */
    @Override
    public Result<PageInfo<UmsUser>> pageUserInfoByKeyword(PageUserInfoByKeywordReqDTO reqDTO, Integer pageSize, Integer pageNum) {
        Page<UmsUser> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<UmsUser> umsUserLambdaQueryWrapper = new LambdaQueryWrapper<>();

        if (StringUtils.isNotEmpty(reqDTO.getUserName())) {
            umsUserLambdaQueryWrapper.like(UmsUser::getUserName, reqDTO.getUserName());
        }
        if (StringUtils.isNotEmpty(reqDTO.getUserName())) {
            umsUserLambdaQueryWrapper.like(UmsUser::getNickName, reqDTO.getNickName());
        }

        Page<UmsUser> umsUserPage = page(page, umsUserLambdaQueryWrapper);

        PageInfo<UmsUser> umsUserPageInfo = PageInfo.restPage(umsUserPage);

        return Result.success(umsUserPageInfo);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：getUserInfoByUserId
     * 方法描述：{ 获取指定用户信息 }
     * param：userId
     * return：Result<UmsUser>
     * 创建人： Echo
     * 创建时间：2024/4/19
     * version：1.0
     */
    @Override
    public Result<UmsUser> getUserInfoByUserId(Long userId) {
        UmsUser umsUser = getOne(new LambdaQueryWrapper<UmsUser>()
                .eq(UmsUser::getId, userId)
                .eq(UmsUser::getStatus, STR_ZERO)
        );
        return ObjUtil.isEmpty(umsUser) ? Result.failed() : Result.success(umsUser);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：updateUserInfoByUserId
     * 方法描述：{ 修改指定用户信息 }
     * param：userId
     * umsUser
     * return：Result
     * 创建人： Echo
     * 创建时间：2024/4/19
     * version：1.0
     */
    @Override
    public Result updateUserInfoByUserId(Long userId, UmsUser umsUser) {
        UmsUser rawsUserInfo = getOne(new LambdaQueryWrapper<UmsUser>()
                .eq(UmsUser::getId, userId)
                .eq(UmsUser::getStatus, STR_ZERO)
        );

        if (ObjUtil.isEmpty(rawsUserInfo)) {
            return Result.failed(THE_USER_QUERY_FAILED);
        }

        if (StrUtil.isBlank(umsUser.getPassword()) || rawsUserInfo.getPassword().equals(umsUser.getPassword())) {
            // 与原加密密码相同的不需要修改
            umsUser.setPassword(null);
        } else {
            umsUser.setPassword(passwordEncoder.encode(umsUser.getPassword()));
        }

        umsUser.setId(userId);

        boolean upBoolean = updateById(umsUser);

        if (upBoolean) {
            getCacheService().delUserCache(userId);
        }

        return upBoolean ? Result.success() : Result.failed(THE_USER_UPDATE_FAILED);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：updatePassword
     * 方法描述：{ 修改指定用户密码 }
     * param：reqDTO
     * return：Result
     * 创建人： Echo
     * 创建时间：2024/4/19
     * version：1.0
     */
    @Override
    public Result updatePassword(UpdatePasswordReqDTO reqDTO) {
        UmsUser umsUser = getOne(new LambdaQueryWrapper<UmsUser>()
                .eq(UmsUser::getUserName, reqDTO.getUsername())
                .eq(UmsUser::getStatus, STR_ZERO)
        );

        if (ObjUtil.isEmpty(umsUser)) {
            return Result.failed(THE_USER_IS_NOT_EXIST);
        }

        if (!passwordEncoder.matches(reqDTO.getOldPassword(), umsUser.getPassword())) {
            return Result.failed(THE_OLD_PASSWORD_IS_WRONG);
        }

        umsUser.setPassword(passwordEncoder.encode(reqDTO.getNewPassword()));

        boolean upBoolean = updateById(umsUser);

        if (upBoolean) {
            getCacheService().delUserCache(umsUser.getId());
        }

        return upBoolean ? Result.success() : Result.failed(THE_USER_UPDATE_FAILED);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：deleteUserByUserId
     * 方法描述：{ 删除指定用户信息 }
     * param：userId
     * return：Result
     * 创建人： Echo
     * 创建时间：2024/4/19
     * version：1.0
     */
    @Override
    public Result deleteUserByUserId(Long userId) {
        UmsUser umsUser = getOne(new LambdaQueryWrapper<UmsUser>()
                .eq(UmsUser::getUserName, userId)
                .eq(UmsUser::getStatus, STR_ZERO)
        );

        if (ObjUtil.isEmpty(umsUser)) {
            return Result.failed(THE_USER_IS_NOT_EXIST);
        }

        umsUser.setStatus(STR_ONE);

        boolean upBoolean = updateById(umsUser);

        if (upBoolean) {
            getCacheService().delUserCache(userId);
        }

        return upBoolean ? Result.success() : Result.failed(THE_USER_DELETE_FAILED);
    }

    /**
     * 类路径：com.echo.modules.ums.service.impl
     * 类名称：UmsUserServiceImpl
     * 方法名称：updateUserStatus
     * 方法描述：{ 修改帐号状态 }
     * param：userId
     * return：Result
     * 创建人： Echo
     * 创建时间：2024/4/19
     * version：1.0
     */
    @Override
    public Result updateUserStatus(Long userId) {
        UmsUser umsUser = getOne(new LambdaQueryWrapper<UmsUser>()
                .eq(UmsUser::getUserName, userId)
                .eq(UmsUser::getStatus, STR_ZERO)
        );

        if (ObjUtil.isEmpty(umsUser)) {
            return Result.failed(THE_USER_IS_NOT_EXIST);
        }

        if (umsUser.getStatus().equals(STR_ZERO)) {
            umsUser.setStatus(STR_ONE);
        } else {
            umsUser.setStatus(STR_ZERO);
        }

        boolean upBoolean = updateById(umsUser);

        if (upBoolean) {
            getCacheService().delUserCache(userId);
        }

        return upBoolean ? Result.success() : Result.failed(THE_USER_UPDATE_FAILED);
    }


}
