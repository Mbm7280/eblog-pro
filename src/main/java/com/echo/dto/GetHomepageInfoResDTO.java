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

}
