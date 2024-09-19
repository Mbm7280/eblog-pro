package com.echo.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.echo.modules.bus.model.BusCategory;
import io.swagger.annotations.ApiModel;
import lombok.*;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class GetAllPageCategoryListDTO extends BusCategory {

    /**
     * 文章数量
     */
    private Integer articleCount;

}
