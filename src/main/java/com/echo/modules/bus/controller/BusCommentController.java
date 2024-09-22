package com.echo.modules.bus.controller;


import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusComment;
import com.echo.modules.bus.service.BusCommentService;
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
@RequestMapping("/admin/busComment")
public class BusCommentController {

    @Autowired
    private BusCommentService busCommentService;

    @ApiOperation(value = "分页获取所有评论信息")
    @GetMapping(value = "/getAllPageCommentList")
    public Result<PageInfo<BusComment>> getAllPageCommentList(@RequestParam(required = false) String commentContent,
                                                               @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busCommentService.getAllPageCommentList(commentContent,pageNum,pageSize);
    }

    @ApiOperation(value = "新增或修改资源")
    @PostMapping(value = "/addOrEditComment")
    public Result addOrEditComment(@RequestBody BusComment busComment) {
        return busCommentService.addOrEditComment(busComment);
    }

    @ApiOperation(value = "删除资源")
    @DeleteMapping(value = "/delComment/{commentID}")
    public Result delComment(@PathVariable String commentID) {
        return busCommentService.delComment(commentID);
    }

    @ApiOperation(value = "批量删除资源")
    @DeleteMapping(value = "/delCommentBatch")
    public Result delCommentBatch(@RequestBody List<String> commentIDList) {
        return busCommentService.delCommentBatch(commentIDList);
    }

}

