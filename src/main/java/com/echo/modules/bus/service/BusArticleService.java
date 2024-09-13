package com.echo.modules.bus.service;

import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusArticle;
import com.baomidou.mybatisplus.extension.service.IService;

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

}
