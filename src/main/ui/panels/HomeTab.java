package ui.panels;

import model.Car;
import model.Dealership;
import model.Event;
import model.EventLog;
import persistence.JsonWriter;
import ui.DealershipGraphicalUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents a panel which acts as the home page and holds the main menu
public class HomeTab extends JPanel implements ActionListener {
    private JLabel greeting;
    private Dealership dealership;
    private JButton addButton;
    private JButton quitButton;
    private JButton saveButton;
    private JPanel labelAndText;
    private JTextField model;
    private JTextField year;
    private JTextField fuel;
    private JTextField mpg;
    private DealershipGraphicalUI gui;
    private DirectoryTab directory;
    private WarehouseTab warehouse;
    private final JsonWriter writer;
    private static final String JSON_STORE = "./data/dealership.json";

    // EFFECTS: constructs a panel with a greeting and buttons for the main menu
    public HomeTab(Dealership dealership, DealershipGraphicalUI ui, DirectoryTab directory, WarehouseTab warehouse) {
        gui = ui;
        writer = new JsonWriter(JSON_STORE);
        this.dealership = dealership;
        this.warehouse = warehouse;
        this.directory = directory;
        setLayout(new GridLayout(2,1));
        setUpHomePanel();
    }

    // EFFECTS: sets up the home page
    public void setUpHomePanel() {
        addGreeting();
        addHomeButtons();
    }

