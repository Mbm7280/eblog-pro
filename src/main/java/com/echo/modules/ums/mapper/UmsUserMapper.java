package com.echo.modules.ums.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.echo.modules.ums.model.UmsUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Echo
 * @since 2024-04-16
 */
public interface UmsUserMapper extends BaseMapper<UmsUser> {

    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getUserIdList(@Param("resourceId") Long resourceId);


    /**
     * 根据note查询用户
     * @param note
     * @return
     */
    UmsUser selectUserByNote(@Param("note") String note);

}
