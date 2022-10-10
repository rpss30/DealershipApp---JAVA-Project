package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TestForCar {

    Car myCar;

    @BeforeEach
    public void runBefore() {
        myCar = new Car("civic", 2015, "petrol", 15.5);
    }

    @Test
    public void testForCarConstructor() {
        assertEquals("civic", myCar.getModel());
        assertEquals(2015, myCar.getMake());
        assertEquals("petrol", myCar.getFuelType());
        assertEquals(15.5, myCar.getMpg());
        assertFalse(myCar.isSold());
    }

    @Test
    public void testForSellCar() {
        myCar.sellCar();
        assertTrue(myCar.isSold());
    }
}
