package com.echo.modules.ums.controller;


import com.echo.config.annos.WebLogAnno;
import com.echo.modules.ums.model.UmsRole;
import com.echo.modules.ums.service.UmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Echo
 * @since 2024-04-16
 */
@RestController
@RequestMapping("/ums/umsRole")
public class UmsRoleController {

    @Autowired
    private UmsRoleService roleService;

    @WebLogAnno(description = "test")
    @GetMapping("/test")
    public List<UmsRole> test(){
        List<UmsRole> umsRoleList = roleService.list();
        return umsRoleList;
    }

}

