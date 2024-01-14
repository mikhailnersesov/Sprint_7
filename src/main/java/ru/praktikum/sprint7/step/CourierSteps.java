package ru.praktikum.sprint7.step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.dto.CourierCreateRequest;
import ru.praktikum.sprint7.dto.CourierLoginRequest;

public class CourierSteps {
    private final CourierClient courierClient;

    public CourierSteps(CourierClient courierClient) {
        this.courierClient = courierClient;
    }

    @Step("Send GET request to /api/users/me")
    public ValidatableResponse createCourier(String login, String password, String firstName) {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(login);
        courierCreateRequest.setPassword(password);
        courierCreateRequest.setFirstName(firstName);
        return courierClient.create(courierCreateRequest).then();
    }
    public ValidatableResponse loginCourier(String login, String password) {
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(login);
        courierLoginRequest.setPassword(password);
        return courierClient.login(courierLoginRequest).then();
    }
}
