package com.javaacademy.shop_remote.controller;

import com.javaacademy.shop_remote.dto.GoodPriceDtoExternal;
import com.javaacademy.shop_remote.dto.ShopStatusDtoExternal;
import com.javaacademy.shop_remote.service.GoodPriceService;
import com.javaacademy.shop_remote.service.StatusService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Tag(name = "Shop remote controller",
        description = "для удаленного управления магазинами. "
                + "Позволяет опрашивать статус магазинов. "
                + "Также позволяет менять стоимость товара в магазине.")
@RestController
@RequestMapping("/shop")
public class RemoteController {
    private final StatusService remoteService;
    private final GoodPriceService changeGoodService;

    @Operation(summary = "Получение статуса магазинов", description = "Опрашиваются все магазины,"
            + " которые указаны в настройках")
    @ApiResponse(
            responseCode = "200",
            description = "Успешно",
            content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            array = @ArraySchema(schema = @Schema(implementation = ShopStatusDtoExternal.class))),
                    @Content(mediaType = MediaType.TEXT_PLAIN_VALUE,
                            schema = @Schema(implementation = String.class))
    })
    @GetMapping("/status")
    public ResponseEntity<?> getShopsStatus() {
        log.info("получен запрос getShopStatus - LocalDateTime: {}", LocalDateTime.now());
        List<ShopStatusDtoExternal> response = remoteService.getAllShopsStatus();
        log.info("сформирован ответ getShopsStatus: {}", response);
        if (response.isEmpty()) {
            return ResponseEntity.ok("Ошибка: Нет ответа от магазинов, которые подключены к сервису.");
        }
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/good")
    @Operation(summary = "Изменение стоимости товара", description = "Передаются атрибуты названия "
            + "товара и новой стоимости товара")
    public void patchGoodPrice(@RequestBody GoodPriceDtoExternal request) {
        log.info("получен запрос patchGood - LocalDateTime: {}, request: {}", LocalDateTime.now(), request);
        changeGoodService.patchGoodInAllShops(request);
    }
}
