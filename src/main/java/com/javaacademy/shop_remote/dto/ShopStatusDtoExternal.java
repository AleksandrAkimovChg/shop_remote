package com.javaacademy.shop_remote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Schema(description = "Тело ответ текущего статуса магазина")
public class ShopStatusDtoExternal {
    @Schema(description = "Наименование магазина", defaultValue = "Девяточка")
    private String name;

    @Schema(description = "Текущий статус магазина")
    private ShopStatus status;

    @JsonProperty("time_open")
    @Schema(description = "Время открытия", defaultValue = "09:00")
    private String timeOpen;

    @JsonProperty("time_close")
    @Schema(description = "Время закрытия", defaultValue = "18:00")
    private String timeClose;
}
