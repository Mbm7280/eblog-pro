package com.echo.modules.ums.controller;


import com.echo.config.annos.WebLogAnno;
import com.echo.config.api.Result;
import com.echo.modules.ums.dto.req.LoginReqDTO;
import com.echo.modules.ums.dto.req.RegisterReqDTO;
import com.echo.modules.ums.dto.res.GetUserInfoResDTO;
import com.echo.modules.ums.dto.res.LoginResDTO;
import com.echo.modules.ums.model.UmsUser;
import com.echo.modules.ums.service.UmsUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static com.echo.config.api.ResultCode.THE_AUTHORIZED_FAILED;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@RestController
@RequestMapping("admin/umsUser")
public class UmsUserController {

    @Autowired
    private UmsUserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping(value = "/register")
    public Result<UmsUser> register(@Validated @RequestBody RegisterReqDTO registerReqDTO) {
        return userService.register(registerReqDTO);
    }


    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public Result<LoginResDTO> login(@Validated @RequestBody LoginReqDTO loginReqDTO) {
        return userService.login(loginReqDTO);
    }

    @ApiOperation(value = "获取当前登录用户信息")
    @GetMapping(value = "/getUserInfo")
    public Result<GetUserInfoResDTO> getUserInfo(Principal principal) {
        if (principal == null) {
            return Result.failed(THE_AUTHORIZED_FAILED);
        }
        return userService.getUserInfo(principal);
    }

}

