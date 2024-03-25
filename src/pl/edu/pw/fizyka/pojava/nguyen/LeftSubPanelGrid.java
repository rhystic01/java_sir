package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class LeftSubPanelGrid extends JPanel {
    public LeftSubPanelGrid() {
        JLabel label = new JLabel("Simulation animation grid");
        add(label);
        this.setBackground(Color.GRAY);
        
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
        this.setBorder(border);
    }
}
