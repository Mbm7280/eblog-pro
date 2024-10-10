package com.echo.modules.bus.controller;


import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.dto.GetTopAndRecommendArticlesResDTO;
import com.echo.dto.ResGetArticleByArticleIDDTO;
import com.echo.modules.bus.model.BusArticle;
import com.echo.modules.bus.model.BusComment;
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

    @ApiOperation(value = "分页获取获取所有文章")
    @GetMapping(value = "/getAllPageArticleList")
    public Result<PageInfo<BusArticle>> getAllPageArticleList(@RequestParam(required = false) String articleTitle,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busArticleService.getAllPageArticleList(articleTitle,pageNum,pageSize);
    }

    @ApiOperation(value = "删除文章")
    @DeleteMapping(value = "/delArticle/{articleID}")
    public Result delArticle(@PathVariable String articleID) {
        return busArticleService.delArticle(articleID);
    }


    @ApiOperation(value = "新增或修改文章")
    @PostMapping(value = "/addOrEditArticle")
    public Result addOrEditArticle(@RequestBody BusArticle busArticle) {
        return busArticleService.addOrEditArticle(busArticle);
    }


    @ApiOperation(value = "根据 articleId 获取对应的文章信息")
    @GetMapping(value = "/getArticleByArticleID")
    public Result<ResGetArticleByArticleIDDTO> getArticleByArticleID(@RequestParam String articleID) {
        return busArticleService.getArticleByArticleID(articleID);
    }

    @ApiOperation(value = "批量删除文章")
    @DeleteMapping(value = "/delArticleBatch")
    public Result delArticleBatch(@RequestBody List<String> articleIdList) {
        return busArticleService.delArticleBatch(articleIdList);
    }

//    Front-Api

    @ApiOperation(value = "用户推荐文章")
    @GetMapping(value = "/getTopAndRecommendArticles")
    public Result<GetTopAndRecommendArticlesResDTO> getTopAndRecommendArticles() {
        return busArticleService.getTopAndRecommendArticles();
    }



}

