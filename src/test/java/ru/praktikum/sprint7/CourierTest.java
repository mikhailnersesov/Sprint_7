package ru.praktikum.sprint7;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.dto.CourierCreateRequest;

public class CourierTest {
    private CourierClient courierClient;

    @Before
    public void setUp() {
        courierClient = new CourierClient();
    }

    @Test
    public void createCourier() {
        String login = RandomStringUtils.randomAlphabetic(10);

        CourierCreateRequest request = new CourierCreateRequest();
        request.setLogin(login);
        request.setPassword("1234");
        request.setFirstName("Anatoliy");

        Boolean result = courierClient.create(request)
                .then()
                .statusCode(201)
                .extract()
                .path("ok");
        Assert.assertEquals(true, result);
    }
}
