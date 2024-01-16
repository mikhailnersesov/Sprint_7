package ru.praktikum.sprint7;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.apache.http.HttpStatus.*;
import static org.hamcrest.Matchers.is;
import static ru.praktikum.sprint7.dataProvider.CourierGenerator.*;

public class CourierCreationsSuiteTest extends BaseTest {
    @Test
    @DisplayName("Успешное создание курьера с корректными данными")
    @Description("Данный тест покрывает следующие кейсы: 1) курьера можно создать; 3) чтобы создать курьера, нужно передать в ручку все обязательные поля; 4) запрос возвращает правильный код ответа (201 Created); 5) успешный запрос возвращает ok: true") // описание теста
    public void createCourierSucessfully() {
        statusCode = courierSteps
                .createCourier(CORRECT_FIRST_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_CREATED) //TODO: add method and change to CourierStepsResponse
                .body("ok", is(true))
                .and()
                .extract().statusCode();
        login = CORRECT_FIRST_LOGIN;
        password = CORRECT_PASSWORD;
    }
    @Test
    @DisplayName("Ошибка при создании двух одинаковых курьеров")
    @Description("Данный тест покрывает следующие кейсы: 2) нельзя создать двух одинаковых курьеров; 4) запрос возвращает правильный код ответа(409 Conflict); 7) если создать пользователя с логином, который уже есть, возвращается ошибка.") // описание теста
    public void createSecondSameCourierFailes() {
        courierSteps
                .createCourier(CORRECT_DUPLICATE_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME);
        courierSteps
                .createCourier(CORRECT_DUPLICATE_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_CONFLICT) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Этот логин уже используется")) // bug: вместо "Этот логин уже используется" приходит "Этот логин уже используется. Попробуйте другой."
;
    }
    @Test
    @DisplayName("Создание курьера без отправки поля 'login'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithoutLoginFieldFailes() {
        courierSteps
                .createCourier(MISSING_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_BAD_REQUEST) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Создание курьера с отправкой пустого поля 'login'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithEmptyLoginFieldFailes() {
        courierSteps
                .createCourier(EMPTY_LOGIN, CORRECT_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_BAD_REQUEST) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Создание курьера без отправки поля 'password'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithoutPasswordFieldFailes() {
        courierSteps
                .createCourier(CORRECT_THIRD_LOGIN, MISSING_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_BAD_REQUEST) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Создание курьера с пустым полем 'password'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(400 Bad Request).")
    public void createCourierWithEmptyPasswordFieldFailes() {
        courierSteps
                .createCourier(CORRECT_FOURTH_LOGIN, EMPTY_PASSWORD, CORRECT_FIRSTNAME)
                .statusCode(SC_BAD_REQUEST) //TODO: add method and change to CourierStepsResponse
                .body("message", is("Недостаточно данных для создания учетной записи"));
    }
    @Test
    @DisplayName("Создание курьера без отправки поля 'firstName'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(201 Created).")
    public void createCourierWithoutFirstNameFieldSucessfully() {
        courierSteps
                .createCourier(CORRECT_SECOND_LOGIN, CORRECT_PASSWORD, MISSING_FIRSTNAME)
                .statusCode(SC_CREATED) //TODO: add method and change to CourierStepsResponse
                .body("ok", is(true));
    }
    @Test
    @DisplayName("Создание курьера с пустым полем 'firstName'")
    @Description("Данный тест покрывает следующие кейс: 4) запрос возвращает правильный код ответа(201 Created).")
    public void createCourierWithEmptyFirstNameFieldSucessfully() {
        courierSteps
                .createCourier(CORRECT_FIFTH_LOGIN, CORRECT_PASSWORD, EMPTY_FIRSTNAME)
                .statusCode(SC_CREATED) //TODO: add method and change to CourierStepsResponse
                .body("ok", is(true));
    }
}
