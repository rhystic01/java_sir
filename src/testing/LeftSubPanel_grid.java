package testing;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class LeftSubPanel_grid extends JPanel {
    public LeftSubPanel_grid() {
        // Initialize and customize left panel components
        JLabel label = new JLabel("LeftSubPanel_grid");
        add(label);
        this.setBackground(Color.GRAY);
    }
}
