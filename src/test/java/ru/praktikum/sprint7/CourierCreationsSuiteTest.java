package ru.praktikum.sprint7;

import org.junit.Test;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.is;
import static ru.praktikum.sprint7.dataProvider.CourierGenerator.*;

public class CourierCreationsSuiteTest extends BaseTest {

    @Test
    public void createCourier() {
        courierSteps
                .createCourier(CORRECT_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_CREATED) //TODO: add method and change to CourierStepsResponse
                .body("ok", is(true));
    }
}
