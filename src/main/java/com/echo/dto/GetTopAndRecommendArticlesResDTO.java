package com.echo.dto;


import com.echo.modules.bus.model.BusArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetTopAndRecommendArticlesResDTO {

    private TopAndRecommendArticlesDTO topArticle;

    private List<TopAndRecommendArticlesDTO> recommendArticles;

}
