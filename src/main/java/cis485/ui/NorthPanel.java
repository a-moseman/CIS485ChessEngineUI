package cis485.ui;

import javax.swing.*;

public class NorthPanel extends JPanel {
    public NorthPanel() {
        JButton exitButton = new JButton("Exit");
        add(exitButton);
        exitButton.addActionListener(e -> System.exit(0));
    }
}
