package com.echo.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.modules.ums.model.UmsMenu;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2024-04-16
 */
public interface UmsMenuMapper extends BaseMapper<UmsMenu> {

    /**
     * 根据 [用户id] 获取该用户对应的菜单信息
     * @param userId
     * @return
     */
    List<UmsMenu> getMenuListByUserId(Long userId);

}
