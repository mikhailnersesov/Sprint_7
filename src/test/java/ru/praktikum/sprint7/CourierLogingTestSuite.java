package ru.praktikum.sprint7;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLogingTestSuite extends BaseTest {
    @Test
    public void testLoginCourierWithCorrectDataIsSuccessfull() {
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);

        courierSteps
                .createCourier(login, password, firstName);

        courierSteps
                .loginCourier(login, password) //TODO: rename to loginCourierRequest
                .statusCode(SC_OK) //TODO:  create Step with loginCourierResponse
                .body("id", notNullValue());
    }
}
