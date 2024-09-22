package com.echo.modules.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.common.utils.GenegateIDUtil;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.dto.ResGetArticleByArticleIDDTO;
import com.echo.modules.bus.mapper.BusCategoryMapper;
import com.echo.modules.bus.model.BusArticle;
import com.echo.modules.bus.mapper.BusArticleMapper;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.model.BusComment;
import com.echo.modules.bus.service.BusArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.echo.common.constant.CommonConstant.*;
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

    @Autowired
    private BusCategoryMapper busCategoryMapper;

    @Override
    public Result<List<BusArticle>> getAllArticleList(String articleType) {
        LambdaQueryWrapper<BusArticle> queryWrapper = new LambdaQueryWrapper<BusArticle>();
        queryWrapper.ne(BusArticle::getArticleStatus, DELETED);
        if (StrUtil.isNotBlank(articleType)) {
            queryWrapper.eq(BusArticle::getArticleType, articleType);
        }
        List<BusArticle> busArticles = busArticleMapper.selectList(queryWrapper);
        return Result.success(busArticles);
    }

    @Override
    public Result<PageInfo<BusArticle>> getAllPageArticleList(String articleTitle, Integer pageNum, Integer pageSize) {
        Page<BusArticle> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<BusArticle> busArticleLambdaQueryWrapper = new LambdaQueryWrapper<BusArticle>();
        busArticleLambdaQueryWrapper.ne(BusArticle::getArticleStatus, DELETED);

        if (StringUtils.isNotEmpty(articleTitle)) {
            busArticleLambdaQueryWrapper.like(BusArticle::getArticleTitle, articleTitle);
        }

        Page<BusArticle> busArticlePage = page (page, busArticleLambdaQueryWrapper);

        PageInfo<BusArticle> busArticlePageInfo = PageInfo.restPage(busArticlePage);

        return Result.success(busArticlePageInfo);
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

    @Override
    public Result addOrEditArticle(BusArticle busArticle) {
        if(StrUtil.isNotBlank(busArticle.getId())) {
            busArticle.setUpdateTime(new Date());
            busArticleMapper.updateById(busArticle);
        } else {
            String articleID = GenegateIDUtil.generateArticleID();
            busArticle.setId(articleID);
            busArticle.setArticleStatus(EXIST);
            busArticle.setCreateTime(new Date());
            busArticle.setUpdateTime(new Date());
            save(busArticle);
        }
        return Result.success();
    }

    @Override
    public Result<ResGetArticleByArticleIDDTO> getArticleByArticleID(String articleID) {
        ResGetArticleByArticleIDDTO result = new ResGetArticleByArticleIDDTO();
        LambdaQueryWrapper<BusArticle> queryWrapper = new LambdaQueryWrapper<BusArticle>();
        queryWrapper.ne(BusArticle::getArticleStatus, DELETED);
        queryWrapper.eq(BusArticle::getId, articleID);
        BusArticle busArticle = busArticleMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(busArticle,result);
        String categoryId = busArticle.getCategoryId();
        BusCategory busCategory = busCategoryMapper.selectById(categoryId);
        result.setCategoryName(busCategory.getCategoryName());
        return Result.success(result);
    }

    @Override
    public Result delArticleBatch(List<String> articleIdList) {
        List<BusArticle> busArticleList = busArticleMapper.selectBatchIds(articleIdList);
        if(CollUtil.isNotEmpty(busArticleList)) {
            busArticleList.forEach(busArticle -> {
                busArticle.setUpdateTime(new Date());
                busArticle.setArticleStatus(DELETED);
                updateById(busArticle);
            });
        }
        return Result.success();
    }
}
