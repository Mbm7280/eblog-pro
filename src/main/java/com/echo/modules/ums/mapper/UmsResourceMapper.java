package com.echo.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.modules.ums.model.UmsResource;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2024-04-16
 */
public interface UmsResourceMapper extends BaseMapper<UmsResource> {


    /**
     * 根据 [用户id] 获取该用户对应的资源信息
     * @param userId
     * @return
     */
    List<UmsResource> getResourceList(Long userId);


}
