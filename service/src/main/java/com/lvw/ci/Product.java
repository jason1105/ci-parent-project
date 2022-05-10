package com.lvw.ci;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Product {

    private static ExecutorService executor = Executors.newFixedThreadPool(8);
    private final OkHttpClient client = new OkHttpClient();
    private String getDetailUrl = "https://mapi.vip.com/vips-mobile/rest/shopping/pc/detail/main/v6";
    private final String productId;
    private Future<String> details;

    public Product(String productId) {
        this.productId = productId;
        run();
    }

    public String id() {
        return productId;
    }

    public String price()
            throws Exception {
        String json;
        try {
            json = details.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new Exception("Error to getting product of " + productId, e);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);
        // .data.products.6918812903707124056.priceView.salePrice
        String price = jsonNode.get("data").get("products")
                .get(productId)
                .get("priceView").get("salePrice").get("salePrice").asText();
        return price;
    }

    private void run() {

        details = executor.submit(() -> {
            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded; charset=UTF-8");

            RequestBody body = RequestBody.create(
                    "api_key=70f71280d5d547b2a7bb370a529aeea1&app_name=shop_pc&app_version=4.0&client=pc&mars_cid=1652062995400_cfdee1cd5a97f98e05c0939b373c4b9c&productId="
                            + productId + "&scene=detail&warehouse=VIP_NH",
                    mediaType);

            Request request = new Request.Builder()
                    .url(getDetailUrl)
                    .method("POST", body)
                    .addHeader("DNT", "1")
                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Accept", "application/json, text/javascript, */*; q=0.01")
                    .addHeader("User-Agent",
                            "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.54 Safari/537.36")
                    .addHeader("X-Requested-With", "XMLHttpRequest")
                    .addHeader("sec-ch-ua-platform", "\"Windows\"")
                    .addHeader("Sec-Fetch-Site", "same-site")
                    .addHeader("Sec-Fetch-Mode", "cors")
                    .addHeader("Sec-Fetch-Dest", "empty")
                    .build();
            try (Response response = client.newCall(request).execute()) {
                return response.body().string();
            }
        });
    }

}
