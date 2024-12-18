package com.javaacademy.shop_remote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Schema(description = "Тело запроса на изменение стоимости товара")
public class GoodPriceDtoExternal {
    @Schema(description = "Наименование товара", defaultValue = "Хлеб")
    private String name;

    @JsonProperty("new_price")
    @Schema(description = "Новая стоимость товара", defaultValue = "65.37")
    private BigDecimal price;
}
