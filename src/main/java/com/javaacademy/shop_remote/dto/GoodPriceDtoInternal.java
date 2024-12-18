package com.javaacademy.shop_remote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class GoodPriceDtoInternal {
    private String name;
    @JsonProperty("new_price")
    private BigDecimal price;
}
