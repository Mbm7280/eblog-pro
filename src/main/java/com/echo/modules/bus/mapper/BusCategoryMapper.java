package com.echo.modules.bus.mapper;

import com.echo.dto.GetArticleCountGroupByCateResDTO;
import com.echo.modules.bus.model.BusCategory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
public interface BusCategoryMapper extends BaseMapper<BusCategory> {

    List<GetArticleCountGroupByCateResDTO> getArticleCountGroupByCate();

}
