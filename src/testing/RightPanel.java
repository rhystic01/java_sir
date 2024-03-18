package testing;

import java.awt.Dimension;
import javax.swing.*;

@SuppressWarnings("serial")
public class RightPanel extends JPanel {
    public RightPanel() {
    	add(Box.createRigidArea(new Dimension(0, 12)));
  
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        // Initialize and customize right panel components
    	JLabel transRateLabel = new JLabel("Transmission rate");
    	add(transRateLabel);
    	JTextField transRateTextField = new JTextField("0");
        add(transRateTextField);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel recoveryRateLabel = new JLabel("Recovery rate");
    	add(recoveryRateLabel);
        JTextField recoveryRateTextField = new JTextField("0");
        add(recoveryRateTextField);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel gridSizeLabel = new JLabel("Grid size");
    	add(gridSizeLabel);       
        JTextField gridSizeTextField = new JTextField("0");
        add(gridSizeTextField);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel initDistributionLabel = new JLabel("Initial infected distribution");
    	add(initDistributionLabel);       
        JTextField initDistributionTextField = new JTextField("0");
        add(initDistributionTextField);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel numOfSimulationLabel = new JLabel("Number of simulations");
    	add(numOfSimulationLabel);
        JTextField numOfSimulationTextField = new JTextField("0");
        add(numOfSimulationTextField);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        JLabel simulationTimeLabel = new JLabel("Simulation time");
    	add(simulationTimeLabel);
        JTextField simulationTimeTextField = new JTextField("0");
        add(simulationTimeTextField);
        
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        JButton startStopButton = new JButton("Start/Stop");
        add(startStopButton);
        
        add(Box.createRigidArea(new Dimension(0, 30)));
        
        JLabel animationSpeedLabel = new JLabel("Animation speed");
    	add(animationSpeedLabel);
        
        add(Box.createRigidArea(new Dimension(0, 12)));
        JSlider animationSpeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 60, 1);
               
        animationSpeedSlider.setMajorTickSpacing(10);
        animationSpeedSlider.setPaintTicks(true);
        animationSpeedSlider.setPaintLabels(true);
        animationSpeedSlider.setMaximumSize(new Dimension(300, animationSpeedSlider.getPreferredSize().height));
        add(animationSpeedSlider);
        
        transRateTextField.setMaximumSize(new Dimension(300, transRateTextField.getPreferredSize().height));
        recoveryRateTextField.setMaximumSize(new Dimension(300, recoveryRateTextField.getPreferredSize().height));
        gridSizeTextField.setMaximumSize(new Dimension(300, gridSizeTextField.getPreferredSize().height));
        numOfSimulationTextField.setMaximumSize(new Dimension(300, numOfSimulationTextField.getPreferredSize().height));
        simulationTimeTextField.setMaximumSize(new Dimension(300, simulationTimeTextField.getPreferredSize().height));
        initDistributionTextField.setMaximumSize(new Dimension(300, initDistributionTextField.getPreferredSize().height));
        
       // this.setBackground(Color.WHITE);
                
    }
}

