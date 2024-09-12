package com.echo.modules.bus.model;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author Echo
 * @since 2024-09-12
 */
@Getter
@Setter
@TableName("bus_article")
@ApiModel(value = "BusArticle对象", description = "")
public class BusArticle implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键")
    private String id;

    @ApiModelProperty("作者ID")
    private String authorId;

    @ApiModelProperty("标题")
    private String articleTitle;

    @ApiModelProperty("文章分类ID")
    private Integer categoryId;

    @ApiModelProperty("文章缩略图")
    private String articleCover;

    @ApiModelProperty("文章摘要，如果该字段为空，默认取文章的前500个字符作为摘要")
    private String articleAbstract;

    @ApiModelProperty("内容")
    private String articleContent;

    @ApiModelProperty("文章类型 1原创 2转载 3翻译")
    private String articleType;

    @ApiModelProperty("状态值 1公开 2私密 3草稿")
    private String articleStatus;

    @ApiModelProperty("原文链接")
    private String originalUrl;

    @ApiModelProperty("创建人")
    private String createBy;

    @ApiModelProperty("发表时间")
    private Date createTime;

    @ApiModelProperty("修改人")
    private String updateBy;

    @ApiModelProperty("更新时间")
    private Date updateTime;


}
