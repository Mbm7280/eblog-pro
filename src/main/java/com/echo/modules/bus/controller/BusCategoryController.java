package com.echo.modules.bus.controller;


import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.service.BusCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/admin/busCategory")
public class BusCategoryController {

    @Autowired
    private BusCategoryService busCategoryService;

    @ApiOperation(value = "根据分类ID获取分类信息")
    @GetMapping(value = "/getCategoryByID/{categoryID}")
    public Result<BusCategory> getCategoryByID(@PathVariable String categoryID) {
        return busCategoryService.getCategoryByID(categoryID);
    }

    @ApiOperation(value = "获取所有分类信息")
    @GetMapping(value = "/getAllCategoryList")
    public Result<List<BusCategory>> getAllCategoryList() {
        return busCategoryService.getAllCategoryList();
    }

    @ApiOperation(value = "根据分类名称获取所有分类信息")
    @GetMapping(value = "/getAllCategoryListByCateName")
    public Result<List<BusCategory>> getAllCategoryListByCateName(@RequestParam String categoryName) {
        return busCategoryService.getAllCategoryListByCateName(categoryName);
    }

}

