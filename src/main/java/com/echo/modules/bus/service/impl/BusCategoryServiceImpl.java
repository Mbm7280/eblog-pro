package com.echo.modules.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.common.utils.GenegateIDUtil;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.mapper.BusCategoryMapper;
import com.echo.modules.bus.service.BusCategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.echo.modules.ums.model.UmsUser;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.echo.common.constant.CommonConstant.DELETED;
import static com.echo.config.api.ResultCode.THE_CATEGORY_IS_NOT_EXIST;

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

    @Override
    public Result<PageInfo<BusCategory>> getAllPageCategoryList(String cateName, Integer pageNum,Integer pageSize) {
        Page<BusCategory> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<BusCategory> busCategoryLambdaQueryWrapper = new LambdaQueryWrapper<BusCategory>();
        busCategoryLambdaQueryWrapper.ne(BusCategory::getCateStatus, DELETED);

        if (StringUtils.isNotEmpty(cateName)) {
            busCategoryLambdaQueryWrapper.like(BusCategory::getCategoryName, cateName);
        }

        Page<BusCategory> busCategoryPage = page (page, busCategoryLambdaQueryWrapper);

        PageInfo<BusCategory> busCategoryPageInfo = PageInfo.restPage(busCategoryPage);

        return Result.success(busCategoryPageInfo);
    }

    @Override
    public Result addOrEditCategory(BusCategory busCategory) {
        if (StrUtil.isNotBlank(busCategory.getId())) {
            busCategory.setUpdateTime(new Date());
            busCategoryMapper.updateById(busCategory);
        } else {
            String categoryID = GenegateIDUtil.generateACID();
            busCategory.setId(categoryID);
            busCategory.setCreateTime(new Date());
            busCategory.setUpdateTime(new Date());
            save(busCategory);
        }
        return Result.success();
    }

    @Override
    public Result deleteCategoryByCateID(String cateID) {
        BusCategory busCategory = getOne(new LambdaQueryWrapper<BusCategory>().eq(BusCategory::getId, cateID));
        if(ObjUtil.isEmpty(busCategory)) {
            return Result.failed(THE_CATEGORY_IS_NOT_EXIST);
        }
        busCategory.setUpdateTime(new Date());
        busCategory.setCateStatus(DELETED);
        updateById(busCategory);
        return Result.success();
    }

    @Override
    public Result delCateBatchByCateID(List<String> cateIDList) {
        List<BusCategory> busCategoryList = busCategoryMapper.selectBatchIds(cateIDList);
        if(CollUtil.isNotEmpty(busCategoryList)) {
            busCategoryList.forEach(busCategory -> {
                busCategory.setUpdateTime(new Date());
                busCategory.setCateStatus(DELETED);
                updateById(busCategory);
            });
        }
        return Result.success();
    }


}
