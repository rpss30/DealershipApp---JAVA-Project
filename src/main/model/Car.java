package model;

// Represents a car having a model name, fuelType and whether sold or not
public class Car {

    private boolean sold;
    private String companyName;
    private String model;
    private int make;

    // REQUIRES: fuelTYpe = "petrol" | "diesel" | "electric"
    // EFFECTS: constructs a car object with given companyName, model, fuelType and sold == false
    public Car(String companyName, String model, String fuelType) {
        // stub
    }

    // EFFECTS: returns the name of the company the car belongs to
    public String getCompanyName() {
        return null; // stub
    }

    // EFFECTS: returns the model of car
    public String getModel() {
        return null; // stub
    }

    // EFFECTS: returns the make of car
    public String getFuelType() {
        return null; // stub
    }

    // EFFECTS: returns true if the car has been sold
    public boolean isSold() {
        return false; // stub
    }

    // MODIFIES: this
    // EFFECTS: mark the car as sold by setting isSold = true
    public void sellCar() {
        // stub
    }
}
