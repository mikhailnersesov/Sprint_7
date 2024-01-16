package ru.praktikum.sprint7.client;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import org.junit.runner.Request;
import ru.praktikum.sprint7.dto.CourierCreateRequest;
import ru.praktikum.sprint7.dto.CourierLoginRequest;

import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.is;

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
}
