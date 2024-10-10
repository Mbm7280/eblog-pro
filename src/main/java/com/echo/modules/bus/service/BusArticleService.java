package com.echo.modules.bus.service;

import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.dto.GetTopAndRecommendArticlesResDTO;
import com.echo.dto.ResGetArticleByArticleIDDTO;
import com.echo.modules.bus.model.BusArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
public interface BusArticleService extends IService<BusArticle> {

    Result<List<BusArticle>> getAllArticleList(String articleType);

    Result<PageInfo<BusArticle>> getAllPageArticleList(String articleTitle, Integer pageNum, Integer pageSize);

    Result delArticle(String articleID);

    Result addOrEditArticle(@RequestBody BusArticle busArticle);

    Result<ResGetArticleByArticleIDDTO> getArticleByArticleID(String articleID);

    Result delArticleBatch(List<String> articleIdList);

//  Front-Api
    Result<GetTopAndRecommendArticlesResDTO> getTopAndRecommendArticles();

}
