package ru.praktikum.sprint7;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.step.CourierSteps;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;

public abstract class BaseTest {
    protected CourierSteps courierSteps;
    protected Integer id;
    protected String login;
    protected String password;

    protected static List<Integer> ids = new ArrayList();

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
    }

    public void getCourierIdIfWasSucessfullyCreated(String login, String password) {
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
}