    // MODIFIES: this
    // EFFECTS: creates greeting label at the top of this panel
    public void addGreeting() {
        greeting = new JLabel(dealership.getBrand() + " Vancouver", JLabel.CENTER);
        greeting.setSize(WIDTH, HEIGHT / 2);
        greeting.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
        this.add(greeting);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to add car, save file and quit application to this panel
    public void addHomeButtons() {
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add new car");
        quitButton = new JButton("Quit");
        saveButton = new JButton("Save ");

        buttonPanel.add(addButton);
        addButton.addActionListener(this);
        buttonPanel.add(saveButton);
        saveButton.addActionListener(this);
        buttonPanel.add(quitButton);
        quitButton.addActionListener(this);

        buttonPanel.setSize(WIDTH, HEIGHT / 2);
        this.add(buttonPanel);
    }

    @Override
    // MODIFIES: directory, warehouse
    // EFFECTS: on clicking the respective buttons,
    //             adds a new car to the dealership,
    //             saves current application state, or
    //             quits the application
    //          updates all the other panels at the end
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addCarDialog();
        } else if (e.getSource() == saveButton) {
            saveData();
        } else if (e.getSource() == quitButton) {
            quitOptionsDialog();
        }
        directory.update();
        warehouse.update();
    }

    // Based on saveWorkRoom method in the WorkRoomApp class:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo
    // EFFECTS: saves the existing state of the dealership to file
    public void saveData() {
        try {
            writer.openWriter();
            writer.writeFile(dealership);
            writer.closeWriter();
            JOptionPane.showMessageDialog(this,
                    "All changes have been saved",
                    "Data saved", JOptionPane.PLAIN_MESSAGE);
        } catch (IOException i) {
            JOptionPane.showMessageDialog(this,
                    "Could not save file",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // EFFECTS: prompts user with a dialog box to enter information about new car to add
    public void addCarDialog() {
        model = new JTextField();
        year = new JTextField();
        fuel = new JTextField();
        mpg = new JTextField();

        labelAndText = new JPanel();
        labelAndText.setLayout(new GridLayout(0, 2));
        labelAndText.add(new JLabel("Model (alphanumeric characters only): "));
        labelAndText.add(model);
        labelAndText.add(new JLabel("Year (2000 to 2022): "));
        labelAndText.add(year);
        labelAndText.add(new JLabel("Fuel (Petrol, Diesel or Electric): "));
        labelAndText.add(fuel);
        labelAndText.add(new JLabel("Miles Per Gallon (10.0 mpg to 50.0 mpg): "));
        labelAndText.add(mpg);

        int i = JOptionPane.showConfirmDialog(this, labelAndText,
                "Add Car", JOptionPane.OK_CANCEL_OPTION);

        processOption(i);
    }

    // MODIFIES: dealership
    // EFFECTS: processes the information entered by the user in the text fields,
    //              if input is valid, then adds car with entered information, follows by a confirmation dialog
    //              else error dialog appears and user is asked to try again
    public void processOption(int i) {
        if (i == 0) {
            while (!validInput()) {
                JOptionPane.showMessageDialog(this,
                        "Invalid values entered. Please try again...",
                        "Error", JOptionPane.ERROR_MESSAGE);
                int n = JOptionPane.showConfirmDialog(this, labelAndText,
                        "Add Car", JOptionPane.OK_CANCEL_OPTION);
                if (n == JOptionPane.CANCEL_OPTION) {
                    break;
                }
            }
            int carYear = Integer.parseInt(year.getText());
            double carMpg = Double.parseDouble(mpg.getText());
            String carModel = model.getText();
            String carFuel = fuel.getText();

            carModel = carModel.substring(0, 1).toUpperCase() + carModel.substring(1);
            carFuel = carFuel.substring(0, 1).toUpperCase() + carFuel.substring(1);

            Car newCar = new Car(carModel, carYear, carFuel, carMpg);
            dealership.addCar(newCar);

            addConfirmed(newCar);
        }
    }

    // EFFECTS: displays a dialog box that confirms car has been added and displays an image corresponding to
    //          the fuel type of the car
    public void addConfirmed(Car car) {
        ImageIcon petrol = new ImageIcon("./data/resized_petrol_car.png");
        ImageIcon diesel = new ImageIcon("./data/resized_diesel_car.png");
        ImageIcon electric = new ImageIcon("./data/resized_electric_car.png");
        if (car.getFuelType().equals("Petrol")) {
            JOptionPane.showMessageDialog(this,
                    "Car has been added",
                    "Car added",
                    JOptionPane.INFORMATION_MESSAGE, petrol);
        } else if (car.getFuelType().equals("Diesel")) {
            JOptionPane.showMessageDialog(this,
                    "Car has been added",
                    "Car added",
                    JOptionPane.INFORMATION_MESSAGE, diesel);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Car has been added",
                    "Car added",
                    JOptionPane.INFORMATION_MESSAGE, electric);
        }
    }

    // EFFECTS: returns true if information entered about new car to add is valid, false otherwise
    public boolean validInput() {
        boolean valid;

        if (!year.getText().matches("^\\d+$") ||  !mpg.getText().matches("[0-9]{1,13}(\\.[0-9]*)?")) {
            return false;
        } else {
            int carYear = Integer.parseInt(year.getText());
            double carMpg = Double.parseDouble(mpg.getText());
            String carFuel = fuel.getText().toLowerCase();

            boolean validModel = model.getText().matches("^[a-zA-Z0-9]+$");
            boolean validYear = carYear >= 2000 && carYear <= 2022;
            boolean validMpg = carMpg >= 10.0 && carMpg <= 50.0;
            boolean validFuel = carFuel.equals("petrol") || carFuel.equals("diesel") || carFuel.equals("electric");

            valid = validFuel && validModel && validMpg && validYear;
            return valid;
        }
    }

    // EFFECTS: prompts user with a dialog box that asks a last time to save the application state
    public void quitOptionsDialog() {
        int i = JOptionPane.showConfirmDialog(this,
                "Would you like to save your current file?",
                "Save data",
                JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            try {
                writer.openWriter();
                writer.writeFile(dealership);
                writer.closeWriter();
                JOptionPane.showMessageDialog(this,
                        "All changes have been saved. Thank you!",
                        "Data saved", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Could not save file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
        gui.dispose();
        printEventLog();
    }

    // EFFECTS: prints all the logged events up to this point on the console and then clears the EventLog
    public void printEventLog() {
        for (Event next : EventLog.getInstance()) {
            System.out.println(next.toString() + "\n\n");;
        }
        EventLog.getInstance().clear();
    }
}
