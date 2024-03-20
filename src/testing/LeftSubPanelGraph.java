package testing;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class LeftSubPanelGraph extends JPanel {
    public LeftSubPanelGraph() {
        JLabel label = new JLabel("SIR graphs");
        add(label);
        this.setBackground(Color.LIGHT_GRAY);       
    }
}
