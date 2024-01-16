package ru.praktikum.sprint7;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.dto.CourierCreateRequest;
import ru.praktikum.sprint7.step.CourierSteps;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public abstract class BaseTest {
    protected CourierSteps courierSteps;
    //private static List<Integer> ids = new ArrayList();
    protected Integer id;
    protected Integer statusCode;
    protected String login;
    protected String password;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
    }

    @After
    public void tearDown(){
        if(statusCode.equals(201)){
            id = courierSteps
                    .loginCourier(login, password)
                    .statusCode(SC_OK)
                    .extract()
                    .path("id");
        }
        //TODO: delete with ID
        System.out.println(id);

    }
}
