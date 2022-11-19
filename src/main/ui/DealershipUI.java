package ui;

import model.Dealership;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.DirectoryTab;
import ui.tabs.WarehouseTab;
import ui.tabs.HomeTab;

import javax.swing.*;
import java.io.IOException;

// Runs the RunnerApp application
public class DealershipUI extends JFrame {
    private static int WIDTH = 500;
    private static int HEIGHT = 500;
    private HomeTab homeTab;
    private WarehouseTab warehouse;
    private DirectoryTab directory;
    private JTabbedPane tabbedPane;
    private Dealership dealership;
    private final JsonWriter writer;
    private final JsonReader reader;
    private static final String JSON_STORE = "./data/dealership.json";

    public static void main(String[] args) {
        new DealershipUI();
    }

    //MODIFIES: this
    //EFFECTS: sets up and displays the Dealership graphical user interface
    private DealershipUI() {
        writer = new JsonWriter(JSON_STORE);
        reader = new JsonReader(JSON_STORE);
        setSize(WIDTH, HEIGHT);
        setTitle("DealershipApp");
        loadFilePrompt();
        warehouse = new WarehouseTab(dealership);
        directory = new DirectoryTab(dealership, warehouse);
        homeTab = new HomeTab(dealership, this, directory, warehouse);
        createTabbedPane();
        add(tabbedPane);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent e) {
                homeTab.quitOptionsDialog();
            }
        });
        setVisible(true);
    }

    // EFFECTS: creates a tabbed pane at the top of the frame with panels
    public void createTabbedPane() {
        tabbedPane = new JTabbedPane();

        tabbedPane.addTab("Main Menu", null, homeTab, null);
        tabbedPane.addTab("Directory", null, directory, null);
        tabbedPane.addTab("Warehouse", null, warehouse, null);
    }

    // MODIFIES: dealership
    // EFFECTS: prompts user with the option to load existing file
    //              if YES, then loads existing dealership
    //              if NO, prompts user to choose a brand and instantiates a new dealership
    private void loadFilePrompt() {
        int i = JOptionPane.showConfirmDialog(this,
                "Would you like to load an existing file?",
                "Load data",
                JOptionPane.YES_NO_OPTION);
        if (i == 0) {
            try {
                dealership = reader.readDealership();
                JOptionPane.showMessageDialog(this,
                        "Welcome back to your dealership!",
                        "Welcome", JOptionPane.PLAIN_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this,
                        "Could not load file",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                chooseBrand();
            }
        } else {
            chooseBrand();
        }
    }

    // MODIFIES: dealership
    // EFFECTS: prompts user to choose a brand for their dealership
    //              if entered input is not alphabetical, then displays error message and asks input again
    //              else, instantiates a new dealership with the given brand name
    private void chooseBrand() {
        String brand = JOptionPane.showInputDialog("Please select a brand for your dealership");
        while (!brand.matches("[a-zA-Z]+")) {
            JOptionPane.showMessageDialog(this,
                    "Please only include alphabets",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            brand = JOptionPane.showInputDialog("Please select a brand for your dealership");
        }
        dealership = new Dealership(brand.toUpperCase());
        JOptionPane.showMessageDialog(this,
                "Welcome to your new dealership!",
                "Welcome", JOptionPane.PLAIN_MESSAGE);
    }
}
