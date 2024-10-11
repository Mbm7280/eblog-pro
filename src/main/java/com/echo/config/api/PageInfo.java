package com.echo.config.api;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;

import java.util.List;

/****************************************************
 * 创建人：Echo
 * 创建时间: 2024/4/19 14:10
 * 项目名称: {pro-cli}
 * 文件名称: PageInfo
 * 文件描述: [ PageInfo ]
 * version：1.0
 *
 ********************************************************/
@Data
public class PageInfo<T> {
    private Integer pageNum;
    private Integer pageSize;
    private Integer totalPage;
    private Long total;
    private List<T> list;
    private String categoryName;

    /**
     * 将MyBatis Plus 分页结果转化为通用结果
     */
    public static <T> PageInfo<T> restPage(Page<T> pageResult,String categoryName) {
        PageInfo<T> result = new PageInfo<>();
        result.setPageNum(Convert.toInt(pageResult.getCurrent()));
        result.setPageSize(Convert.toInt(pageResult.getSize()));
        result.setTotal(pageResult.getTotal());
        result.setTotalPage(Convert.toInt(pageResult.getTotal()/pageResult.getSize()+1));
        result.setList(pageResult.getRecords());
        result.setCategoryName(categoryName);
        return result;
    }
}
