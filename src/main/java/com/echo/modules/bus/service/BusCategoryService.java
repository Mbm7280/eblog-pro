package com.echo.modules.bus.service;

import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
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
public interface BusCategoryService extends IService<BusCategory> {


    Result<BusCategory> getCategoryByID(@PathVariable String categoryID);

    Result<List<BusCategory>> getAllCategoryList();

    Result<List<BusCategory>> getAllCategoryListByCateName(@RequestParam String categoryName);

}
