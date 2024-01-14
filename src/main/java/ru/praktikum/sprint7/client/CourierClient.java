package ru.praktikum.sprint7.client;

import io.restassured.response.Response;
import ru.praktikum.sprint7.dto.CourierCreateRequest;
import ru.praktikum.sprint7.dto.CourierLoginRequest;

public class CourierClient extends RestClient {
    public Response create(CourierCreateRequest courierCreateRequest) {
        return getdefaultRequestSpecification()
                .body(courierCreateRequest)
                .when()
                .post("/courier");
    }

    public Response login(CourierLoginRequest courierLoginRequest) {
        return getdefaultRequestSpecification()
                .body(courierLoginRequest)
                .when()
                .post("/courier/login");
    }
}
