package ui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControlPanel extends JPanel {
    private List<JButton> buttons;

    public ControlPanel() {
        buttons = new ArrayList<>();
        setLayout(new GridLayout(4,1,0,5));
        buttons.add(new JButton("Add a car"));
        buttons.add(new JButton("View cars"));
        buttons.add(new JButton("Save current dealership"));
        buttons.add(new JButton("Load existing dealership"));
        for (JButton button : buttons) {
            add(button);
        }
        setVisible(true);
    }
}
