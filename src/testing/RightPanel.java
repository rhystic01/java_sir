package testing;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.*;

public class RightPanel extends JPanel {
    public RightPanel() {
    	this.setLayout(new GridLayout(8,1));
        // Initialize and customize right panel components
        JLabel label = new JLabel("Right Panel");
        add(label);
        JLabel label1 = new JLabel("Right Panel 1");
        add(label1);
        this.setBackground(Color.GREEN);
    }
}

