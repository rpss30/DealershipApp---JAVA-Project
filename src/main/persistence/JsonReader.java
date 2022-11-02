package persistence;

import model.Car;
import model.Dealership;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.json.JSONArray;
import org.json.JSONObject;

// Based on the class JsonReader in the supplied Workroom example for CPSC 210:
// https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo

// Represents a reader that reads dealership from JSON data stored in file
public class JsonReader {
    private String origin;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String origin) {
        this.origin = origin;
    }

    // EFFECTS: reads dealership from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Dealership readDealership() throws IOException {
        String jsonData = readFile(origin);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseDealership(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses dealership from JSON object and returns it
    private Dealership parseDealership(JSONObject jsonObject) {
        String brand = jsonObject.getString("brand");
        Dealership ds = new Dealership(brand);
        addCars(ds, jsonObject);
        return ds;
    }

    // MODIFIES: ds
    // EFFECTS: parses cars from JSON object and adds them to dealership
    private void addCars(Dealership ds, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("cars");
        for (Object json : jsonArray) {
            JSONObject nextCar = (JSONObject) json;
            addCar(ds, nextCar);
        }
    }

    // MODIFIES: ds
    // EFFECTS: parses car from JSON object and adds it to dealership
    private void addCar(Dealership ds, JSONObject jsonObject) {
        String model = jsonObject.getString("model");
        int year = jsonObject.getInt("year");
        String fuelType = jsonObject.getString("fuelType");
        double mpg = jsonObject.getDouble("mpg");
        boolean sold = jsonObject.getBoolean("sold");
        Car car = new Car(model, year, fuelType, mpg);
        car.setSaleStatus(sold);
        ds.addCar(car);
    }
}
