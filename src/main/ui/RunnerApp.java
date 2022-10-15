package ui;

import model.Dealership;
import model.Car;

import java.util.Scanner;

// Dealership application
public class RunnerApp {
    private String error = "Error! Please try again:";
    private Dealership myShop;
    private Scanner input;
    private Car newCar;

    // EFFECTS: runs the dealership application
    public RunnerApp() {
        welcomeMessage();
        myShop = new Dealership(chooseBrand());
        System.out.println("Congratulations! You have a new " + myShop.getBrand() + " dealership.");
        mainMenu();
    }

    // EFFECTS: displays a welcome menu to user
    public void welcomeMessage() {
        System.out.println("Welcome to your dealership!");
        System.out.println("Please choose a brand for your dealership:");
    }

    // EFFECTS: returns brandName chosen by the user for the dealership
    public String chooseBrand() {
        input = new Scanner(System.in);
        String brandName = input.next();
        if (brandName.contains(" ")) {
            System.out.println(error);
        }
        return brandName.toUpperCase();
    }

    // EFFECTS: displays the main menu
    public void mainMenu() {
        System.out.println("Please choose from the following options:");
        System.out.println("Enter '+' to add a car");
        System.out.println("Enter '-' to remove a car");
        System.out.println("Enter 'v' to view cars");
        System.out.println("Enter 'n' to find out the number of cars");
        System.out.println("Enter 'c' to select a car");
        processInput();
    }

    // EFFECTS: processes user input
    public void processInput() {
        String toProcess = input.next();
        toProcess = toProcess.toLowerCase();
        if (toProcess.equals("+")) {
            addOneCar();
        } else if (toProcess.equals("v")) {
            viewCars();
        } else if (toProcess.equals("n")) {
            numberOfCars();
        } else if (toProcess.equals("c")) {
            selectCar();
        } else {
            System.out.println(error);
            processInput();
        }
    }

    // EFFECTS: adds a car to the dealership
    public void addOneCar() {
        System.out.println("Please enter the following information:");
        newCar = new Car(enterModel(), enterMake(), enterFuelType(), enterMpg());
        myShop.addCar(newCar);
        System.out.println("You have added the following car to your dealership:");
        getInfo(newCar);
        System.out.println("Enter 'return' to return to the main menu");
        System.out.println("Enter 'another' to add another car");
        String endInput = input.next();
        while (true) {
            if (endInput.equals("another")) {
                addOneCar();
                break;
            } else if (endInput.equals("return")) {
                mainMenu();
                break;
            } else {
                System.out.println(error);
                endInput = input.next();
            }
        }
    }

    // EFFECTS: prints out list of all cars/soldCars in the dealership
    public void viewCars() {
        // stub
    }

    // EFFECTS: prints out the total number of cars/soldCars in the dealership
    public void numberOfCars() {
        // stub
    }

    // EFFECTS: prints out information about the selected car
    public void selectCar() {
        // getInfo();
    }

    // EFFECTS: prints out information about the car
    public void getInfo(Car car) {
        System.out.println("Model: " + newCar.getModel());
        System.out.println("Make: " + newCar.getMake());
        System.out.println("Fuel Type: " + newCar.getFuelType());
        System.out.println("Miles Per Gallon (MPG): " + newCar.getMpg());
        System.out.println("I.D. Number: " + newCar.getId());
    }

    public String enterModel() {
        System.out.println("Enter car model:");
        String model = input.next();
        model = model.substring(0, 1).toUpperCase() + model.substring(1);
        return model;
    }

    public int enterMake() {
        System.out.println("Enter make year (from 2000 to 2022):");
        int make = Integer.parseInt(input.next());
        if (make < 2000 || make > 2022) {
            System.out.println(error);
            enterMake();
        }
        return make;
    }

    public String enterFuelType() {
        System.out.println("Enter fuel type (petrol, diesel or electric):");
        String fuel = input.next();
//        if (fuel.isBlank() || !fuel.equals("petrol") || !fuel.equals("diesel") || !fuel.equals("electric")) {
//            System.out.println(error);
//            enterFuelType();
//        }
        return fuel;
    }

    public double enterMpg() {
        System.out.println("Enter miles per gallon (between 10.0 to 50.0):");
        double mpg = Double.parseDouble(input.next());
        if (mpg < 10.0 || mpg > 50.0) {
            System.out.println(error);
            enterMpg();
        }
        return mpg;
    }
}
