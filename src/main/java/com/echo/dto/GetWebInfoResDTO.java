package com.echo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetWebInfoResDTO {

    private Integer articleCount;
    private Integer categoryCount;
    private Integer friendLinkCount;
    private String notice;

}
