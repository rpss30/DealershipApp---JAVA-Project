package ui.panels;

import model.Car;
import model.Dealership;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.Vector;

// Represents a panel that displays all the cars in the dealership in a table
public class DirectoryTab extends JPanel implements ActionListener {
    private Dealership dealership;
    private WarehouseTab warehouse;
    private JTable table;
    private JScrollPane scrollPane;
    private JPanel labelAndText;
    private JButton addButton;
    private JButton removeButton;
    private JButton sellButton;
    private JTextField model;
    private JTextField year;
    private JTextField fuel;
    private JTextField mpg;

    // EFFECTS: constructs a panel that displays all the cars in the dealership in table and has buttons
    public DirectoryTab(Dealership dealership, WarehouseTab warehouse) {
        this.dealership = dealership;
        this.warehouse = warehouse;
        setLayout(new GridLayout(2,1));
        setUpDirectory();
    }

    // EFFECTS: sets up the directory
    public void setUpDirectory() {
        initializeTable();
        addButtons();
    }

    // MODIFIES: this
    // EFFECTS: constructs a non-editable table that displays all the cars displayed on this panel
    public void initializeTable() {
        DefaultTableModel model = new DefaultTableModel(rowData(), columnData());
        table = new JTable(model) {
            public boolean isCellEditable(int rowIndex, int colIndex) {
                return false; //Disallow the editing of any cell
            }
        };
        scrollPane = new JScrollPane(table);
        this.add(scrollPane, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds buttons to add, remove, or sell a car to this panel
    public void addButtons() {
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add car");
        removeButton = new JButton("Remove car");
        sellButton = new JButton("Sell car");

        buttonPanel.add(addButton);
        addButton.addActionListener(this);
        buttonPanel.add(removeButton);
        removeButton.addActionListener(this);
        buttonPanel.add(sellButton);
        sellButton.addActionListener(this);

        this.add(buttonPanel);
    }

    // EFFECTS: returns a vector of vectors which holds data to make rows for the table
    public Vector<Vector> rowData() {
        Vector<Vector> rows = new Vector<>();
        List<Car> cars = dealership.allCars();
        for (Car car : cars) {
            Vector<String> row = new Vector<>();
            row.addElement(car.getModel());
            row.addElement(String.valueOf(car.getYear()));
            row.addElement(car.getFuelType());
            row.addElement(String.valueOf(car.getMpg()));
            row.addElement(saleStatus(car));
            rows.add(row);
        }
        return rows;
    }

    // EFFECTS: returns a vector of strings which holds data for column names for the table
    public Vector<String> columnData() {
        Vector<String> columnNames = new Vector<>();
        columnNames.addElement("Model");
        columnNames.addElement("Year");
        columnNames.addElement("Fuel");
        columnNames.addElement("Miles Per Gallon");
        columnNames.addElement("Sale Status");
        return columnNames;
    }

    // EFFECTS: returns "Sold" is car has been sold, returns "In Sale" otherwise
    public String saleStatus(Car car) {
        if (car.isSold()) {
            return "Sold";
        } else {
            return "In Sale";
        }
    }

    @Override
    // MODIFIES: this, warehouse
    // EFFECTS: on clicking the respective buttons,
    //             adds a new car to the dealership,
    //             saves current application state, or
    //             quits the application
    //          updates all the other panels at the end
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addCarDialog();
        } else if (e.getSource() == removeButton) {
            removeCar();
        } else if (e.getSource() == sellButton) {
            sellCar();
        }
        update();
        warehouse.update();
    }

    // MODIFIES: dealership
    // EFFECTS: removes the selected car in the table from the dealership and the table
    public void removeCar() {
        if (!table.getSelectionModel().isSelectionEmpty()) {
            int carVector = table.getSelectedRow();
            List<Car> cars = dealership.allCars();
            Car car = cars.get(carVector);
            dealership.removeCar(car);
            ((DefaultTableModel)table.getModel()).removeRow(carVector);
        }
    }

    // EFFECTS: sells the selected car in the table
    public void sellCar() {
        if (!table.getSelectionModel().isSelectionEmpty()) {
            int carVector = table.getSelectedRow();
            List<Car> cars = dealership.allCars();
            Car car = cars.get(carVector);
            if (!car.isSold()) {
                car.sellCar();
            } else {
                JOptionPane.showMessageDialog(this,
                        "This car has already been sold. Please try with a different car...",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // EFFECTS: prompts user with a dialog box to enter information about new car to add
    private void addCarDialog() {
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
    private void processOption(int i) {
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
    private boolean validInput() {
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

    // MODIFIES: this
    // EFFECTS: removes all components and creates them again
    public void update() {
        this.removeAll();
        initializeTable();
        addButtons();
    }
}
