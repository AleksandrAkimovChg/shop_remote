package com.javaacademy.shop_remote.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ShopStatusDtoInternal {
    private String name;
    private ShopStatus shopStatus;
    @JsonProperty("time_open")
    private String timeOpen;
    @JsonProperty("time_close")
    private String timeClose;
}
