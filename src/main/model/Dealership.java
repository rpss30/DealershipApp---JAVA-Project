package model;

import java.util.ArrayList;
import java.util.List;

// Represents a dealership consisting of cars that are either sold or not
public class Dealership {

    private ArrayList<Car> cars;
    private String brand;

    // EFFECTS: constructs a dealership with given name and an empty list of cars
    public Dealership(String name) {
        brand = name;
        cars = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds a car to the dealership
    public void addCar(Car car) {
        cars.add(car);
    }

    // MODIFIES: this
    // EFFECTS: removes a car from the dealership
    public void removeCar(Car car) {
        cars.remove(car);
    }

    // EFFECTS: returns the total number of cars in the dealership
    public int numCars() {
        return cars.size();
    }

    // EFFECTS: returns a list of all the sold cars
    public List<Car> allSoldCars() {
        List<Car> soldCars = new ArrayList<>();
        for (Car car : cars) {
            if (car.isSold()) {
                soldCars.add(car);
            }
        }
        return soldCars;
    }

    // EFFECTS: returns the total number of sold cars in the dealership
    public int numSoldCars() {
        return allSoldCars().size();
    }

    // EFFECTS: returns the name of dealership
    public String getBrand() {
        return brand;
    }
}
