package com.echo.modules.bus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.echo.config.api.Result;
import com.echo.dto.GetArticleCountGroupByCateResDTO;
import com.echo.dto.GetArticleStatisticsResDTO;
import com.echo.dto.GetHomepageInfoResDTO;
import com.echo.modules.bus.mapper.BusArticleMapper;
import com.echo.modules.bus.mapper.BusCategoryMapper;
import com.echo.modules.bus.mapper.BusCommentMapper;
import com.echo.modules.bus.model.BusArticle;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.model.BusComment;
import com.echo.modules.bus.service.BusCommentService;
import com.echo.modules.bus.service.BusHomepageService;
import com.echo.modules.ums.mapper.UmsUserMapper;
import com.echo.modules.ums.model.UmsUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.echo.common.constant.CommonConstant.EXIST;

@Service
public class BusHomepageServiceImpl implements BusHomepageService {

    @Autowired
    private BusArticleMapper busArticleMapper;

    @Autowired
    private BusCategoryMapper busCategoryMapper;

    @Autowired
    private UmsUserMapper umsUserMapper;

    @Autowired
    private BusCommentMapper busCommentMapper;

    /**
     * 文章量
     * 文章浏览量
     * 用户量
     * 分类量
     *
     * @return
     */
    @Override
    public Result<GetHomepageInfoResDTO> getHomepageInfo() {
        GetHomepageInfoResDTO getHomepageInfoResDTO = new GetHomepageInfoResDTO();

        List<GetArticleCountGroupByCateResDTO> articleCountResDTOList = busCategoryMapper.getArticleCountGroupByCate();

        Long articleCount = busArticleMapper.selectCount(new LambdaQueryWrapper<BusArticle>().eq(BusArticle::getArticleStatus, EXIST));
        Long userCount = umsUserMapper.selectCount(new LambdaQueryWrapper<UmsUser>().eq(UmsUser::getStatus, EXIST));
        Long categoryCount = busCategoryMapper.selectCount(new LambdaQueryWrapper<BusCategory>().eq(BusCategory::getCateStatus, EXIST));
        Long commentCount = busCommentMapper.selectCount(new LambdaQueryWrapper<BusComment>().eq(BusComment::getStatus, EXIST));
        List<GetArticleStatisticsResDTO> articleStatisticsList = busArticleMapper.getArticleStatistics();


        getHomepageInfoResDTO.setArticleCount(articleCount);
        getHomepageInfoResDTO.setUserCount(userCount);
        getHomepageInfoResDTO.setCategoryCount(categoryCount);
        getHomepageInfoResDTO.setCommentCount(commentCount);
        getHomepageInfoResDTO.setArticleStatisticsList(articleStatisticsList);
        getHomepageInfoResDTO.setArticleCountResDTOList(articleCountResDTOList);

        return Result.success(getHomepageInfoResDTO);
    }

}
