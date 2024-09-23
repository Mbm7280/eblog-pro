package com.echo.modules.bus.controller;

import com.echo.config.api.Result;
import com.echo.dto.GetHomepageInfoResDTO;
import com.echo.modules.bus.service.BusHomepageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin/busHomepage")
public class BusHomepageController {

    @Autowired
    private BusHomepageService busHomepageService;

    @ApiOperation(value = "获取主页数据信息")
    @GetMapping(value = "/getHomepageInfo")
    public Result<GetHomepageInfoResDTO> getHomepageInfo() {
        return busHomepageService.getHomepageInfo();
    }


}
