package com.javaacademy.shop_remote.mapper;

import com.javaacademy.shop_remote.dto.GoodPriceDtoExternal;
import com.javaacademy.shop_remote.dto.GoodPriceDtoInternal;
import com.javaacademy.shop_remote.dto.ShopStatusDtoExternal;
import com.javaacademy.shop_remote.dto.ShopStatusDtoInternal;
import org.springframework.stereotype.Service;

@Service
public class RemoteMapper {

    public ShopStatusDtoExternal convertToShopStatusDtoExternal(ShopStatusDtoInternal dto) {
        return new ShopStatusDtoExternal(dto.getName(), dto.getShopStatus(), dto.getTimeOpen(), dto.getTimeClose());
    }

    public GoodPriceDtoInternal convertToGoodPriceDtoInternal(GoodPriceDtoExternal dto) {
        return new GoodPriceDtoInternal(dto.getName(), dto.getPrice());
    }
}
