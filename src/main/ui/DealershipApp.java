package ui;

import model.Dealership;
import model.Car;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Runs the dealership as an application
public class DealershipApp {
    private static final String JSON_STORE = "./data/dealership.json";
    private static final String ERROR_MESSAGE = "Error! Please try again:";
    private Dealership dealership;
    private Scanner input;
    private Car pickedCar;
    private boolean loop;
    private JsonWriter writer;
    private JsonReader reader;

    // EFFECTS: runs the dealership application
    public DealershipApp() {
        loop = true;
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        System.out.println("Welcome to your dealership!");
        welcomeMessage();
        mainMenu();
    }

    // EFFECTS: displays a welcome message to user
    public void welcomeMessage() {
        System.out.println("Please choose from the following options:\nEnter 'l' to load an existing dealership:");
        System.out.println("Enter 'c' to start a new dealership");
        String userInput = input.nextLine();
        userInput = userInput.toLowerCase();
        if (userInput.equals("l")) {
            loadDealership();
        } else if (userInput.equals("c")) {
            System.out.println("Please choose a brand for your dealership:");
            dealership = new Dealership(chooseBrand());
            System.out.println("Congratulations! You have a new " + dealership.getBrand() + " dealership.");
        } else {
            System.out.println(ERROR_MESSAGE);
            welcomeMessage();
        }
    }

    // EFFECTS: returns brandName chosen by the user for the dealership
    public String chooseBrand() {
        String brandName = input.nextLine();
        return brandName.toUpperCase();
    }

    // EFFECTS: displays the main menu
    public void mainMenu() {
        System.out.println("Please choose from the following options:");
        System.out.println("Enter '+' to add a car");
        System.out.println("Enter 'v' to view cars");
        System.out.println("Enter 'n' to find out the number of cars");
        System.out.println("Enter 's' to select a car");
        System.out.println("Enter 'k' to save dealership");
        System.out.println("Enter 'l' to load dealership");
        System.out.println("Enter 'q' to quit");
        processInput();
    }

    // EFFECTS: processes user input
    public void processInput() {
        String userInput = input.nextLine();
        userInput = userInput.toLowerCase();
        if (userInput.equals("+")) {
            addOneCar();
        } else if (userInput.equals("v")) {
            viewCars();
        } else if (userInput.equals("n")) {
            numberOfCars();
        } else if (userInput.equals("s")) {
            selectCar();
        } else if (userInput.equals("k")) {
            saveDealership();
        } else if (userInput.equals("l")) {
            loadDealership();
        } else if (userInput.equals("q")) {
            System.out.println("Goodbye!");
        } else {
            System.out.println(ERROR_MESSAGE);
            mainMenu();
        }
    }

    // EFFECTS: adds a car to the dealership
    public void addOneCar() {
        System.out.println("Please enter the following information:");
        Car newCar = new Car(enterModel(), enterMake(), enterFuelType(), enterMpg());
        dealership.addCar(newCar);
        System.out.println("You have added the following car to your dealership:");
        getInfo(newCar);
        System.out.println("Enter 'return' to return to the main menu\nEnter 'another' to add another car");
        String endInput = input.nextLine();
        while (loop) {
            if (endInput.equals("another")) {
                addOneCar();
                loop = false;
            } else if (endInput.equals("return")) {
                mainMenu();
                loop = false;
            } else {
                System.out.println(ERROR_MESSAGE);
                endInput = input.nextLine();
            }
        }
    }

    // EFFECTS: prints out list of all cars/soldCars in the dealership
    //          and provides further options with afterViewCars
    public void viewCars() {
        int i = 1;
        System.out.println("Enter 'all' to view all cars\nEnter 'sold' to view all sold cars");
        String userInput = input.nextLine();
        if (userInput.equals("all")) {
            if (dealership.allCars().isEmpty()) {
                System.out.println("There are no cars in the dealership");
            } else {
                System.out.println("Index, Model, Year, MPG");
                for (Car car : dealership.allCars()) {
                    System.out.println(i + ". " + car.getModel() + ", " + car.getYear() + ", " + car.getFuelType());
                    i++;
                }
            }
        } else if (userInput.equals("sold")) {
            viewSoldCars();
        } else {
            System.out.println(ERROR_MESSAGE);
            viewCars();
        }
        afterViewCars();
    }

