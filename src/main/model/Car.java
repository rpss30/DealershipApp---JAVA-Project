package model;

import org.json.JSONObject;

// Represents a car with given features and sale status
public class Car {
    private String model;
    private int year;
    private String fuelType;
    private double mpg;
    private boolean sold;

    // REQUIRES: fuelTYpe == "petrol" | "diesel" | "electric", model.length >= 0,
    //           2000 <= year <= 2022 and 10.0 <= mpg <= 50.0
    // EFFECTS: constructs a car with given model, year, fuelType, mpg
    // which has not been sold yet and id = 0
    public Car(String model, int year, String fuelType, double mpg) {
        this.model = model;
        this.year = year;
        this.fuelType = fuelType;
        this.mpg = mpg;
        sold = false;
//        String carModel = this.model.substring(0, 1).toUpperCase() + this.model.substring(1);
//        String fuel = fuelType.substring(0, 1).toUpperCase() + fuelType.substring(1);
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public double getMpg() {
        return mpg;
    }

    // EFFECTS: returns true if the car has been sold
    public boolean isSold() {
        return sold;
    }

    // MODIFIES: this
    // EFFECTS: marks the car as sold
    public void sellCar() {
        sold = true;
        model = model.substring(0, 1).toUpperCase() + model.substring(1);
        String fuel = fuelType.substring(0, 1).toUpperCase() + fuelType.substring(1);
        EventLog.getInstance().logEvent(new Event("Sold " + model + " (" + year + "), " + fuel));
    }

    // MODIFIES: this
    // EFFECTS: sets sold to status
    public void setSaleStatus(boolean status) {
        sold = status;
    }


    // EFFECTS: returns this as a jason object
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("model", model);
        json.put("year", year);
        json.put("fuelType", fuelType);
        json.put("mpg", mpg);
        json.put("sold", sold);
        return json;
    }
}
