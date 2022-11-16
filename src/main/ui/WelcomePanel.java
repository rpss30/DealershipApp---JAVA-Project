package ui;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {
    private JLabel label;

    public WelcomePanel() {
//        setBackground(Color.gray);
        label = new JLabel("Welcome to your dealership!");
//        label.setForeground(Color.WHITE);
        label.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
        setLayout(new GridBagLayout());
        add(label);
    }
}
