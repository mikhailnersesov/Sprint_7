package ru.praktikum.sprint7;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.praktikum.sprint7.client.OrdersClient;
import ru.praktikum.sprint7.step.OrdersSteps;

import java.util.List;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(Parameterized.class)
public class OrderCreationTest {
    protected static OrdersSteps ordersSteps;
    @Parameterized.Parameter(0)
    public List<String> color;
    String firstName = RandomStringUtils.randomAlphabetic(10);
    String lastName = RandomStringUtils.randomAlphabetic(10);
    String address = RandomStringUtils.randomAlphabetic(10);
    String metroStation = RandomStringUtils.randomNumeric(10);
    String phone = RandomStringUtils.randomAlphabetic(10);
    int rentTime = Integer.parseInt(RandomStringUtils.randomNumeric(1));
    String deliveryDate = "2022-02-24";
    String comment = RandomStringUtils.randomAlphabetic(10);

    @Parameterized.Parameters
    public static Object[] data() {
        return new Object[]{
                List.of("BLACK"),
                List.of("GREY"),
                List.of("BLACK", "GREY"),
                List.of()
        };
    }

    @Before
    public void setUp() {
        ordersSteps = new OrdersSteps(new OrdersClient());
    }

    @Test
    @DisplayName("Успешное создание заказа")
    @Description("Данный тест покрывает следующие кейсы: 1) можно указать один из цветов — BLACK или GREY; 2) можно указать оба цвета; 3) можно совсем не указывать цвет; 4) тело ответа содержит track.")
    public void createOrderSucessfully() {
        ordersSteps
                .createOrdersRequest(firstName, lastName, address, metroStation, phone, rentTime, deliveryDate, comment, color)
                .statusCode(SC_CREATED)
                .body("track", notNullValue());
    }

}
