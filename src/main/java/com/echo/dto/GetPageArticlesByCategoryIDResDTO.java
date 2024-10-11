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
public class GetPageArticlesByCategoryIDResDTO extends BusArticle {

    private List<GetPageArticlesByCategoryIDDTO> records;

    private Integer pageNum;

    private Integer pageSize;

    private Long total;

}
