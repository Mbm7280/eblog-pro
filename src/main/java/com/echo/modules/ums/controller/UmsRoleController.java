package com.echo.modules.ums.controller;


import com.echo.config.api.PageInfo;
import com.echo.config.api.Result;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsRole;
import com.echo.modules.ums.service.UmsResourceService;
import com.echo.modules.ums.service.UmsRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@RestController
@RequestMapping("/admin/umsRole")
public class UmsRoleController {

    @Autowired
    private UmsRoleService umsRoleService;

    @ApiOperation(value = "分页获取所有角色信息")
    @GetMapping(value = "/getAllPageRoleList")
    public Result<PageInfo<UmsRole>> getAllPageRoleList(@RequestParam(required = false) String roleName,
                                                                @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize) {
        return umsRoleService.getAllPageRoleList(roleName,pageNum,pageSize);
    }

    @ApiOperation(value = "新增或修改角色")
    @PostMapping(value = "/addOrEditRole")
    public Result addOrEditRole(@RequestBody UmsRole umsRole) {
        return umsRoleService.addOrEditRole(umsRole);
    }

    @ApiOperation(value = "删除角色")
    @DeleteMapping(value = "/delRole/{roleID}")
    public Result delRole(@PathVariable String roleID) {
        return umsRoleService.delRole(roleID);
    }

    @ApiOperation(value = "批量删除角色")
    @DeleteMapping(value = "/delRoleBatch")
    public Result delRoleBatch(@RequestBody List<String> roleIDList) {
        return umsRoleService.delRoleBatch(roleIDList);
    }

}

