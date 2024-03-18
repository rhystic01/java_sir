package testing;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.*;

public class RightPanel extends JPanel {
    public RightPanel() {
    	add(Box.createRigidArea(new Dimension(0, 12)));
  
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Initialize and customize right panel components
    	JLabel aa1 = new JLabel("Transmission rate");
    	add(aa1);
    	JTextField label = new JTextField("Right Panel");
        add(label);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel aa2 = new JLabel("Recovery rate");
    	add(aa2);
        JTextField label1 = new JTextField("Right Panel");
        add(label1);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel aa3 = new JLabel("Grid size");
    	add(aa3);       
        JTextField label2 = new JTextField("Right Panel");
        add(label2);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel aa4 = new JLabel("Initial infected distribution");
    	add(aa4);       
        JTextField label3 = new JTextField("Right Panel");
        add(label3);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel aa5 = new JLabel("Number of simulations");
    	add(aa5);
        JTextField label4 = new JTextField("Right Panel");
        add(label4);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel aa6 = new JLabel("Simulation time");
    	add(aa6);
        JTextField label5 = new JTextField("Right Panel");
        add(label5);
        
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton label6 = new JButton("Start/Stop");
        add(label6);
        
        add(Box.createRigidArea(new Dimension(0, 30)));
        
        JLabel aa7 = new JLabel("Animation speed");
    	add(aa7);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        JSlider label7 = new JSlider(JSlider.HORIZONTAL, 0, 60, 1);
        
        
        label7.setMajorTickSpacing(10);
        label7.setPaintTicks(true);
        label7.setPaintLabels(true);
        label7.setMaximumSize(new Dimension(300, label7.getPreferredSize().height));
        add(label7);
        
        label.setMaximumSize(new Dimension(300, label.getPreferredSize().height));
        label1.setMaximumSize(new Dimension(300, label1.getPreferredSize().height));
        label2.setMaximumSize(new Dimension(300, label2.getPreferredSize().height));
        label3.setMaximumSize(new Dimension(300, label3.getPreferredSize().height));
        label4.setMaximumSize(new Dimension(300, label4.getPreferredSize().height));
        label5.setMaximumSize(new Dimension(300, label5.getPreferredSize().height));
        
       // this.setBackground(Color.WHITE);
                
    }
}

