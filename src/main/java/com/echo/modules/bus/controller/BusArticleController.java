package com.echo.modules.bus.controller;


import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.dto.*;
import com.echo.modules.bus.model.BusArticle;
import com.echo.modules.bus.service.BusArticleService;
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
@RequestMapping("/api/busArticle")
public class BusArticleController {

    @Autowired
    private BusArticleService busArticleService;

    @ApiOperation(value = "用户获取所有文章")
    @GetMapping(value = "/admin/getAllArticleList")
    public Result<List<BusArticle>> getAllArticleList(String articleType) {
        return busArticleService.getAllArticleList(articleType);
    }

    @ApiOperation(value = "分页获取获取所有文章")
    @GetMapping(value = "/admin/getAllPageArticleList")
    public Result<PageInfo<BusArticle>> getAllPageArticleList(@RequestParam(required = false) String articleTitle,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busArticleService.getAllPageArticleList(articleTitle,pageNum,pageSize);
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping(value = "/admin/delArticle/{articleID}")
    public Result delArticle(@PathVariable String articleID) {
        return busArticleService.delArticle(articleID);
    }


    @ApiOperation(value = "新增或修改文章")
    @PostMapping(value = "/admin/addOrEditArticle")
    public Result addOrEditArticle(@RequestBody BusArticle busArticle) {
        return busArticleService.addOrEditArticle(busArticle);
    }


    @ApiOperation(value = "根据 articleId 获取对应的文章信息")
    @GetMapping(value = "/admin/getArticleByArticleID")
    public Result<ResGetArticleByArticleIDDTO> getArticleByArticleID(@RequestParam String articleID) {
        return busArticleService.getArticleByArticleID(articleID);
    }

    @ApiOperation(value = "批量删除文章")
    @DeleteMapping(value = "/admin/delArticleBatch")
    public Result delArticleBatch(@RequestBody List<String> articleIdList) {
        return busArticleService.delArticleBatch(articleIdList);
    }

//    Front-Api

    @ApiOperation(value = "用户推荐文章")
    @GetMapping(value = "/front/getTopAndRecommendArticles")
    public Result<GetTopAndRecommendArticlesResDTO> getTopAndRecommendArticles() {
        return busArticleService.getTopAndRecommendArticles();
    }

    @ApiOperation(value = "分页且根据分类ID获取对应的文章列表")
    @GetMapping(value = "/front/getPageArticlesByCategoryID")
    public Result<GetPageArticlesByCategoryIDResDTO> getPageArticlesByCategoryID(@RequestParam(required = true) String categoryID,
                                                                                           @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                                           @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busArticleService.getPageArticlesByCategoryID(categoryID, pageNum, pageSize);
    }

    @ApiOperation(value = "分页获取获取所有文章")
    @GetMapping(value = "/front/getAllPageArticles")
    public Result<GetAllPageArticlesResDTO> getAllPageArticles(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busArticleService.getAllPageArticles(pageNum,pageSize);
    }


    @ApiOperation(value = "分页获取获取所有文章")
    @GetMapping(value = "/front/getAllPageArchives")
    public Result<GetAllPageArchivesResDTO> getAllPageArchives(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busArticleService.getAllPageArchives(pageNum,pageSize);
    }


    @ApiOperation(value = "根据文章获取文章")
    @GetMapping(value = "/front/getArticleInfoByArticleID/{articleID}")
    public Result<GetArticleInfoByArticleIDResDTO> getArticleInfoByArticleID(@PathVariable String articleID) {
        return busArticleService.getArticleInfoByArticleID(articleID);
    }


}

