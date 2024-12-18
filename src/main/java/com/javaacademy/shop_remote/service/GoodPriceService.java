package com.javaacademy.shop_remote.service;

import com.javaacademy.shop_remote.dto.GoodPriceDtoExternal;
import com.javaacademy.shop_remote.dto.GoodPriceDtoInternal;
import com.javaacademy.shop_remote.http_client.OkClient;
import com.javaacademy.shop_remote.mapper.JsonMapper;
import com.javaacademy.shop_remote.mapper.RemoteMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoodPriceService {
    private final OkClient client;
    private final RemoteMapper remoteMapper;
    private final JsonMapper jsonMapper;

    @Value("${app.shops.urls}")
    private List<String> shopsUrl2;
    @Value("${app.shops.path.good}")
    private String path;

    public void patchGoodInAllShops(GoodPriceDtoExternal dto) {
        GoodPriceDtoInternal convertedDto = remoteMapper.convertToGoodPriceDtoInternal(dto);
        shopsUrl2.forEach(e -> patchGoodRequest(e + path, convertedDto));
    }

    private void patchGoodRequest(String shopUrl, GoodPriceDtoInternal dto) {
        log.info("запрос patchGoodRequest - shopUrl: {}", shopUrl);
        try {
            String json = jsonMapper.getJsonString(dto);
            Request request = client.getPatchRequestWithJson(shopUrl, json);
            Response response = client.sendRequest(request);
            log.info("Получен ответ от магазина: {}, isSuccessful: {}", shopUrl,  response.isSuccessful());
        } catch (IOException e) {
            log.info("Нет ответа от магазина: {}", shopUrl);
            log.error(e.getMessage(), e);
        }
    }
}
