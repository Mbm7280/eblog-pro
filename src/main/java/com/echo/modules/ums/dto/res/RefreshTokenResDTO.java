package com.echo.modules.ums.dto.res;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RefreshTokenResDTO implements Serializable {

    private String token;

    private String tokenHead;

}
