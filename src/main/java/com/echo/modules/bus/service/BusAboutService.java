package com.echo.modules.bus.service;

import com.echo.config.api.Result;
import com.echo.dto.GetAboutInfoResDTO;
import com.echo.dto.GetWebInfoResDTO;
import com.echo.modules.bus.model.BusAbout;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
public interface BusAboutService extends IService<BusAbout> {

    Result<GetAboutInfoResDTO> getAboutInfo();

}
