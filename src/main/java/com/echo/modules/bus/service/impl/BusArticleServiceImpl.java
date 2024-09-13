package com.echo.modules.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusArticle;
import com.echo.modules.bus.mapper.BusArticleMapper;
import com.echo.modules.bus.service.BusArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.echo.common.constant.CommonConstant.DELETED;
import static com.echo.common.constant.CommonConstant.ZERO;
import static com.echo.config.api.ResultCode.THE_ARTICLE_IS_NOT_EXIST;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Service
public class BusArticleServiceImpl extends ServiceImpl<BusArticleMapper, BusArticle> implements BusArticleService {

    @Autowired
    private BusArticleMapper busArticleMapper;

    @Override
    public Result<List<BusArticle>> getAllArticleList(String articleType) {
        LambdaQueryWrapper<BusArticle> queryWrapper = new LambdaQueryWrapper<BusArticle>();
        if (StrUtil.isNotBlank(articleType)) {
            queryWrapper.eq(BusArticle::getArticleType, articleType);
        }
        List<BusArticle> busArticles = busArticleMapper.selectList(queryWrapper);
        return Result.success(busArticles);
    }

    @Override
    public Result delArticle(String articleID) {
        BusArticle busArticle = busArticleMapper.selectById(articleID);
        if(ObjUtil.isEmpty(busArticle)) {
            return Result.failed(THE_ARTICLE_IS_NOT_EXIST);
        }
        busArticle.setArticleStatus(DELETED);
        busArticle.setUpdateTime(new Date());
        return busArticleMapper.updateById(busArticle) > ZERO ? Result.success() : Result.failed();
    }
}
