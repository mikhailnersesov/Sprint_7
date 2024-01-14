package ru.praktikum.sprint7;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.step.CourierSteps;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.is;

public class CourierCreationsTestSuite {

    private CourierSteps courierSteps;

    @Before
    public void setUp() { //TODO: abstract into BaseTest and extend TestClass
        courierSteps = new CourierSteps(new CourierClient());
    }

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
