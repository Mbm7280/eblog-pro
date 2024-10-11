package com.echo.modules.bus.controller;


import com.echo.config.api.Result;
import com.echo.dto.GetAboutInfoResDTO;
import com.echo.dto.GetWebInfoResDTO;
import com.echo.modules.bus.service.BusAboutService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/admin/busAbout")
public class BusAboutController {

    @Autowired
    private BusAboutService busAboutService;

    @ApiOperation(value = "获取关于信息")
    @GetMapping(value = "/getAboutInfo")
    public Result<GetAboutInfoResDTO> getAboutInfo() {
        return busAboutService.getAboutInfo();
    }


}

