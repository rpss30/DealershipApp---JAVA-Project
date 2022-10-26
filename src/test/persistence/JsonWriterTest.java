package persistence;

import model.Car;
import model.Dealership;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Based on the test class JsonWriterTest in the supplied Workroom example for CPSC 210:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
public class JsonWriterTest extends JsonTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Dealership dealership = new Dealership("My dealership");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.openWriter();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyDealership() {
        try {
            Dealership dealership = new Dealership("some brand");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyDealership.json");
            writer.openWriter();
            writer.writeFile(dealership);
            writer.closeWriter();

            JsonReader reader = new JsonReader("./data/testWriterEmptyDealership.json");
            dealership = reader.readDealership();
            assertEquals("some brand", dealership.getBrand());
            assertEquals(0, dealership.numCars());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralDealership() {
        try {
            Dealership dealership = new Dealership("some brand");
            dealership.addCar(new Car("model1", 2000, "petrol", 25.8));
            dealership.addCar(new Car("model2", 2011, "diesel", 38.2));
            dealership.addCar(new Car("model3", 2022, "electric", 41.4));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralDealership.json");
            writer.openWriter();
            writer.writeFile(dealership);
            writer.closeWriter();

            JsonReader reader = new JsonReader("./data/testWriterGeneralDealership.json");
            dealership = reader.readDealership();
            assertEquals("some brand", dealership.getBrand());
            List<Car> cars = dealership.getAllCars();
            assertEquals(3, cars.size());
            checkCar(cars.get(0), "model1", 2000, "petrol", 25.8);
            checkCar(cars.get(1), "model2", 2011, "diesel", 38.2);
            checkCar(cars.get(2), "model3", 2022, "electric", 41.4);

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
