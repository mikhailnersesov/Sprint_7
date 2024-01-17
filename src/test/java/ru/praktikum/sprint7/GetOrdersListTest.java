package ru.praktikum.sprint7;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.sprint7.client.OrdersClient;
import ru.praktikum.sprint7.step.OrdersSteps;

import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.notNullValue;

public class GetOrdersListTest {
    protected static OrdersSteps ordersSteps;
    int courierId = Integer.parseInt(RandomStringUtils.randomNumeric(1));
    int limit = Integer.parseInt(RandomStringUtils.randomNumeric(1));
    String nearestStation = RandomStringUtils.randomAlphabetic(10);
    int page = Integer.parseInt(RandomStringUtils.randomNumeric(1));

    @Before
    public void setUp() {
        ordersSteps = new OrdersSteps(new OrdersClient());
    }

    @Test
    @DisplayName("Получение списка заказов")
    @Description("Данный тест покрывает следующий кейс: Проверь, что в тело ответа возвращается список заказов.")
    public void getOrdersListSucessfully() {
        ordersSteps
                .getOrdersRequest(courierId, limit, nearestStation, page)
                .statusCode(SC_OK)
                .body("orders", notNullValue());
    }
}
