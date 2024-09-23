package com.echo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleCountGroupByCateResDTO {

    private String categoryId;

    private String categoryName;

    private Long articleCount;

}
