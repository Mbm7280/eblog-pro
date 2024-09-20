package com.echo.modules.bus.controller;


import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.model.BusFriendLink;
import com.echo.modules.bus.service.BusFriendLinkService;
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
@RequestMapping("/admin/busFriendLink")
public class BusFriendLinkController {

    @Autowired
    private BusFriendLinkService busFriendLinkService;


    @ApiOperation(value = "分页获取所有分类信息")
    @GetMapping(value = "/getAllPageFriLinkList")
    public Result<PageInfo<BusFriendLink>> getAllPageFriLinkList(@RequestParam(required = false) String friLinkName,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busFriendLinkService.getAllPageFriLinkList(friLinkName,pageNum,pageSize);
    }

    @ApiOperation(value = "新增或修改分类")
    @PostMapping(value = "/addOrEditFriLink")
    public Result addOrEditFriLink(@RequestBody BusFriendLink busFriendLink) {
        return busFriendLinkService.addOrEditFriLink(busFriendLink);
    }

    @ApiOperation(value = "删除友链")
    @DeleteMapping(value = "/delFriLinkByID/{friLinkID}")
    public Result delFriLinkByID(@PathVariable String friLinkID) {
        return busFriendLinkService.delFriLinkByID(friLinkID);
    }

    @ApiOperation(value = "批量删除友链")
    @DeleteMapping(value = "/delFriLinkBatchByIDS")
    public Result delFriLinkBatchByIDS(@RequestBody List<String> friLinkIDList) {
        return busFriendLinkService.delFriLinkBatchByIDS(friLinkIDList);
    }

}

