package ui.tabs;

import model.Car;
import model.Dealership;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// Represents a panel that displays all the cars in the dealership as image icons
public class WarehouseTab extends JPanel {
    private List<ImageIcon> icons;
    private Dealership dealership;

    // EFFECTS: constructs a panel that displays all the unsold cars in the dealership
    public WarehouseTab(Dealership dealership) {
        icons = new ArrayList<>();
        this.dealership = dealership;

        setLayout(new FlowLayout());
        addIcons();
        layIcons();
    }

    // EFFECTS: add an image icon for each car corresponding to the fuel type of the car to a list
    public void addIcons() {
        for (Car car : dealership.unsoldCars()) {
            if (car.getFuelType().equals("Petrol")) {
                icons.add(new ImageIcon("./data/resized_petrol_car.png"));
            } else if (car.getFuelType().equals("Diesel")) {
                icons.add(new ImageIcon("./data/resized_diesel_car.png"));
            } else if (car.getFuelType().equals("Electric")) {
                icons.add(new ImageIcon("./data/resized_electric_car.png"));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: displays all the image icons in the panel
    public void layIcons() {
        JPanel iconPanel = new JPanel();
        for (ImageIcon icon : icons) {
            JLabel label = new JLabel(icon);
            iconPanel.add(label);
        }
        this.add(iconPanel);
    }

    // MODIFIES: this
    // EFFECTS: removes all components and creates them again
    public void update() {
        this.removeAll();
        icons = new ArrayList<>();
        addIcons();
        layIcons();
    }
}