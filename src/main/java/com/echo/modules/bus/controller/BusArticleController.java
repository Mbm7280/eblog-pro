package com.echo.modules.bus.controller;


import com.echo.config.api.Result;
import com.echo.dto.ResGetArticleByArticleIDDTO;
import com.echo.modules.bus.model.BusArticle;
import com.echo.modules.bus.service.BusArticleService;
import com.echo.modules.ums.dto.req.RegisterReqDTO;
import com.echo.modules.ums.model.UmsUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/admin/busArticle")
public class BusArticleController {

    @Autowired
    private BusArticleService busArticleService;

    @ApiOperation(value = "用户获取所有文章")
    @GetMapping(value = "/getAllArticleList")
    public Result<List<BusArticle>> getAllArticleList(String articleType) {
        return busArticleService.getAllArticleList(articleType);
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping(value = "/delArticle/{articleID}")
    public Result delArticle(@PathVariable String articleID) {
        return busArticleService.delArticle(articleID);
    }


    @ApiOperation(value = "新增文章")
    @PostMapping(value = "/addOrEditArticle")
    public Result addOrEditArticle(@RequestBody BusArticle busArticle) {
        return busArticleService.addOrEditArticle(busArticle);
    }


    @ApiOperation(value = "根据 articleId 获取对应的文章信息")
    @GetMapping(value = "/getArticleByArticleID")
    public Result<ResGetArticleByArticleIDDTO> getArticleByArticleID(@RequestParam String articleID) {
        return busArticleService.getArticleByArticleID(articleID);
    }



}

