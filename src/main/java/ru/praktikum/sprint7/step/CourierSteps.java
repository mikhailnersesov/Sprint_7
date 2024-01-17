package ru.praktikum.sprint7.step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.dto.CourierCreateRequest;
import ru.praktikum.sprint7.dto.CourierLoginRequest;

import static org.apache.http.HttpStatus.SC_OK;

public class CourierSteps {
    private final CourierClient courierClient;

    public CourierSteps(CourierClient courierClient) {
        this.courierClient = courierClient;
    }

    @Step("createCourier Method")
    public ValidatableResponse createCourierRequest(String login, String password, String firstName) {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(login);
        courierCreateRequest.setPassword(password);
        courierCreateRequest.setFirstName(firstName);
        return courierClient.sendPostRequestCourier(courierCreateRequest).then();
    }

    @Step("loginCourier Method")
    public ValidatableResponse loginCourierRequest(String login, String password) {
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(login);
        courierLoginRequest.setPassword(password);
        return courierClient.sendPostRequestCourierLogin(courierLoginRequest).then();
    }
}

