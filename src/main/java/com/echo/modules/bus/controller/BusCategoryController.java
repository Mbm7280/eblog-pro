package com.echo.modules.bus.controller;


import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.dto.GetAllPageCategoryListDTO;
import com.echo.modules.bus.model.BusArticle;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.service.BusCategoryService;
import com.echo.modules.ums.dto.req.PageUserInfoByKeywordReqDTO;
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

    @ApiOperation(value = "分页获取所有分类信息")
    @GetMapping(value = "/getAllPageCategoryList")
    public Result<PageInfo<BusCategory>> getAllPageCategoryList(@RequestParam(required = false) String cateName,
                                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                              @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return busCategoryService.getAllPageCategoryList(cateName,pageNum,pageSize);
    }

    @ApiOperation(value = "新增或修改分类")
    @PostMapping(value = "/addOrEditCategory")
    public Result addOrEditCategory(@RequestBody BusCategory busCategory) {
        return busCategoryService.addOrEditCategory(busCategory);
    }

    @ApiOperation(value = "删除分类")
    @DeleteMapping(value = "/deleteCategoryByCateID/{cateID}")
    public Result deleteCategoryByCateID(@PathVariable String cateID) {
        return busCategoryService.deleteCategoryByCateID(cateID);
    }

    @ApiOperation(value = "批量删除分类")
    @DeleteMapping(value = "/delCateBatchByCateID")
    public Result delCateBatchByCateID(@RequestBody List<String> cateIDList) {
        return busCategoryService.delCateBatchByCateID(cateIDList);
    }


}

