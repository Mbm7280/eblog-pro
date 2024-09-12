package com.echo.config.security;

import com.echo.config.security.component.DynamicSecurityService;
import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.service.UmsResourceService;
import com.echo.modules.ums.service.UmsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


/****************************************************
 * 创建人：Echo
 * 创建时间: 2024/4/18 10:35
 * 项目名称: {pro-cli}
 * 文件名称: SecurityConfig
 * 文件描述: [ spring-security-config ]
 * version：1.0
 *
 ********************************************************/

@Configuration
public class SecurityConfig {

    @Autowired
    private UmsUserService umsUserService;

    @Autowired
    private UmsResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> umsUserService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return () -> {
            Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
            List<UmsResource> resourceList = resourceService.list();
            for (UmsResource resource : resourceList) {
                map.put(resource.getResUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getResName()));
            }
            return map;
        };
    }

}
