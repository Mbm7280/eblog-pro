package com.echo.dto;

import lombok.*;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetHomepageInfoResDTO {

    private Long articleCount;

    private Long userCount;

    private Long categoryCount;

    private Long commentCount;

    private List<GetArticleCountGroupByCateResDTO> articleCountResDTOList;

    private List<GetArticleStatisticsResDTO> articleStatisticsList;

    // 文章浏览量的具体操作需要  前端页面好了之后，在获取具体文章的方法中进行操作
    // 使用 redis 进行缓存，而后配合定时器，将数据同步到数据库
    private List<GetArticleViewListResDTO> articleViewList;

}
