package ru.praktikum.sprint7.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import ru.praktikum.sprint7.dto.CourierCreateRequest;
import ru.praktikum.sprint7.dto.CourierDeleteRequest;
import ru.praktikum.sprint7.dto.CourierLoginRequest;

public class CourierClient extends RestClient {
    @Step("Send POST request to /api/v1/courier")
    public Response sendPostRequestCourier(CourierCreateRequest courierCreateRequest) {
        return getdefaultRequestSpecification()
                .body(courierCreateRequest)
                .when()
                .post("/courier");
    }

    @Step("Send POST request to /api/v1/courier/login")
    public Response sendPostRequestCourierLogin(CourierLoginRequest courierLoginRequest) {
        return getdefaultRequestSpecification()
                .body(courierLoginRequest)
                .when()
                .post("/courier/login");
    }

    @Step("Send DELETE request to /api/v1/courier/:id")
    public Response sendDeleteRequestCourierDeletion(CourierDeleteRequest courierDeleteRequest) {
        return getdefaultRequestSpecification()
                .log().all()
                .when()
                .delete("/courier/" + courierDeleteRequest.getId());
    }
}
