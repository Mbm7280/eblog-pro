package com.echo.modules.ums.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.ums.dto.req.LoginReqDTO;
import com.echo.modules.ums.dto.req.PageUserInfoByKeywordReqDTO;
import com.echo.modules.ums.dto.req.RegisterReqDTO;
import com.echo.modules.ums.dto.req.UpdatePasswordReqDTO;
import com.echo.modules.ums.dto.res.GetUserInfoResDTO;
import com.echo.modules.ums.dto.res.LoginResDTO;
import com.echo.modules.ums.dto.res.RefreshTokenResDTO;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsUser;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Echo
 * @since 2024-04-16
 */
public interface UmsUserService extends IService<UmsUser> {


    /**
     * 根据 [用户名] 获取用户信息及对应的角色及权限信息
     *
     * @param username
     * @return
     */
    public UserDetails loadUserByUsername(String username);

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
    public List<UmsResource> getResourceListByUserId(Long userId);

    /**
     * 获取 用户缓存Service
     *
     * @return
     */
    public UmsUserCacheService getCacheService();


    /**
     * 用户注册
     *
     * @param registerReqDTO
     * @return
     */
    Result<UmsUser> register(RegisterReqDTO registerReqDTO);

    /**
     * 用户登录
     *
     * @param loginReqDTO
     * @return
     */
    Result<LoginResDTO> login(LoginReqDTO loginReqDTO);


    /**
     * 刷新token
     *
     * @param request
     * @return
     */
    Result<RefreshTokenResDTO> refreshToken(HttpServletRequest request);


    /**
     * 获取当前登录用户信息
     *
     * @param principal
     * @return
     */
    Result<GetUserInfoResDTO> getUserInfo(Principal principal);


    /**
     * 根据用户名或姓名分页获取用户列表
     *
     * @param reqDTO
     * @param pageSize
     * @param pageNum
     * @return
     */
    Result<PageInfo<UmsUser>> pageUserInfoByKeyword(PageUserInfoByKeywordReqDTO reqDTO, Integer pageSize, Integer pageNum);


    /**
     * 获取指定用户信息
     *
     * @param userId
     * @return
     */
    Result<UmsUser> getUserInfoByUserId(Long userId);


    /**
     * 修改指定用户信息
     *
     * @param userId
     * @param umsUser
     * @return
     */
    Result updateUserInfoByUserId(Long userId, UmsUser umsUser);


    /**
     * 修改指定用户密码
     *
     * @param reqDTO
     * @return
     */
    Result updatePassword(UpdatePasswordReqDTO reqDTO);

    /**
     * 删除指定用户信息
     *
     * @param userId
     * @return
     */
    Result deleteUserByUserId(Long userId);


    /**
     * 修改帐号状态
     *
     * @param userId
     * @return
     */
    Result updateUserStatus(Long userId);

}
