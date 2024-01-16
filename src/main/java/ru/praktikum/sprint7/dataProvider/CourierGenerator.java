package ru.praktikum.sprint7.dataProvider;

import org.apache.commons.lang3.RandomStringUtils;

public class CourierGenerator {
    public static final String CORRECT_FIRST_LOGIN = RandomStringUtils.randomAlphabetic(10);
    public static final String CORRECT_SECOND_LOGIN = RandomStringUtils.randomAlphabetic(10);
    public static final String CORRECT_THIRD_LOGIN = RandomStringUtils.randomAlphabetic(10);
    public static final String CORRECT_FOURTH_LOGIN = RandomStringUtils.randomAlphabetic(10);
    public static final String CORRECT_FIFTH_LOGIN = RandomStringUtils.randomAlphabetic(10);
    public static final String CORRECT_DUPLICATE_LOGIN = RandomStringUtils.randomAlphabetic(10);
    public static final String MISSING_LOGIN = null;
    public static final String EMPTY_LOGIN = "";
    public static final String CORRECT_PASSWORD = RandomStringUtils.randomAlphabetic(10);
    public static final String MISSING_PASSWORD = null;
    public static final String EMPTY_PASSWORD = "";
    public static final String CORRECT_FIRSTNAME = RandomStringUtils.randomAlphabetic(10);
    public static final String MISSING_FIRSTNAME = null;
    public static final String EMPTY_FIRSTNAME = "";

}
