package com.echo.modules.bus.service;

import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusComment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
public interface BusCommentService extends IService<BusComment> {

    Result<PageInfo<BusComment>> getAllPageCommentList(String commentContent, Integer pageNum, Integer pageSize);

    Result addOrEditComment(BusComment busComment);

    Result delComment(String commentID);

    Result delCommentBatch(List<String> commentIDList);

}
