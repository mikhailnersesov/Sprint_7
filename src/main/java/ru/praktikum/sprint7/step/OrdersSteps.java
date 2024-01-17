package ru.praktikum.sprint7.step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.sprint7.client.OrdersClient;
import ru.praktikum.sprint7.dto.CourierCreateRequest;
import ru.praktikum.sprint7.dto.OrdersCreateRequest;

import java.util.List;

public class OrdersSteps {
    private final OrdersClient ordersClient;

    public OrdersSteps(OrdersClient ordersClient) {
        this.ordersClient = ordersClient;
    }
    @Step("Создание заказа")
    public ValidatableResponse createOrdersRequest(String firstName, String lastName, String address, String metroStation, String phone, int rentTime, String deliveryDate, String comment, List<String> color) {
        OrdersCreateRequest ordersCreateRequest = new OrdersCreateRequest();
        ordersCreateRequest.setFirstName(firstName);
        ordersCreateRequest.setLastName(lastName);
        ordersCreateRequest.setAddress(address);
        ordersCreateRequest.setMetroStation(metroStation);
        ordersCreateRequest.setPhone(phone);
        ordersCreateRequest.setRentTime(rentTime);
        ordersCreateRequest.setDeliveryDate(deliveryDate);
        ordersCreateRequest.setComment(comment);
        ordersCreateRequest.setColor(color);

        return ordersClient.sendPostRequestOrders(ordersCreateRequest).then();
    }
}
