package ru.praktikum.sprint7.step;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.dto.CourierCreateRequest;
import ru.praktikum.sprint7.dto.CourierDeleteRequest;
import ru.praktikum.sprint7.dto.CourierLoginRequest;

public class CourierSteps {
    private final CourierClient courierClient;

    public CourierSteps(CourierClient courierClient) {
        this.courierClient = courierClient;
    }

    @Step("Создание курьера")
    public ValidatableResponse createCourierRequest(String login, String password, String firstName) {
        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(login);
        courierCreateRequest.setPassword(password);
        courierCreateRequest.setFirstName(firstName);
        return courierClient.sendPostRequestCourier(courierCreateRequest).then();
    }

    @Step("Логин курьера в системе")
    public ValidatableResponse loginCourierRequest(String login, String password) {
        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(login);
        courierLoginRequest.setPassword(password);
        return courierClient.sendPostRequestCourierLogin(courierLoginRequest).then();
    }

    @Step("Удаление курьера")
    public ValidatableResponse deleteCourierRequest(Integer id) {
        CourierDeleteRequest courierDeleteRequest = new CourierDeleteRequest();
        courierDeleteRequest.setId(id);
        return courierClient.sendDeleteRequestCourierDeletion(courierDeleteRequest).then();
    }
}