    public void viewSoldCars() {
        int i = 1;
        if (dealership.soldCars().isEmpty()) {
            System.out.println("There are no sold cars in the dealership");
        } else {
            for (Car car : dealership.soldCars()) {
                System.out.println(i + ". " + car.getModel() + ", " + car.getYear() + ", " + car.getFuelType());
                i++;
            }
        }
    }

    // EFFECTS: prompts user to go back to viewCars or return to the main menu
    public void afterViewCars() {
        System.out.println("Enter 'back' to go back\nEnter 'return' to return to the main menu");
        String endInput = input.nextLine();
        loop = true;
        while (loop) {
            if (endInput.equals("back")) {
                viewCars();
                loop = false;
            } else if (endInput.equals("return")) {
                mainMenu();
                loop = false;
            } else {
                System.out.println(ERROR_MESSAGE);
                endInput = input.nextLine();
            }
        }
    }

    // EFFECTS: prints out the total number of cars/soldCars in the dealership
    //          and provides further options with afterCarNum
    public void numberOfCars() {
        System.out.println("Enter 'all' to view the count of all cars\nEnter 'sold' to view the count of sold cars");
        String userInput = input.nextLine();
        loop = true;
        while (loop) {
            if (userInput.equals("all")) {
                System.out.println("The dealership has " + dealership.numCars() + " car(s) in total");
                loop = false;
            } else if (userInput.equals("sold")) {
                System.out.println("The dealership has sold " + dealership.numSoldCars() + " car(s) in total");
                loop = false;
            } else {
                System.out.println(ERROR_MESSAGE);
                userInput = input.nextLine();
            }
        }
        afterCarNum();
    }

    // EFFECTS: prompts user to go back to numberOfCars or return to the main menu
    public void afterCarNum() {
        System.out.println("Enter 'back' to go back\nEnter 'return' to return to the main menu");
        String endInput = input.nextLine();
        loop = true;
        while (loop) {
            if (endInput.equals("back")) {
                numberOfCars();
                loop = false;
            } else if (endInput.equals("return")) {
                mainMenu();
                loop = false;
            } else {
                System.out.println(ERROR_MESSAGE);
                endInput = input.nextLine();
            }
        }
    }

    // EFFECTS: prompts user to select a car and provide further
    //          options with actionOnSelectedCar
    public void selectCar() {
        int i = 1;
        System.out.println("Please select from the following (<= " + dealership.numCars() + ")");
        for (Car car : dealership.allCars()) {
            System.out.println(i + ". " + car.getModel() + ", " + car.getYear() + ", " + car.getFuelType());
            i++;
        }
        int userInput = Integer.parseInt(input.nextLine());
        if (userInput <= dealership.numCars()) {
            pickedCar = dealership.allCars().get(userInput - 1);
            System.out.println("You have selected the following car");
            System.out.println(pickedCar.getModel() + ", " + pickedCar.getYear() + ", " + pickedCar.getFuelType());
            actionOnSelectedCar();
        } else {
            System.out.println(ERROR_MESSAGE);
            selectCar();
        }
    }

