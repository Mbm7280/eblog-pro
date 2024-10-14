package com.echo.dto;

import com.echo.modules.bus.model.BusArticle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleInfoByArticleIDResDTO {

    private GetArticleInfoByArticleIDDTO currentArticle;

    private GetArticleInfoByArticleIDDTO prevArticle;

    private GetArticleInfoByArticleIDDTO nextArticle;

}
