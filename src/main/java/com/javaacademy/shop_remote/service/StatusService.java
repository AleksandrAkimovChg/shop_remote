package com.javaacademy.shop_remote.service;

import com.javaacademy.shop_remote.dto.ShopStatusDtoExternal;
import com.javaacademy.shop_remote.dto.ShopStatusDtoInternal;
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
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatusService {
    private final OkClient client;
    private final RemoteMapper remoteMapper;
    private final JsonMapper jsonMapper;

    @Value("${app.shops.urls}")
    private List<String> shopsUrl2;
    @Value("${app.shops.path.status}")
    private String path;

    public List<ShopStatusDtoExternal> getAllShopsStatus() {
        return shopsUrl2.stream()
                .map(e -> getShopStatus(e + path).orElse(null)).filter(Objects::nonNull)
                .map(remoteMapper::convertToShopStatusDtoExternal).toList();
    }

    private Optional<ShopStatusDtoInternal> getShopStatus(String shopUrl) {
        log.info("выполняю запрос getShopStatus - shopUrl: {}", shopUrl);
        ShopStatusDtoInternal dto = null;
        Request request = client.getGetRequest(shopUrl);
        try (Response response = client.sendRequest(request);) {
            String responseBody = client.getResponseBody(response);
            dto = jsonMapper.convertToShopStatusDtoInternal(responseBody);
            log.info("получен ответ от: {}, сформирован dto: {}", shopUrl, dto);
        } catch (IOException e) {
            log.info("Нет ответа от магазина: {}", shopUrl);
            log.error(e.getMessage(), e);
        }
        return Optional.ofNullable(dto);
    }
}
