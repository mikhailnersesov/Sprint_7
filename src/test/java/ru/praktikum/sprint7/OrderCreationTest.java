package ru.praktikum.sprint7;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.step.CourierSteps;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.is;

public class OrderCreationTest {
    protected static CourierSteps courierSteps;
    protected static List<Integer> ids = new ArrayList();
    protected Integer id;
    String login = RandomStringUtils.randomAlphabetic(10);
    String secondLogin = RandomStringUtils.randomAlphabetic(10);
    String password = RandomStringUtils.randomAlphabetic(10);
    String secondPassword = RandomStringUtils.randomAlphabetic(10);
    String firstName = RandomStringUtils.randomAlphabetic(10);

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
    }
    @Test
    @DisplayName("Успешное создание курьера с корректными данными")
    @Description("Данный тест покрывает следующие кейсы: 1) курьера можно создать; 3) чтобы создать курьера, нужно передать в ручку все обязательные поля; 4) запрос возвращает правильный код ответа (201 Created); 5) успешный запрос возвращает ok: true")
    public void createCourierSucessfully() {
        courierSteps
                .createCourierRequest(login, password, firstName)
                .statusCode(SC_CREATED)
                .body("ok", is(true));
    }
}
