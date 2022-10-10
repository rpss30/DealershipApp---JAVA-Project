package model;

// Represents a car with given features and sale status
public class Car {

    // REQUIRES: fuelTYpe == "petrol" | "diesel" | "electric"
    // EFFECTS: constructs a car with given model, make, fuelType, mpg
    // which has not been sold yet
    public Car(String model, int make, String fuelType, double mpg) {
        // stub
    }

    // EFFECTS: returns the model of car
    public String getModel() {
        return null; // stub
    }

    // EFFECTS: returns make of the car
    public int getMake() {
        return 0;
    }

    // EFFECTS: returns the make of car
    public String getFuelType() {
        return null; // stub
    }

    // EFFECTS: returns true if the car has been sold
    public boolean isSold() {
        return false; // stub
    }

    // returns mpg of the car
    public double getMpg() {
        return 0;
    }

    // MODIFIES: this
    // EFFECTS: marks the car as sold
    public void sellCar() {
        // stub
    }
}
