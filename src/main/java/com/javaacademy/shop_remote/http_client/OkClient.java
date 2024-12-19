package com.javaacademy.shop_remote.http_client;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OkClient {
    private final OkHttpClient client = new OkHttpClient();

    public Request getGetRequest(String shopUrl) {
        return new Request.Builder().get().url(shopUrl).build();
    }

    public Request getPatchRequestWithJson(String shopUrl, String json) {
        RequestBody body = RequestBody.create(json, okhttp3.MediaType.parse(
                MediaType.APPLICATION_JSON_VALUE));
        return new Request.Builder().patch(body).url(shopUrl).build();
    }

    public Response sendRequest(Request request) throws IOException {
        return client.newCall(request).execute();
    }

    public String getResponseBody(Response response) throws IOException {
        if (!response.isSuccessful() || response.body() == null) {
            throw new RuntimeException("Response неуспешен или пустой");
        }
        return response.body().string();
    }
}
