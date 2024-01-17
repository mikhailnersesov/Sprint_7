package ru.praktikum.sprint7.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.sprint7.dto.OrdersCreateRequest;
import ru.praktikum.sprint7.dto.OrdersGetRequest;

public class OrdersClient extends RestClient {
    @Step("Send POST request to /api/v1/orders")
    public Response sendPostRequestOrders(OrdersCreateRequest ordersCreateRequest) {
        return getdefaultRequestSpecification()
                .body(ordersCreateRequest)
                .when()
                .post("/orders");
    }

    @Step("Send GET request to /api/v1/orders")
    public Response sendGetRequestOrders(OrdersGetRequest ordersGetRequest) {
        return getdefaultRequestSpecification()
                .body(ordersGetRequest)
                .when()
                .get("/orders");
    }
}
