package com.echo.modules.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.mapper.BusCategoryMapper;
import com.echo.modules.bus.service.BusCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Service
public class BusCategoryServiceImpl extends ServiceImpl<BusCategoryMapper, BusCategory> implements BusCategoryService {

    @Autowired
    private BusCategoryMapper busCategoryMapper;

    @Override
    public Result<BusCategory> getCategoryByID(String categoryID) {
        BusCategory busCategory = busCategoryMapper.selectById(categoryID);
        return ObjUtil.isNotEmpty(busCategory) ? Result.success(busCategory) : Result.failed();
    }

    @Override
    public Result<List<BusCategory>> getAllCategoryList() {
        List<BusCategory> busCategories = list();
        return Result.success(busCategories);
    }

    @Override
    public Result<List<BusCategory>> getAllCategoryListByCateName(String categoryName) {
        QueryWrapper<BusCategory> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("category_name", categoryName);
        List<BusCategory> busCategories = busCategoryMapper.selectList(queryWrapper);
        return Result.success(busCategories);
    }


}
