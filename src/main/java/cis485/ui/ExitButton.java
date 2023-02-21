package cis485.ui;

import javax.swing.*;

public class ExitButton extends JButton {
    public ExitButton() {
        setText("Exit");
        addActionListener(e -> System.exit(0));
    }
}
