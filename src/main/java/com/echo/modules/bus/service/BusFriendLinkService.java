package com.echo.modules.bus.service;

import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.bus.model.BusCategory;
import com.echo.modules.bus.model.BusFriendLink;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
public interface BusFriendLinkService extends IService<BusFriendLink> {

    Result<PageInfo<BusFriendLink>> getAllPageFriLinkList(String friLinkName, Integer pageNum, Integer pageSize);

    Result addOrEditFriLink(@RequestBody BusFriendLink busFriendLink);

    Result delFriLinkByID(@PathVariable String friLinkID);

    Result delFriLinkBatchByIDS(@RequestBody List<String> friLinkIDList);


}
