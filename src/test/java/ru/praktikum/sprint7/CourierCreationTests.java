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
import static ru.praktikum.sprint7.dataProvider.CourierGenerator.*;

public class CourierCreationTests {
    protected static List<Integer> ids = new ArrayList();
    protected Integer id;
    protected static CourierSteps courierSteps;
    String login = RandomStringUtils.randomAlphabetic(10);
    String password = RandomStringUtils.randomAlphabetic(10);
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
    @DisplayName("Успешное создание курьера с корректными данными")
    @Description("Данный тест покрывает следующие кейсы: 1) курьера можно создать; 3) чтобы создать курьера, нужно передать в ручку все обязательные поля; 4) запрос возвращает правильный код ответа (201 Created); 5) успешный запрос возвращает ok: true")
    public void createCourierSucessfully() {
        courierSteps
                .createCourierRequest(login, password, firstName)
                .statusCode(SC_CREATED)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Ошибка при создании двух одинаковых курьеров")
    @Description("Данный тест покрывает следующие кейсы: 2) нельзя создать двух одинаковых курьеров; 4) запрос возвращает правильный код ответа(409 Conflict); 7) если создать пользователя с логином, который уже есть, возвращается ошибка.")
    @Issue("BUG-1")
    public void createSecondSameCourierFailes() {
        courierSteps
                .createCourierRequest(login, password, firstName);
        courierSteps
                .createCourierRequest(login, password, firstName)
                .statusCode(SC_CONFLICT)
                .body("message", is("Этот логин уже используется")); // bug: вместо "Этот логин уже используется" приходит "Этот логин уже используется. Попробуйте другой."
    }

    @Test
    @DisplayName("Создание курьера без отправки поля 'login'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithoutLoginFieldFailes() {
        courierSteps
                .createCourierRequest(MISSING_LOGIN, password, firstName)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с отправкой пустого поля 'login'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithEmptyLoginFieldFailes() {
        courierSteps
                .createCourierRequest(EMPTY_LOGIN, password, firstName)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без отправки поля 'password'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithoutPasswordFieldFailes() {
        courierSteps
                .createCourierRequest(login, MISSING_PASSWORD, firstName)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с пустым полем 'password'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithEmptyPasswordFieldFailes() {
        courierSteps
                .createCourierRequest(login, EMPTY_PASSWORD, firstName)
                .statusCode(SC_BAD_REQUEST)
                .body("message", is("Недостаточно данных для создания учетной записи"));
        login = CORRECT_FOURTH_LOGIN;
        password = EMPTY_PASSWORD;
    }

    @Test
    @DisplayName("Создание курьера без отправки поля 'firstName'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(201 Created).")
    public void createCourierWithoutFirstNameFieldSucessfully() {
        courierSteps
                .createCourierRequest(login, password, MISSING_FIRSTNAME)
                .statusCode(SC_CREATED)
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Создание курьера с пустым полем 'firstName'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(201 Created).")
    public void createCourierWithEmptyFirstNameFieldSucessfully() {
        courierSteps
                .createCourierRequest(login, password, EMPTY_FIRSTNAME)
                .statusCode(SC_CREATED)
                .body("ok", is(true));
    }

}
