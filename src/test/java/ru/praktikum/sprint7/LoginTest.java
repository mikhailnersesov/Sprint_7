package ru.praktikum.sprint7;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.dto.CourierCreateRequest;
import ru.praktikum.sprint7.dto.CourierLoginRequest;

import static org.apache.http.HttpStatus.SC_OK;

public class LoginTest {
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    public void loginCourier() {
        String login = RandomStringUtils.randomAlphabetic(10);

        CourierCreateRequest courierCreateRequest = new CourierCreateRequest();
        courierCreateRequest.setLogin(login);
        courierCreateRequest.setPassword("1234");
        courierCreateRequest.setFirstName("Anatoliy");

        courierClient.create(courierCreateRequest);

        CourierLoginRequest courierLoginRequest = new CourierLoginRequest();
        courierLoginRequest.setLogin(courierCreateRequest.getLogin());
        courierLoginRequest.setPassword(courierCreateRequest.getPassword());

        Object result = courierClient.login(courierLoginRequest)
                .then()
                .statusCode(SC_OK)
                .extract()
                .path("id");
        Assert.assertNotNull(result);
    }
}
