package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestForEventLog {
    private Dealership dealership;
    private Car car1;
    private Car car2;

    @BeforeEach
    public void setUp() {
        EventLog.getInstance().clear();
        dealership = new Dealership("HONDA");
        car1 = new Car("Crv",2008,"Petrol", 14.6);
        car2 = new Car("Civic", 2015, "Diesel", 24.9);
    }

    @Test
    public void testEventLogging() {
        List<Event> events = new ArrayList<>();
        EventLog log = EventLog.getInstance();

        dealership.addCar(car1);
        dealership.addCar(car2);
        car1.sellCar();
        dealership.removeCar(car2);

        for (Event next : log) {
            events.add(next);
        }

        assertEquals("Event log cleared.", events.get(0).getDescription());
        assertEquals("Created a new HONDA dealership", events.get(1).getDescription());
        assertEquals("Added Crv (2008), Petrol, to the dealership", events.get(2).getDescription());
        assertEquals("Added Civic (2015), Diesel, to the dealership", events.get(3).getDescription());
        assertEquals("Sold Crv (2008), Petrol", events.get(4).getDescription());
        assertEquals("Removed Civic (2015), Diesel, from the dealership", events.get(5).getDescription());

        log.clear();
    }
}
