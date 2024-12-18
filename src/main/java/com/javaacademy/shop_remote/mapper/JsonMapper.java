package com.javaacademy.shop_remote.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javaacademy.shop_remote.dto.ShopStatusDtoInternal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JsonMapper {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public ShopStatusDtoInternal convertToShopStatusDtoInternal(String responseBody) throws JsonProcessingException {
        return objectMapper.readValue(responseBody, ShopStatusDtoInternal.class);
    }

    public String getJsonString(Object value) throws JsonProcessingException {
        return objectMapper.writeValueAsString(value);
    }
}
