package com.echo.dto;

import com.echo.modules.bus.model.BusAbout;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPageArchivesResDTO {

    private List<GetAllPageArchivesDTO> records;

    private Integer pageNum;

    private Integer pageSize;

    private Long total;

}
