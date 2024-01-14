package ru.praktikum.sprint7.client;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static ru.praktikum.sprint7.config.RestConfig.BASE_URI;

public abstract class RestClient {
    public RequestSpecification getdefaultRequestSpecification() {
        return given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }
}
