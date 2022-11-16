package ui;

import model.Dealership;

import javax.swing.*;
import java.awt.*;

// Runs the RunnerApp application
public class Main {
    private static final int WIDTH = 550;
    private static final int HEIGHT = 750;
    private static JPanel panel;
    private static JFrame frame;
    private static JLabel label;


    public static void main(String[] args) {
        new DealershipEditor();
//        new DealershipWindow();
    }
}
