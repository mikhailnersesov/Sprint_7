package ru.praktikum.sprint7.client;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_BAD_REQUEST;
import static org.hamcrest.Matchers.is;
import static ru.praktikum.sprint7.config.RestConfig.BASE_URI;

public abstract class RestClient {
    protected RequestSpecification getdefaultRequestSpecification() {
        return given()
                .log().all()
                //TODO: delete before submission
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON);
    }
}
