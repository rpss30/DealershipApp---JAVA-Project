package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestForDealership {
    Dealership myShop;
    Car car1;
    Car car2;
    Car car3;

    @BeforeEach
    public void setUp() {
        myShop = new Dealership("honda");
        car1 = new Car("cr-v",2010,"diesel",12.8);
        car2 = new Car("accord", 2018, "petrol", 18.4);
        car3 = new Car("civic", 2021,"electric",25.6);
    }

    @Test
    public void testForConstructor() {
        assertEquals("honda", myShop.getBrand());
        assertEquals(0, myShop.numCars());
    }

    @Test
    public void testForAddCar() {
        myShop.addCar(car1);
        assertEquals(1, myShop.numCars());
        myShop.addCar(car2);
        assertEquals(2, myShop.numCars());
    }

    @Test
    public void testForRemoveCar() {
        myShop.addCar(car1);
        assertEquals(1, myShop.numCars());
        myShop.removeCar(car1);
        assertEquals(0, myShop.numCars());
        myShop.addCar(car1);
        myShop.addCar(car2);
        assertEquals(2, myShop.numCars());
        myShop.removeCar(car2);
        assertEquals(1, myShop.numCars());
    }

    @Test
    public void testForNumSoldCars() {
        myShop.addCar(car1);
        assertEquals(1, myShop.numCars());
        assertEquals(0, myShop.numSoldCars());
        car1.sellCar();
        assertEquals(1, myShop.numCars());
        assertEquals(1, myShop.numSoldCars());
        car2.sellCar();
        myShop.addCar(car2);
        assertEquals(2, myShop.numCars());
        assertEquals(2, myShop.numSoldCars());
    }

    @Test
    public void testForSoldCars() {
        assertTrue(myShop.soldCars().isEmpty());
        car1.sellCar();
        myShop.addCar(car1);
        assertEquals(1, myShop.soldCars().size());
        assertTrue(myShop.soldCars().contains(car1));
        car2.sellCar();
        car3.sellCar();
        myShop.addCar(car2);
        myShop.addCar(car3);
        assertEquals(3, myShop.soldCars().size());
        assertTrue(myShop.soldCars().contains(car2));
        assertTrue(myShop.soldCars().contains(car3));
    }

    @Test
    public void testForNumAllCars() {
        myShop.addCar(car1);
        assertEquals(1, myShop.numCars());
        myShop.addCar(car2);
        assertEquals(2, myShop.numCars());
    }

    @Test
    public void testForAllCars() {
        myShop.addCar(car1);
        assertEquals(1, myShop.allCars().size());
        assertTrue(myShop.allCars().contains(car1));
        myShop.addCar(car2);
        myShop.addCar(car3);
        assertEquals(3, myShop.allCars().size());
        assertTrue(myShop.allCars().contains(car2));
        assertTrue(myShop.allCars().contains(car3));
    }

    @Test
    public void testForDuplicateCars() {
        myShop.addCar(car1);
        myShop.addCar(car1);
        assertEquals(2, myShop.numCars());
    }

    @Test
    public void testForUnsoldCars() {
        myShop.addCar(car1);
        myShop.addCar(car2);
        myShop.addCar(car3);
        assertEquals(myShop.numCars(), myShop.unsoldCars().size());
        assertTrue(myShop.unsoldCars().contains(car1));
        assertTrue(myShop.unsoldCars().contains(car2));
        assertTrue(myShop.unsoldCars().contains(car3));

        car1.sellCar();
        assertEquals(myShop.numCars() - myShop.numSoldCars(), myShop.unsoldCars().size());
        assertFalse(myShop.unsoldCars().contains(car1));
        assertTrue(myShop.unsoldCars().contains(car2));
        assertTrue(myShop.unsoldCars().contains(car3));

        car2.sellCar();
        car3.sellCar();
        assertEquals(0, myShop.unsoldCars().size());
    }
}
