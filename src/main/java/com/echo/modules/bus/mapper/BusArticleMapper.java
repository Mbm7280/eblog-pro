package com.echo.modules.bus.mapper;

import com.echo.dto.GetArticleStatisticsResDTO;
import com.echo.modules.bus.model.BusArticle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
public interface BusArticleMapper extends BaseMapper<BusArticle> {

    List<GetArticleStatisticsResDTO> getArticleStatistics();

}
