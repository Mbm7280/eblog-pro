package com.echo.modules.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.common.utils.GenegateIDUtil;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.model.BusFriendLink;
import com.echo.modules.bus.mapper.BusFriendLinkMapper;
import com.echo.modules.bus.service.BusFriendLinkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.echo.common.constant.CommonConstant.DELETED;
import static com.echo.common.constant.CommonConstant.EXIST;
import static com.echo.config.api.ResultCode.THE_CATEGORY_IS_NOT_EXIST;
import static com.echo.config.api.ResultCode.THE_FRIENDLINK_IS_NOT_EXIST;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Service
public class BusFriendLinkServiceImpl extends ServiceImpl<BusFriendLinkMapper, BusFriendLink> implements BusFriendLinkService {

    @Autowired
    private BusFriendLinkMapper busFriendLinkMapper;

    @Override
    public Result<PageInfo<BusFriendLink>> getAllPageFriLinkList(String friLinkName, Integer pageNum, Integer pageSize) {
        Page<BusFriendLink> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<BusFriendLink> busFriendLinkLambdaQueryWrapper = new LambdaQueryWrapper<BusFriendLink>();
        busFriendLinkLambdaQueryWrapper.ne(BusFriendLink::getFriLinkStatus, DELETED);

        if (StringUtils.isNotEmpty(friLinkName)) {
            busFriendLinkLambdaQueryWrapper.like(BusFriendLink::getFirLinkName, friLinkName);
        }

        Page<BusFriendLink> busFriendLinkPage = page (page, busFriendLinkLambdaQueryWrapper);

        PageInfo<BusFriendLink> busFriendLinkPageInfo = PageInfo.restPage(busFriendLinkPage);

        return Result.success(busFriendLinkPageInfo);
    }

    @Override
    public Result addOrEditFriLink(BusFriendLink busFriendLink) {
        if (StrUtil.isNotBlank(busFriendLink.getId())) {
            busFriendLink.setUpdateTime(new Date());
            busFriendLinkMapper.updateById(busFriendLink);
        } else {
            String categoryID = GenegateIDUtil.generateACID();
            busFriendLink.setId(categoryID);
            busFriendLink.setCreateTime(new Date());
            busFriendLink.setUpdateTime(new Date());
            busFriendLink.setFriLinkStatus(EXIST);
            save(busFriendLink);
        }
        return Result.success();
    }

    @Override
    public Result delFriLinkByID(String friLinkID) {
        BusFriendLink busFriendLink = getOne(new LambdaQueryWrapper<BusFriendLink>().eq(BusFriendLink::getId, friLinkID));
        if(ObjUtil.isEmpty(busFriendLink)) {
            return Result.failed(THE_FRIENDLINK_IS_NOT_EXIST);
        }
        busFriendLink.setUpdateTime(new Date());
        busFriendLink.setFriLinkStatus(DELETED);
        updateById(busFriendLink);
        return Result.success();
    }

    @Override
    public Result delFriLinkBatchByIDS(List<String> friLinkIDList) {
        List<BusFriendLink> busFriendLinkList = busFriendLinkMapper.selectBatchIds(friLinkIDList);
        if(CollUtil.isNotEmpty(busFriendLinkList)) {
            busFriendLinkList.forEach(busFriendLink -> {
                busFriendLink.setUpdateTime(new Date());
                busFriendLink.setFriLinkStatus(DELETED);
                updateById(busFriendLink);
            });
        }
        return Result.success();
    }
}
