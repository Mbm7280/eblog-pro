package com.echo.modules.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.common.utils.GenegateIDUtil;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.dto.*;
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

import java.util.AbstractList;
import java.util.ArrayList;
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

        PageInfo<BusArticle> busArticlePageInfo = PageInfo.restPage(busArticlePage,null);

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


//  Front-Api

    @Override
    public Result<GetTopAndRecommendArticlesResDTO> getTopAndRecommendArticles() {
        List<BusArticle> recommendArticleList = busArticleMapper.selectList(new LambdaQueryWrapper<BusArticle>().eq(BusArticle::getArticleStatus, RECOMMEND));
        BusArticle topArticle = busArticleMapper.selectOne(new LambdaQueryWrapper<BusArticle>().eq(BusArticle::getArticleStatus, TOP));

        List<TopAndRecommendArticlesDTO> recommendArticlesDTOList = new ArrayList<>();
        TopAndRecommendArticlesDTO topArticleDTO = new TopAndRecommendArticlesDTO();
        BeanUtils.copyProperties(topArticle,topArticleDTO);

        if(CollUtil.isNotEmpty(recommendArticleList)) {
            recommendArticleList.forEach(a -> {
                TopAndRecommendArticlesDTO recommendArticlesDTO = new TopAndRecommendArticlesDTO();
                BeanUtils.copyProperties(a,recommendArticlesDTO);
                BusCategory busCategory = busCategoryMapper.selectById(a.getCategoryId());
                recommendArticlesDTO.setCategoryName(busCategory.getCategoryName());
                recommendArticlesDTOList.add(recommendArticlesDTO);
            });
        }

        BusCategory busCategory = busCategoryMapper.selectById(topArticle.getCategoryId());
        topArticleDTO.setCategoryName(busCategory.getCategoryName());

        GetTopAndRecommendArticlesResDTO resDTO = new GetTopAndRecommendArticlesResDTO();
        resDTO.setTopArticle(topArticleDTO);
        resDTO.setRecommendArticles(recommendArticlesDTOList);
        return Result.success(resDTO);
    }

    @Override
    public Result<GetPageArticlesByCategoryIDResDTO> getPageArticlesByCategoryID(String categoryID, Integer pageNum, Integer pageSize) {
        GetPageArticlesByCategoryIDResDTO resDTO = new GetPageArticlesByCategoryIDResDTO();
        List<GetPageArticlesByCategoryIDDTO> getPageArticlesByCategoryIDDTOList = new ArrayList<>();

        Page<BusArticle> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<BusCategory> busCategoryLambdaQueryWrapper = new LambdaQueryWrapper<BusCategory>();
        busCategoryLambdaQueryWrapper.eq(BusCategory::getId,categoryID).ne(BusCategory::getCateStatus, DELETED);
        BusCategory busCategory = busCategoryMapper.selectOne(busCategoryLambdaQueryWrapper);

        LambdaQueryWrapper<BusArticle> busArticleQueryWrapper = new LambdaQueryWrapper<BusArticle>();
        busArticleQueryWrapper.eq(BusArticle::getCategoryId,categoryID).ne(BusArticle::getArticleStatus,DELETED);

        Page<BusArticle> busArticlePage = page(page,  busArticleQueryWrapper);
        List<BusArticle> busArticlePageRecords = busArticlePage.getRecords();
        busArticlePageRecords.stream().forEach(b -> {
            GetPageArticlesByCategoryIDDTO getPageArticlesByCategoryIDDTO = new GetPageArticlesByCategoryIDDTO();
            BeanUtils.copyProperties(b,getPageArticlesByCategoryIDDTO);
            getPageArticlesByCategoryIDDTO.setCategoryName(busCategory.getCategoryName());
            getPageArticlesByCategoryIDDTOList.add(getPageArticlesByCategoryIDDTO);
        });
        resDTO.setTotal(busArticlePage.getTotal());
        resDTO.setRecords(getPageArticlesByCategoryIDDTOList);
        resDTO.setPageNum(Convert.toInt(busArticlePage.getCurrent()));
        resDTO.setPageSize(Convert.toInt(busArticlePage.getSize()));

        return Result.success(resDTO);
    }

    @Override
    public Result<GetAllPageArticlesResDTO> getAllPageArticles(Integer pageNum, Integer pageSize) {
        GetAllPageArticlesResDTO getAllPageArticlesResDTO = new GetAllPageArticlesResDTO();

        Page<BusArticle> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<BusArticle> busArticleLambdaQueryWrapper = new LambdaQueryWrapper<BusArticle>();
        busArticleLambdaQueryWrapper.ne(BusArticle::getArticleStatus, DELETED);

        Page<BusArticle> busArticlePage = page (page, busArticleLambdaQueryWrapper);

        List<BusArticle> records = busArticlePage.getRecords();
        List<GetAllPageArticlesDTO> resDTOList = new ArrayList<>();
        records.stream().forEach(r -> {
            GetAllPageArticlesDTO  getAllPageArticlesDTO = new GetAllPageArticlesDTO();
            BeanUtils.copyProperties(r,getAllPageArticlesDTO);
            BusCategory busCategory = busCategoryMapper.selectById(r.getCategoryId());
            getAllPageArticlesDTO.setCategoryName(busCategory.getCategoryName());
            resDTOList.add(getAllPageArticlesDTO);
        });

        getAllPageArticlesResDTO.setRecords(resDTOList);
        getAllPageArticlesResDTO.setTotal(busArticlePage.getTotal());
        getAllPageArticlesResDTO.setPageNum(Convert.toInt(busArticlePage.getCurrent()));
        getAllPageArticlesResDTO.setPageSize(Convert.toInt(busArticlePage.getSize()));

        return Result.success(getAllPageArticlesResDTO);
    }
}
