package com.echo.modules.ums.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.echo.common.utils.GenegateIDUtil;
import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.ums.model.UmsRole;
import com.echo.modules.ums.mapper.UmsRoleMapper;
import com.echo.modules.ums.service.UmsRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static com.echo.common.constant.CommonConstant.DELETED;
import static com.echo.common.constant.CommonConstant.EXIST;
import static com.echo.config.api.ResultCode.THE_ROLE_QUERY_FAILED;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Service
public class UmsRoleServiceImpl extends ServiceImpl<UmsRoleMapper, UmsRole> implements UmsRoleService {

    @Autowired
    private UmsRoleMapper umsRoleMapper;

    @Override
    public Result<PageInfo<UmsRole>> getAllPageRoleList(String roleName, Integer pageNum, Integer pageSize) {
        Page<UmsRole> page = new Page<>(pageNum, pageSize);

        LambdaQueryWrapper<UmsRole> umsRoleLambdaQueryWrapper = new LambdaQueryWrapper<UmsRole>();
        umsRoleLambdaQueryWrapper.ne(UmsRole::getStatus, DELETED);

        if (StringUtils.isNotEmpty(roleName)) {
            umsRoleLambdaQueryWrapper.like(UmsRole::getRoleName, roleName);
        }

        Page<UmsRole> umsRolePage = page(page, umsRoleLambdaQueryWrapper);

        PageInfo<UmsRole> umsRolePageInfo = PageInfo.restPage(umsRolePage);

        return Result.success(umsRolePageInfo);
    }

    @Override
    public Result addOrEditRole(UmsRole umsRole) {
        if (StrUtil.isNotBlank(umsRole.getId())) {
            umsRole.setUpdateTime(new Date());
            updateById(umsRole);
        } else {
            String roleID = GenegateIDUtil.generateRoleID();
            umsRole.setId(roleID);
            umsRole.setCreateTime(new Date());
            umsRole.setUpdateTime(new Date());
            umsRole.setStatus(EXIST);
            save(umsRole);
        }
        return Result.success();
    }

    @Override
    public Result delRole(String roleID) {
        UmsRole umsRole = getOne(new LambdaQueryWrapper<UmsRole>().eq(UmsRole::getId, roleID));
        if (ObjUtil.isEmpty(umsRole)) {
            return Result.failed(THE_ROLE_QUERY_FAILED);
        }
        umsRole.setUpdateTime(new Date());
        umsRole.setStatus(DELETED);
        updateById(umsRole);
        return Result.success();
    }

    @Override
    public Result delRoleBatch(List<String> roleIDList) {
        List<UmsRole> umsRoleList = umsRoleMapper.selectBatchIds(roleIDList);
        if (CollUtil.isNotEmpty(umsRoleList)) {
            umsRoleList.forEach(umsRole -> {
                umsRole.setUpdateTime(new Date());
                umsRole.setStatus(DELETED);
                updateById(umsRole);
            });
        }
        return Result.success();
    }
}
