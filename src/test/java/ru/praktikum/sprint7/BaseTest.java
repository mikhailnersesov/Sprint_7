package ru.praktikum.sprint7;

import org.junit.Before;
import ru.praktikum.sprint7.client.CourierClient;
import ru.praktikum.sprint7.step.CourierSteps;

public abstract class BaseTest {
    protected CourierSteps courierSteps;

    @Before
    public void setUp() {
        courierSteps = new CourierSteps(new CourierClient());
    }
}
