package ru.praktikum.sprint7;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;
import static ru.praktikum.sprint7.dataProvider.CourierGenerator.*;

public class CourierCreationsSuiteTest extends BaseTest {
    @Test
    @DisplayName("Успешное создание курьера с корректными данными")
    @Description("Данный тест покрывает следующие кейсы: 1) курьера можно создать; 3) чтобы создать курьера, нужно передать в ручку все обязательные поля; 4) запрос возвращает правильный код ответа (201 Created); 5) успешный запрос возвращает ok: true")
    public void createCourierSucessfully() {
        String login = RandomStringUtils.randomAlphabetic(10);
        String password = RandomStringUtils.randomAlphabetic(10);
        String firstName = RandomStringUtils.randomAlphabetic(10);
        courierSteps
                .createCourierRequest(login, password, firstName)
                .statusCode(SC_CREATED) //TODO: add method and change to CourierStepsResponse
                .body("ok", is(true));
        getCourierIdIfWasSucessfullyCreated(login,password);
    }

    @Test
    @DisplayName("Ошибка при создании двух одинаковых курьеров")
    @Description("Данный тест покрывает следующие кейсы: 2) нельзя создать двух одинаковых курьеров; 4) запрос возвращает правильный код ответа(409 Conflict); 7) если создать пользователя с логином, который уже есть, возвращается ошибка.")
    // описание теста
    public void createSecondSameCourierFailes() {
        courierSteps
                .createCourierRequest(CORRECT_DUPLICATE_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME);
        courierSteps
                .createCourierRequest(CORRECT_DUPLICATE_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_CONFLICT) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Этот логин уже используется")) // bug: вместо "Этот логин уже используется" приходит "Этот логин уже используется. Попробуйте другой."
        ;
    }

    @Test
    @DisplayName("Создание курьера без отправки поля 'login'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithoutLoginFieldFailes() {
        courierSteps
                .createCourierRequest(MISSING_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_BAD_REQUEST) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с отправкой пустого поля 'login'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithEmptyLoginFieldFailes() {
        courierSteps
                .createCourierRequest(EMPTY_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_BAD_REQUEST) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера без отправки поля 'password'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithoutPasswordFieldFailes() {
        courierSteps
                .createCourierRequest(CORRECT_THIRD_LOGIN, MISSING_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_BAD_REQUEST) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }

    @Test
    @DisplayName("Создание курьера с пустым полем 'password'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithEmptyPasswordFieldFailes() {
        courierSteps
                .createCourierRequest(CORRECT_FOURTH_LOGIN, EMPTY_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_BAD_REQUEST) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Недостаточно данных для создания учетной записи"));
        login = CORRECT_FOURTH_LOGIN;
        password = EMPTY_PASSWORD;
    }

    @Test
    @DisplayName("Создание курьера без отправки поля 'firstName'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(201 Created).")
    public void createCourierWithoutFirstNameFieldSucessfully() {
        courierSteps
                .createCourierRequest(CORRECT_SECOND_LOGIN, CORRECT_PASSWORD, MISSING_FIRSTNAME)
                .statusCode(SC_CREATED) //TODO: add method and change to CourierStepsResponse
                .body("ok", is(true));
    }

    @Test
    @DisplayName("Создание курьера с пустым полем 'firstName'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(201 Created).")
    public void createCourierWithEmptyFirstNameFieldSucessfully() {
        courierSteps
                .createCourierRequest(CORRECT_FIFTH_LOGIN, CORRECT_PASSWORD, EMPTY_FIRSTNAME)
                .statusCode(SC_CREATED) //TODO: add method and change to CourierStepsResponse
                .body("ok", is(true));
    }
}
