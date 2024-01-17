<h1 align="center">Welcome to final project in yandex automation 7th sprint 👋</h1>

> Author: Mikhail Nersesov
>
> GitHub link: https://github.com/mikhailnersesov/Sprint_7

## ✨ Technologies in the project

Тебе предстоит протестировать API учебного сервиса Яндекс.Самокат. Его документация:
qa-scooter.praktikum-services.ru/docs/.
Перед тем как писать тесты, протестируй API вручную в Postman. Это поможет разобраться, как работают запросы.
Чтобы вспомнить материал спринта, загляни в шпаргалки.
> Подготовка
> Перед тем как приступить к заданиям:

- Создай Maven-проект.
- Назови проект Sprint_7.
- Подключи JUnit 4, RestAssured, Allure.

> Протестируй ручки
> Проверь, что они корректно работают и выдают нужные ошибки.
> Создание курьера
> Проверь:
> курьера можно создать;
> нельзя создать двух одинаковых курьеров;
> чтобы создать курьера, нужно передать в ручку все обязательные поля;
> запрос возвращает правильный код ответа;
> успешный запрос возвращает ok: true;
> если одного из полей нет, запрос возвращает ошибку;
> если создать пользователя с логином, который уже есть, возвращается ошибка.
> Логин курьера
> Проверь:
> курьер может авторизоваться;
> для авторизации нужно передать все обязательные поля;
> система вернёт ошибку, если неправильно указать логин или пароль;
> если какого-то поля нет, запрос возвращает ошибку;
> если авторизоваться под несуществующим пользователем, запрос возвращает ошибку;
> успешный запрос возвращает id.
> Создание заказа
> Проверь, что когда создаёшь заказ:
> можно указать один из цветов — BLACK или GREY;
> можно указать оба цвета;
> можно совсем не указывать цвет;
> тело ответа содержит track.
> Чтобы протестировать создание заказа, нужно использовать параметризацию.
> Список заказов
> Проверь, что в тело ответа возвращается список заказов.
> Отчёт Allure
> Сгенерируй его и запушь в репозиторий.
> Обрати внимание: всю папку target коммитить не нужно. Чтобы добавить в коммит только отчёт, можно перейти в папку
> проекта в консоли и выполнить команды:

# добавляем папку с отчётом Allure к файлам. Ключ -f пригодится, если папка target указана в .gitignore

git add -f .\target\allure-results\.

# выполняем коммит

git commit -m "add allure report"

# так отправишь файлы в удалённый репозиторий

git push
Не забудь: тесты должны быть независимыми. Все данные нужно удалять после того, как тест выполнится. Если для проверки
нужен пользователь, создай его перед тестом и удали после.

## ✨ Technologies in the project

> SDK: Amazon Corretto 11.0.20
>
> JUnit: 4.13.1
>
> Maven: 3.9.2
>
> Rest-assured: 4.4.0
>
> Allure: 2.25.0
>
> Gson: 2.8.9
>
> Maven-surefire plugin: 2.22.2

## 🚀 Usage

To run the tests:

```sh
mvn clean test
```

To get an Allure report:

```sh
mvn allure:serve 
```

1) login tests which are missing
2) delete method
3) cleanUP
4) copy the orderCheck

Structure:
RestClient(package Client): contains given which is necessary in any request
CourierClient: contains different requests (hat RestClient method inside) till the endPoint call
CourierSteps: puts together: input data for call from dto classes and requests from CourierClient

Package client: classes with requests parts (clients)
Package config: RestConfig with general configs (BaseURL)
Package dataProvider: CourierGenerator with constant courier inputData
Package dto: constructors for login and create parameters
Package step: Steps to build a Test




