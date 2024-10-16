package com.echo.modules.bus.controller;


import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.dto.GetAllFriLinksResDTO;
import com.echo.modules.bus.model.BusFriendLink;
import com.echo.modules.bus.service.BusFriendLinkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/api/busFriendLink")
public class BusFriendLinkController {

    @Autowired
    private BusFriendLinkService busFriendLinkService;


    @ApiOperation(value = "分页获取所有分类信息")
    @GetMapping(value = "/getAllPageFriLinkList")
    public Result<PageInfo<BusFriendLink>> getAllPageFriLinkList(@RequestParam(required = false) String friLinkName,
                                                                 @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                 @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busFriendLinkService.getAllPageFriLinkList(friLinkName, pageNum, pageSize);
    }

    @ApiOperation(value = "新增或修改分类")
    @PostMapping(value = "/addOrEditFriLink")
    public Result addOrEditFriLink(@RequestBody BusFriendLink busFriendLink) {
        return busFriendLinkService.addOrEditFriLink(busFriendLink);
    }

    @ApiOperation(value = "删除友链")
    @DeleteMapping(value = "/delFriLink/{friLinkID}")
    public Result delFriLink(@PathVariable String friLinkID) {
        return busFriendLinkService.delFriLink(friLinkID);
    }

    @ApiOperation(value = "批量删除友链")
    @DeleteMapping(value = "/delFriLinkBatch")
    public Result delFriLinkBatch(@RequestBody List<String> friLinkIDList) {
        return busFriendLinkService.delFriLinkBatch(friLinkIDList);
    }


//  Front-Api
    @ApiOperation(value = "获取所有的友链")
    @GetMapping(value = "/front//getAllFriLinks")
    public Result<List<GetAllFriLinksResDTO>> getAllFriLinks() {
        return busFriendLinkService.getAllFriLinks();
    }

}

