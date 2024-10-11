package com.echo.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.common.utils.GenegateIDUtil;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.mapper.UmsResourceMapper;
import com.echo.modules.ums.service.UmsResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.echo.common.constant.CommonConstant.DELETED;
import static com.echo.common.constant.CommonConstant.EXIST;
import static com.echo.config.api.ResultCode.THE_RESOURCE_QUERY_FAILED;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Service
public class UmsResourceServiceImpl extends ServiceImpl<UmsResourceMapper, UmsResource> implements UmsResourceService {

    @Autowired
    private UmsResourceMapper umsResourceMapper;

    @Override
    public Result<PageInfo<UmsResource>> getAllPageResourceList(String resName, Integer pageNum, Integer pageSize) {
        Page<UmsResource> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<UmsResource> umsResourceLambdaQueryWrapper = new LambdaQueryWrapper<UmsResource>();
        umsResourceLambdaQueryWrapper.ne(UmsResource::getStatus, DELETED);

        if (StringUtils.isNotEmpty(resName)) {
            umsResourceLambdaQueryWrapper.like(UmsResource::getResName, resName);
        }

        Page<UmsResource> umsResourcePage = page (page, umsResourceLambdaQueryWrapper);

        PageInfo<UmsResource> umsResourcePageInfo = PageInfo.restPage(umsResourcePage,null);

        return Result.success(umsResourcePageInfo);
    }

    @Override
    public Result addOrEditResource(UmsResource umsResource) {
        if (StrUtil.isNotBlank(umsResource.getId())) {
            umsResource.setUpdateTime(new Date());
            umsResourceMapper.updateById(umsResource);
        } else {
            String resourceID = GenegateIDUtil.generateResourceID();
            umsResource.setId(resourceID);
            umsResource.setCreateTime(new Date());
            umsResource.setUpdateTime(new Date());
            umsResource.setStatus(EXIST);
            save(umsResource);
        }
        return Result.success();
    }

    @Override
    public Result delResource(String resID) {
        UmsResource umsResource = getOne(new LambdaQueryWrapper<UmsResource>().eq(UmsResource::getId, resID));
        if(ObjUtil.isEmpty(umsResource)) {
            return Result.failed(THE_RESOURCE_QUERY_FAILED);
        }
        umsResource.setUpdateTime(new Date());
        umsResource.setStatus(DELETED);
        updateById(umsResource);
        return Result.success();
    }

    @Override
    public Result delResourceBatch(List<String> resIDList) {
        List<UmsResource> umsResourceList = umsResourceMapper.selectBatchIds(resIDList);
        if(CollUtil.isNotEmpty(umsResourceList)) {
            umsResourceList.forEach(umsResource -> {
                umsResource.setUpdateTime(new Date());
                umsResource.setStatus(DELETED);
                updateById(umsResource);
            });
        }
        return Result.success();
    }
}
