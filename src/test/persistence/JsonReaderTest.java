package persistence;

import model.Car;
import model.Dealership;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Based on the test class JsonReaderTest in the supplied Workroom example for CPSC 210:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonReaderTest extends JsonTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Dealership dealership = reader.readDealership();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyDealership() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyDealership.json");
        try {
            Dealership dealership = reader.readDealership();
            assertEquals("some brand", dealership.getBrand());
            assertEquals(0, dealership.numCars());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralDealership() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralDealership.json");
        try {
            Dealership dealership = reader.readDealership();
            assertEquals("some brand", dealership.getBrand());
            List<Car> cars = dealership.getAllCars();
            assertEquals(3, cars.size());
            checkCar(cars.get(0), "model1", 2000, "petrol", 25.8);
            checkCar(cars.get(1), "model2", 2011, "diesel", 38.2);
            checkCar(cars.get(2), "model3", 2022, "electric", 41.4);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

}
