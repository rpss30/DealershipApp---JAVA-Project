package model;

// Represents a car with given features and sale status
public class Car {

    private String model;
    private int make;
    private String fuelType;
    private double mpg;
    private boolean sold;

    // REQUIRES: fuelTYpe == "petrol" | "diesel" | "electric", model.length >= 0,
    //           2000 <= make <= 2022 and 10.0 <= mpg <= 50.0
    // EFFECTS: constructs a car with given model, make, fuelType, mpg
    // which has not been sold yet
    public Car(String model, int make, String fuelType, double mpg) {
        this.model = model;
        this.make = make;
        this.fuelType = fuelType;
        this.mpg = mpg;
        sold = false;
    }

    // EFFECTS: returns the model of car
    public String getModel() {
        return model;
    }

    // EFFECTS: returns make of the car
    public int getMake() {
        return make;
    }

    // EFFECTS: returns the make of car
    public String getFuelType() {
        return fuelType;
    }

    // EFFECTS: returns true if the car has been sold
    public boolean isSold() {
        return sold;
    }

    // returns mpg of the car
    public double getMpg() {
        return mpg;
    }

    // MODIFIES: this
    // EFFECTS: marks the car as sold
    public void sellCar() {
        sold = true;
    }
}
