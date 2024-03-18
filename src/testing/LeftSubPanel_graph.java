package testing;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LeftSubPanel_graph extends JPanel {
    public LeftSubPanel_graph() {
        // Initialize and customize left panel components
        JLabel label = new JLabel("SIR graphs");
        add(label);
        this.setBackground(Color.LIGHT_GRAY);       
    }
}
