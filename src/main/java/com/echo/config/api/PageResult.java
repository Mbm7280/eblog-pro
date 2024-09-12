package com.echo.config.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2024/4/19 14:10
 * 项目名称: {pro-cli}
 * 文件名称: PageResult
 * 文件描述: [ PageResult ]
 * version：1.0
 *
 ********************************************************/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PageResult<T> {

    private List<T> records;

    private Object count;

    private Integer pageNum;

    private Integer pageSize;

    private Integer pages;
}
