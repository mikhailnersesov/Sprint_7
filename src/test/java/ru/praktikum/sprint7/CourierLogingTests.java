package ru.praktikum.sprint7;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.step.CourierSteps;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class CourierLogingTests {
    protected CourierSteps courierSteps;
    protected static List<Integer> ids = new ArrayList();
    protected Integer id;
    String login = RandomStringUtils.randomAlphabetic(10);
    String password = RandomStringUtils.randomAlphabetic(10);
    String firstName = RandomStringUtils.randomAlphabetic(10);
    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
    }
    @After
    public void getCourierIdIfWasSucessfullyCreated2() {
        try {
            id = courierSteps
                    .loginCourierRequest(login, password)
                    .statusCode(SC_OK)
                    .extract()
                    .path("id");
        } catch (AssertionError assertionError) {
            System.out.println("no courier was created - nothing to save");
        }
        ids.add(id);
    }

    @AfterClass
    public static void tearDown() {
        for (int i = 0; i < ids.size(); i++) {
            System.out.println("pass");
            // delete
        }

    }
    @Test
    public void testLoginCourierWithCorrectDataIsSuccessfull() {

        courierSteps
                .createCourierRequest(login, password, firstName);

        courierSteps
                .loginCourierRequest(login, password)
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }
}
