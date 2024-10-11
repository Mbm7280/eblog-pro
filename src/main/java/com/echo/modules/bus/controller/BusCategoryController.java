package com.echo.modules.bus.controller;


import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.dto.GetAllCategoriesAndCountResDTO;
import com.echo.dto.GetPageArticlesByCategoryIDResDTO;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.service.BusCategoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
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

    @ApiOperation(value = "分页获取所有分类信息")
    @GetMapping(value = "/getAllPageCategoryList")
    public Result<PageInfo<BusCategory>> getAllPageCategoryList(@RequestParam(required = false) String cateName,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busCategoryService.getAllPageCategoryList(cateName, pageNum, pageSize);
    }

    @ApiOperation(value = "新增或修改分类")
    @PostMapping(value = "/addOrEditCategory")
    public Result addOrEditCategory(@RequestBody BusCategory busCategory) {
        return busCategoryService.addOrEditCategory(busCategory);
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping(value = "/delCategory/{cateID}")
    public Result delCategory(@PathVariable String cateID) {
        return busCategoryService.delCategory(cateID);
    }

    @ApiOperation(value = "批量删除分类")
    @DeleteMapping(value = "/delCategoryBatch")
    public Result delCategoryBatch(@RequestBody List<String> cateIDList) {
        return busCategoryService.delCategoryBatch(cateIDList);
    }

//    Front-Api
    @ApiOperation(value = "获取所有分类信息以及对应的文章数量")
    @GetMapping(value = "/getAllCategoriesAndCount")
    public Result<List<GetAllCategoriesAndCountResDTO>> getAllCategoriesAndCount() {
        return busCategoryService.getAllCategoriesAndCount();
    }



}

