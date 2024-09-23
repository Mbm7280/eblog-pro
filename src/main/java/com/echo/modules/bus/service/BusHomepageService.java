package com.echo.modules.bus.service;

import com.echo.config.api.Result;
import com.echo.dto.GetHomepageInfoResDTO;

public interface BusHomepageService {

    Result<GetHomepageInfoResDTO> getHomepageInfo();

}
