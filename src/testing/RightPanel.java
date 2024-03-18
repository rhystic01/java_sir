package testing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

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

