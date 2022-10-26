package persistence;

import model.Car;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkCar(Car car, String model, int year, String fuelType, double mpg) {
        assertEquals(model, car.getModel());
        assertEquals(year, car.getYear());
        assertEquals(fuelType, car.getFuelType());
        assertEquals(mpg, car.getMpg());
    }
}