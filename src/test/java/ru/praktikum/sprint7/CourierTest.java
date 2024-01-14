package ru.praktikum.sprint7;

import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static ru.praktikum.sprint7.config.RestConfig.BASE_URI;

public class CourierTest {
    @Test
    public void createCourier() {
        String login = RandomStringUtils.randomAlphabetic(10);
        Boolean result = given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"login\": \"" + login + "\",\n" +
                        "    \"password\": \"1234\",\n" +
                        "    \"firstName\": \"saske\"\n" +
                        "}")
                .when()
                .post("/courier")
                .then()
                .statusCode(201)
                .extract()
                .path("ok");
        Assert.assertEquals(true,result);
    }
}
