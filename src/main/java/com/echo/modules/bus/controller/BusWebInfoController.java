package com.echo.modules.bus.controller;


import com.echo.config.api.Result;
import com.echo.dto.GetWebInfoResDTO;
import com.echo.modules.bus.service.BusWebInfoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/busWebInfo")
public class BusWebInfoController {

    @Autowired
    private BusWebInfoService busWebInfoService;

    @ApiOperation(value = "获取网站信息")
    @GetMapping(value = "/front/getWebInfo")
    public Result<GetWebInfoResDTO> getWebInfo() {
        return busWebInfoService.getWebInfo();
    }

}
