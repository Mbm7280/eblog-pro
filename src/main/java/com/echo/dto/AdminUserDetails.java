package com.echo.dto;

import com.echo.modules.ums.model.UmsResource;
import com.echo.modules.ums.model.UmsUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static com.echo.common.constant.CommonConstant.STR_ZERO;

public class AdminUserDetails implements UserDetails {

    private UmsUser umsUser;
    private List<UmsResource> resourceList;


    public AdminUserDetails(UmsUser umsUser, List<UmsResource> resourceList) {
        this.umsUser = umsUser;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getResName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return umsUser.getPassword();
    }

    @Override
    public String getUsername() {
        return umsUser.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsUser.getStatus().equals(STR_ZERO);
    }

}
