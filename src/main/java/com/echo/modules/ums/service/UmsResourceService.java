package com.echo.modules.ums.service;

import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.ums.model.UmsResource;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
public interface UmsResourceService extends IService<UmsResource> {


    Result<PageInfo<UmsResource>> getAllPageResourceList(String resName, Integer pageNum, Integer pageSize);


    Result addOrEditResource(UmsResource umsResource);


    Result delResource(String resID);


    Result delResourceBatch(List<String> resIDList);


}
