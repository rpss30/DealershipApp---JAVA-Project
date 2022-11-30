package model;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a dealership with a brand name consisting of cars that are either sold or not
public class Dealership {
    private ArrayList<Car> cars;
    private String brand;

    // EFFECTS: constructs a dealership with given name and an empty list of cars
    public Dealership(String name) {
        brand = name;
        brand.toUpperCase();
        cars = new ArrayList<>();
        EventLog.getInstance().logEvent(new Event("Created a new " + getBrand() + " dealership"));
    }

    // MODIFIES: this
    // EFFECTS: adds a car to the dealership and sets its id number
    public void addCar(Car car) {
        cars.add(car);
        String model = car.getModel().substring(0, 1).toUpperCase() + car.getModel().substring(1);
        String fuel = car.getFuelType().substring(0, 1).toUpperCase() + car.getFuelType().substring(1);
        EventLog.getInstance().logEvent(new Event("Added "
                + model + " (" + car.getYear() + "), " + fuel + ", to the dealership"));
    }

    // MODIFIES: this
    // EFFECTS: removes a car from the dealership
    public void removeCar(Car car) {
        cars.remove(car);
        String model = car.getModel().substring(0, 1).toUpperCase() + car.getModel().substring(1);
        String fuel = car.getFuelType().substring(0, 1).toUpperCase() + car.getFuelType().substring(1);
        EventLog.getInstance().logEvent(new Event("Removed " + model + " (" + car.getYear() + "), "
                + fuel + ", from the dealership"));
    }

    // EFFECTS: returns the total number of cars in the dealership
    public int numCars() {
        return cars.size();
    }

    // EFFECTS: returns a list of all the sold cars
    public List<Car> soldCars() {
        List<Car> soldCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isSold()) {
                soldCars.add(car);
            }
        }
        return soldCars;
    }

    // EFFECTS: returns a list of all the unsold cars
    public List<Car> unsoldCars() {
        List<Car> unsoldCars = new ArrayList<>();
        for (Car car : cars) {
            if (!car.isSold()) {
                unsoldCars.add(car);
            }
        }
        return unsoldCars;
    }

    // EFFECTS: returns the total number of sold cars in the dealership
    public int numSoldCars() {
        return soldCars().size();
    }

    // EFFECTS: returns the name of dealership
    public String getBrand() {
        return brand;
    }

    // EFFECTS: returns a list of all the cars
    public List<Car> allCars() {
        return cars;
    }


    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Car> getAllCars() {
        return Collections.unmodifiableList(cars);
    }

    // EFFECTS: returns this as a jason object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("brand", brand);
        json.put("cars", carsToJason());
        return json;
    }

    // EFFECTS: returns cars in this dealership as a JSON array
    private JSONArray carsToJason() {
        JSONArray jsonArray = new JSONArray();

        for (Car c : cars) {
            jsonArray.put(c.toJson());
        }

        return jsonArray;
    }
}
