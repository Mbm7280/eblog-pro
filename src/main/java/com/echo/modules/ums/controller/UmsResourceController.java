package com.echo.modules.ums.controller;


import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusFriendLink;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.service.UmsResourceService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/admin/umsResource")
public class UmsResourceController {

    @Autowired
    private UmsResourceService umsResourceService;

    @ApiOperation(value = "分页获取所有资源信息")
    @GetMapping(value = "/getAllPageResourceList")
    public Result<PageInfo<UmsResource>> getAllPageResourceList(@RequestParam(required = false) String resName,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return umsResourceService.getAllPageResourceList(resName,pageNum,pageSize);
    }

    @ApiOperation(value = "新增或修改资源")
    @PostMapping(value = "/addOrEditResource")
    public Result addOrEditResource(@RequestBody UmsResource umsResource) {
        return umsResourceService.addOrEditResource(umsResource);
    }

    @ApiOperation(value = "删除资源")
    @DeleteMapping(value = "/delResource/{resID}")
    public Result delResource(@PathVariable String resID) {
        return umsResourceService.delResource(resID);
    }

    @ApiOperation(value = "批量删除资源")
    @DeleteMapping(value = "/delResourceBatch")
    public Result delResourceBatch(@RequestBody List<String> resIDList) {
        return umsResourceService.delResourceBatch(resIDList);
    }


}

