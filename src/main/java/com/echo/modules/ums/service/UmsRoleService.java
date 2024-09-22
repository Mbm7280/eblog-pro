package com.echo.modules.ums.service;

import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsRole;
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
public interface UmsRoleService extends IService<UmsRole> {

    Result<PageInfo<UmsRole>> getAllPageRoleList(String roleName, Integer pageNum, Integer pageSize);


    Result addOrEditRole(UmsRole umsRole);


    Result delRole(String roleID);


    Result delRoleBatch(List<String> roleIDList);

}
