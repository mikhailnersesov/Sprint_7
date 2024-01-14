package ru.praktikum.sprint7;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.is;

public class CourierCreationsTestSuite extends BaseTest {

    @Test
    public void createCourier() {
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);

        courierSteps
                .createCourier(login, password, firstName)
                .statusCode(SC_CREATED) //TODO: add method and change to CourierStepsResponse
                .body("ok", is(true));
    }
}
