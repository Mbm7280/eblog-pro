package com.echo.modules.ums.controller;


import com.echo.config.annos.WebLogAnno;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.ums.dto.req.LoginReqDTO;
import com.echo.modules.ums.dto.req.PageUserInfoByKeywordReqDTO;
import com.echo.modules.ums.dto.req.RegisterReqDTO;
import com.echo.modules.ums.dto.req.UpdatePasswordReqDTO;
import com.echo.modules.ums.dto.res.GetUserInfoResDTO;
import com.echo.modules.ums.dto.res.LoginResDTO;
import com.echo.modules.ums.dto.res.RefreshTokenResDTO;
import com.echo.modules.ums.model.UmsUser;
import com.echo.modules.ums.service.UmsRoleService;
import com.echo.modules.ums.service.UmsUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

import static com.echo.config.api.ResultCode.THE_AUTHORIZED_FAILED;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Echo
 * @since 2024-04-16
 */
@RestController
@RequestMapping("/ums/umsUser")
public class UmsUserController {

    @Autowired
    private UmsUserService userService;

    @Autowired
    private UmsRoleService roleService;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    @WebLogAnno(description = "用户注册")
    public Result<UmsUser> register(@Validated @RequestBody RegisterReqDTO registerReqDTO) {
        return userService.register(registerReqDTO);
    }


    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    @WebLogAnno(description = "登录")
    public Result<LoginResDTO> login(@Validated @RequestBody LoginReqDTO loginReqDTO) {
        return userService.login(loginReqDTO);
    }

    @ApiOperation(value = "刷新token")
    @PostMapping(value = "/refreshToken")
    @WebLogAnno(description = "刷新token")
    public Result<RefreshTokenResDTO> refreshToken(HttpServletRequest request) {
        return userService.refreshToken(request);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/getUserInfo")
    @WebLogAnno(description = "获取当前登录用户信息")
    public Result<GetUserInfoResDTO> getUserInfo(Principal principal) {
        if (principal == null) {
            return Result.failed(THE_AUTHORIZED_FAILED);
        }
        return userService.getUserInfo(principal);
    }

    @ApiOperation(value = "登出功能")
    @PostMapping(value = "/logout")
    @WebLogAnno(description = "登出功能")
    public Result logout() {
        return Result.success(null);
    }

    @ApiOperation("根据用户名或姓名分页获取用户列表")
    @GetMapping(value = "/pageUserInfoByKeyword")
    @WebLogAnno(description = "根据用户名或姓名分页获取用户列表")
    public Result<PageInfo<UmsUser>> pageUserInfoByKeyword(@RequestBody(required = false) PageUserInfoByKeywordReqDTO reqDTO,
                                                           @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        return userService.pageUserInfoByKeyword(reqDTO, pageSize, pageNum);
    }


    @ApiOperation("获取指定用户信息")
    @GetMapping(value = "/getUserInfoByUserId/{userId}")
    @WebLogAnno(description = "获取指定用户信息")
    public Result<UmsUser> getUserInfoByUserId(@PathVariable Long userId) {
        return userService.getUserInfoByUserId(userId);
    }

    @ApiOperation("修改指定用户信息")
    @PostMapping(value = "/updateUserInfoByUserId/{userId}")
    @WebLogAnno(description = "修改指定用户信息")
    public Result updateUserInfoByUserId(@PathVariable Long userId, @RequestBody UmsUser umsUser) {
        return userService.updateUserInfoByUserId(userId, umsUser);
    }

    @ApiOperation("修改指定用户密码")
    @PostMapping(value = "/updatePassword")
    @WebLogAnno(description = "修改指定用户密码")
    public Result updatePassword(@Validated @RequestBody UpdatePasswordReqDTO reqDTO) {
        return userService.updatePassword(reqDTO);
    }


    @ApiOperation("删除指定用户信息")
    @GetMapping(value = "/deleteUserByUserId/{userId}")
    @WebLogAnno(description = "删除指定用户信息")
    public Result deleteUserByUserId(@PathVariable Long userId) {
        return userService.deleteUserByUserId(userId);
    }

    @ApiOperation("修改帐号状态")
    @PostMapping(value = "/updateUserStatus/{userId}")
    @WebLogAnno(description = "修改帐号状态")
    public Result updateUserStatus(@PathVariable Long userId) {
        return userService.updateUserStatus(userId);
    }

}

