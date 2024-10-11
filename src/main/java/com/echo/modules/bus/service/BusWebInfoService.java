package com.echo.modules.bus.service;

import com.echo.config.api.Result;
import com.echo.dto.GetWebInfoResDTO;

public interface BusWebInfoService {

    Result<GetWebInfoResDTO> getWebInfo();

}
