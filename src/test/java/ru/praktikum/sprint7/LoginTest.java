package ru.praktikum.sprint7;

import io.restassured.http.ContentType;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static ru.praktikum.sprint7.config.RestConfig.BASE_URI;

public class LoginTest {
    @Test
    public void loginCourier() {
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
                .statusCode(SC_CREATED)
                .extract()
                .path("ok");

        given()
                .baseUri(BASE_URI)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"login\": \"" + login + "\",\n" +
                        "    \"password\": \"1234\"\n" +
                        "}")
                .when()
                .post("/courier/login")
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("id");
        Assert.assertNotNull(result);
    }
}
