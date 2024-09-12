package com.echo.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.modules.ums.model.UmsRole;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2024-04-16
 */
public interface UmsRoleMapper extends BaseMapper<UmsRole> {

    /**
     * 根据 【用户id】 获取该用户角色信息
     * @param userId
     * @return
     */
    List<UmsRole> getRoleListByUserId(Long userId);


}