    // EFFECTS: prompts user to getInfo on the selected car, sell or remove it
    //          and provides further options with afterSelectCar
    public void actionOnSelectedCar() {
        System.out.println("Enter 'g' to get info on car\nEnter 'sell' to sell car\nEnter '-' to remove car");
        String otherUserInput = input.nextLine();
        while (loop) {
            if (otherUserInput.equals("g")) {
                getInfo(pickedCar);
                loop = false;
            } else if (otherUserInput.equals("sell")) {
                pickedCar.sellCar();
                System.out.println("You have sold the following car:");
                System.out.println(pickedCar.getModel() + ", " + pickedCar.getYear() + ", " + pickedCar.getFuelType());
                System.out.println();
                loop = false;
            } else if (otherUserInput.equals("-")) {
                System.out.println("You have removed the following car:");
                System.out.println(pickedCar.getModel() + ", " + pickedCar.getYear() + ", " + pickedCar.getFuelType());
                dealership.removeCar(pickedCar);
                loop = false;
            } else {
                System.out.println(ERROR_MESSAGE);
                selectCar();
            }
        }
        afterSelectCar();
    }

    // EFFECTS: prompts user to go back to selectCar or return to the main menu
    public void afterSelectCar() {
        System.out.println("Enter 'back' to select another car");
        System.out.println("Enter 'return' to return to the main menu");
        String endInput = input.nextLine();
        loop = true;
        while (loop) {
            if (endInput.equals("back")) {
                selectCar();
                loop = false;
            } else if (endInput.equals("return")) {
                mainMenu();
                loop = false;
            } else {
                System.out.println(ERROR_MESSAGE);
                endInput = input.nextLine();
            }
        }
    }

    // EFFECTS: prints out information about the car
    public void getInfo(Car car) {
        System.out.println("Model: " + car.getModel());
        System.out.println("Make: " + car.getYear());
        System.out.println("Fuel Type: " + car.getFuelType());
        System.out.println("Miles Per Gallon (MPG): " + car.getMpg());
        if (car.isSold()) {
            System.out.println("Sale Status: Sold");
        } else {
            System.out.println("Sale Status: In Sale");
        }
    }

    // EFFECTS: returns model for the car entered by the user
    public String enterModel() {
        System.out.println("Enter car model:");
        String model = input.nextLine();
        model = model.substring(0, 1).toUpperCase() + model.substring(1);
        return model;
    }

    // EFFECTS: returns make for the car entered by the user
    public int enterMake() {
        System.out.println("Enter make year (from 2000 to 2022):");
        int make = Integer.parseInt(input.nextLine());
        if (make < 2000 || make > 2022) {
            System.out.println(ERROR_MESSAGE);
            mainMenu();
        }
        return make;
    }

    // EFFECTS: returns type of fuel for the car entered by the user
    public String enterFuelType() {
        System.out.println("Enter fuel type (petrol, diesel or electric):");
        String fuel = input.nextLine();
        if (!(fuel.equals("petrol") || fuel.equals("diesel") || fuel.equals("electric"))) {
            System.out.println(ERROR_MESSAGE);
            mainMenu();
        }
        fuel = fuel.substring(0, 1).toUpperCase() + fuel.substring(1);
        return fuel;
    }

    // EFFECTS: returns mpg for the car entered by the user
    public double enterMpg() {
        System.out.println("Enter miles per gallon (between 10.0 to 50.0):");
        double mpg = Double.parseDouble(input.nextLine());
        if (mpg < 10.0 || mpg > 50.0) {
            System.out.println(ERROR_MESSAGE);
            mainMenu();
        }
        return mpg;
    }

    // Method based on the supplied Workroom example for CPSC 210:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves the dealership to file
    private void saveDealership() {
        try {
            writer.openWriter();
            writer.writeFile(dealership);
            writer.closeWriter();
            System.out.println("Saved your " + dealership.getBrand().toUpperCase() + " dealership to " + JSON_STORE);
            mainMenu();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // Method based on the supplied Workroom example for CPSC 210:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // MODIFIES: this
    // EFFECTS: loads dealership from file
    private void loadDealership() {
        try {
            dealership = reader.readDealership();
            System.out.println("Loaded your " + dealership.getBrand().toUpperCase() + " dealership from " + JSON_STORE);
            mainMenu();
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
