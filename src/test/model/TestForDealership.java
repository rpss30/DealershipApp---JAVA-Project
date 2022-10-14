package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestForDealership {
    Dealership myShop;
    Car car1;
    Car car2;

    @BeforeEach
    public void setUp() {
        myShop = new Dealership("honda");
        car1 = new Car("cr-v",2010,"diesel",12.8);
        car2 = new Car("accord", 2018, "petrol", 9.6);
    }

    @Test
    public void testForConstructor() {
        assertEquals("honda", myShop.getBrand());
        assertEquals(0,myShop.numCars());
    }

    @Test
    public void testForAddCar() {
        myShop.addCar(car1);
        assertEquals(1, myShop.numCars());
        assertEquals(0, myShop.numSoldCars());
    }

    @Test
    public void testForRemoveCar() {
        myShop.addCar(car1);
        assertEquals(1, myShop.numCars());
        assertEquals(0, myShop.numSoldCars());
        myShop.removeCar(car1);
        assertEquals(0, myShop.numCars());
        assertEquals(0, myShop.numSoldCars());
    }

    @Test
    public void testForNumSoldCars() {
        myShop.addCar(car1);
        assertEquals(0, myShop.numSoldCars());
        car1.sellCar();
        assertEquals(1, myShop.numSoldCars());
        car2.sellCar();
        myShop.addCar(car2);
        assertEquals(2, myShop.numSoldCars());
    }
}
