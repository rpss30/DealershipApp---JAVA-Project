package model;

import model.Car;
import java.util.ArrayList;

// Represents a dealership consisting of cars that are either sold or not
public class Dealership {

    private ArrayList<Car> cars;
    private String name;

    // EFFECTS: constructs a dealership with given name and an empty list of cars
    public Dealership(String name) {
        this.name = name;
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

    // EFFECTS: returns the total number of sold cars in the dealership
    public int numSoldCars() {
        int num = 0;
        for (Car car: cars) {
            if (car.isSold() == true) {
                num++;
            }
        }
        return num;
    }

    // EFFECTS: returns the name of dealership
    public String getName() {
        return name;
    }
}
