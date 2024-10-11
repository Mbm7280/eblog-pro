package com.echo.modules.bus.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.common.utils.GenegateIDUtil;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusComment;
import com.echo.modules.bus.mapper.BusCommentMapper;
import com.echo.modules.bus.service.BusCommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.echo.common.constant.CommonConstant.DELETED;
import static com.echo.common.constant.CommonConstant.EXIST;
import static com.echo.config.api.ResultCode.THE_COMMENT_QUERY_FAILED;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Service
public class BusCommentServiceImpl extends ServiceImpl<BusCommentMapper, BusComment> implements BusCommentService {

    @Autowired
    private BusCommentMapper busCommentMapper;

    @Override
    public Result<PageInfo<BusComment>> getAllPageCommentList(String commentContent, Integer pageNum, Integer pageSize) {
        Page<BusComment> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<BusComment> busCommentLambdaQueryWrapper = new LambdaQueryWrapper<BusComment>();
        busCommentLambdaQueryWrapper.ne(BusComment::getStatus, DELETED);

        if (StringUtils.isNotEmpty(commentContent)) {
            busCommentLambdaQueryWrapper.like(BusComment::getCommentContent, commentContent);
        }

        Page<BusComment> busCommentPage = page(page, busCommentLambdaQueryWrapper);

        PageInfo<BusComment> busCommentPageInfo = PageInfo.restPage(busCommentPage,null);

        return Result.success(busCommentPageInfo);
    }

    @Override
    public Result addOrEditComment(BusComment busComment) {
        if (StrUtil.isNotBlank(busComment.getId())) {
            busComment.setUpdateTime(new Date());
            updateById(busComment);
        } else {
            String commentID = GenegateIDUtil.generateCommentID();
            busComment.setId(commentID);
            busComment.setCreateTime(new Date());
            busComment.setUpdateTime(new Date());
            busComment.setStatus(EXIST);
            save(busComment);
        }
        return Result.success();
    }

    @Override
    public Result delComment(String commentID) {
        BusComment busComment = getOne(new LambdaQueryWrapper<BusComment>().eq(BusComment::getId, commentID));
        if (ObjUtil.isEmpty(busComment)) {
            return Result.failed(THE_COMMENT_QUERY_FAILED);
        }
        busComment.setUpdateTime(new Date());
        busComment.setStatus(DELETED);
        updateById(busComment);
        return Result.success();
    }

    @Override
    public Result delCommentBatch(List<String> commentIDList) {
        List<BusComment> busCommentList = busCommentMapper.selectBatchIds(commentIDList);
        if (CollUtil.isNotEmpty(busCommentList)) {
            busCommentList.forEach(busComment -> {
                busComment.setUpdateTime(new Date());
                busComment.setStatus(DELETED);
                updateById(busComment);
            });
        }
        return Result.success();
    }
}
