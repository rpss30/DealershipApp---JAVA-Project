package ui;

import javax.swing.*;

public class DealershipEditor extends JFrame {
    public static final int WIDTH = 700;
    public static final int HEIGHT = 540;
    private ControlPanel controlPanel;
    private WelcomePanel welcomePanel;

    public DealershipEditor() {
        controlPanel = new ControlPanel();
        welcomePanel = new WelcomePanel();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Dealership");
        setLayout(null);
        setSize(WIDTH, HEIGHT);
        controlPanel.setBounds(10,100,WIDTH - 20,400);
        welcomePanel.setBounds(10,10,WIDTH - 20,80);
        add(controlPanel);
        add(welcomePanel);
        setVisible(true);
    }
}
