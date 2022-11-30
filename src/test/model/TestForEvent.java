package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestForEvent {
    private Event event;

    @BeforeEach
    public void setUp() {
        event = new Event("Added R8 (2010), Petrol, to the dealership");
    }

    @Test
    public void testEventConstructor() {
        assertEquals("Added R8 (2010), Petrol, to the dealership", event.getDescription());
    }
}
