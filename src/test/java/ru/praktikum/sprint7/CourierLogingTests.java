package ru.praktikum.sprint7;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.step.CourierSteps;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static ru.praktikum.sprint7.dataProvider.CourierGenerator.*;

public class CourierLogingTests {
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

    @After
    public void getCourierIdIfWasSucessfullyCreated() {
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
    @Issue("BUG-3")
    public static void tearDown() {
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) != null) {
                courierSteps
                        .deleteCourierRequest(ids.get(i))
                        .statusCode(SC_OK)  // bug: вместо 200, возвращается 404 "Курьера с таким id нет"
                        .body("ok", is(true));
                ;
            }
        }
    }

    @Test
    @DisplayName("Успешный логин курьера с корректными данными")
    @Description("Данный тест покрывает следующие кейсы: 1) курьер может авторизоваться; 2) для авторизации нужно передать все обязательные поля; 6) успешный запрос возвращает id.")
    public void testLoginCourierWithCorrectDataIsSuccessfull() {
        courierSteps
                .createCourierRequest(login, password, firstName);

        courierSteps
                .loginCourierRequest(login, password)
                .statusCode(SC_OK)
                .body("id", notNullValue());
    }

    @Test
    @DisplayName("Неудачный логин курьера с некорректным логином")
    @Description("Данный тест покрывает следующий кейс: 3) система вернёт ошибку, если неправильно указать логин или пароль; 5) если авторизоваться под несуществующим пользователем, запрос возвращает ошибку")
    public void testLoginCourierWithIncorrectLoginFailes() {
        courierSteps
                .createCourierRequest(login, password, firstName);
        courierSteps
                .loginCourierRequest(secondLogin, password)
                .statusCode(SC_NOT_FOUND)
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Неудачный логин курьера с некорректным паролем")
    @Description("Данный тест покрывает следующий кейс: 3) система вернёт ошибку, если неправильно указать логин или пароль;.")
    public void testLoginCourierWithIncorrectPasswordFailes() {
        courierSteps
                .createCourierRequest(login, password, firstName);
        courierSteps
                .loginCourierRequest(login, secondPassword)
                .statusCode(SC_NOT_FOUND)
                .body("message", is("Учетная запись не найдена"));
    }

    @Test
    @DisplayName("Неудачный логин курьера с отсуствующим логином")
    @Description("Данный тест покрывает следующий кейс: 4) если какого-то поля нет, запрос возвращает ошибку.")
    public void testLoginCourierWithInCorrectLoginFailes() {
        courierSteps
                .loginCourierRequest(MISSING_LOGIN, password)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Неудачный логин курьера с отсуствующим паролем")
    @Description("Данный тест покрывает следующий кейс: 4) если какого-то поля нет, запрос возвращает ошибку.")
    @Issue("BUG-2")
    public void testLoginCourierWithMissingPasswordFailes() {
        courierSteps
                .loginCourierRequest(login, MISSING_PASSWORD)
                .statusCode(SC_BAD_REQUEST) // bug: возвращается "504 Gateway time out"
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Неудачный логин курьера с пустым паролем")
    @Description("Данный тест покрывает следующий кейс: 4) если какого-то поля нет, запрос возвращает ошибку.")
    public void testLoginCourierWithEmptyPasswordFailes() {
        courierSteps
                .loginCourierRequest(login, EMPTY_PASSWORD)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для входа"));
    }

    @Test
    @DisplayName("Неудачный логин курьера с пустым логином")
    @Description("Данный тест покрывает следующий кейс: 4) если какого-то поля нет, запрос возвращает ошибку.")
    public void testLoginCourierWithEmptyLoginFailes() {
        courierSteps
                .loginCourierRequest(EMPTY_LOGIN, password)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для входа"));
    }
}
