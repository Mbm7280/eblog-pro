package com.echo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategoriesAndCountResDTO {

    private String categoryID;

    private String categoryName;

    private Long articleCount;

}
