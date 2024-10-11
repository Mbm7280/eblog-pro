package com.echo.modules.bus.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.echo.config.api.Result;
import com.echo.dto.GetWebInfoResDTO;
import com.echo.modules.bus.mapper.BusArticleMapper;
import com.echo.modules.bus.mapper.BusCategoryMapper;
import com.echo.modules.bus.mapper.BusFriendLinkMapper;
import com.echo.modules.bus.model.BusArticle;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.model.BusFriendLink;
import com.echo.modules.bus.service.BusWebInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.echo.common.constant.CommonConstant.DELETED;

@Service
public class BusWebInfoServiceImpl implements BusWebInfoService {

    @Autowired
    private BusArticleMapper busArticleMapper;

    @Autowired
    private BusCategoryMapper busCategoryMapper;

    @Autowired
    private BusFriendLinkMapper busFriendLinkMapper;

    @Override
    public Result<GetWebInfoResDTO> getWebInfo() {
        GetWebInfoResDTO resDTO = new GetWebInfoResDTO();

        Long articleCount = busArticleMapper.selectCount(new LambdaQueryWrapper<BusArticle>().ne(BusArticle::getArticleStatus, DELETED));
        Long categoryCount = busCategoryMapper.selectCount(new LambdaQueryWrapper<BusCategory>().ne(BusCategory::getCateStatus, DELETED));
        Long friendLinkCount = busFriendLinkMapper.selectCount(new LambdaQueryWrapper<BusFriendLink>().ne(BusFriendLink::getFriLinkStatus, DELETED));

        resDTO.setArticleCount(Convert.toInt(articleCount));
        resDTO.setCategoryCount(Convert.toInt(categoryCount));
        resDTO.setFriendLinkCount(Convert.toInt(friendLinkCount));
        resDTO.setNotice("6666666");

        return Result.success(resDTO);
    }
}
