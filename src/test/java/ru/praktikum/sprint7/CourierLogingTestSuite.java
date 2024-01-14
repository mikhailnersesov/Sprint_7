package ru.praktikum.sprint7;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.step.CourierSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLogingTestSuite {
    private CourierSteps courierSteps;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
    }

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
