package com.echo.modules.bus.service;

import com.echo.config.api.Result;
import com.echo.dto.ResGetArticleByArticleIDDTO;
import com.echo.modules.bus.model.BusArticle;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.RequestBody;

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

    Result delArticle(String articleID);

    Result addOrEditArticle(@RequestBody BusArticle busArticle);

    Result<ResGetArticleByArticleIDDTO> getArticleByArticleID(String articleID);

}
