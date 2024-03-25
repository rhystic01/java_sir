package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class RightPanel extends JPanel {
	private SirCalculator sirCalculator;
	private JLabel xLabel, transRateLabel, recoveryRateLabel, gridSizeLabel, initDistributionLabel, numOfSimulationLabel, simulationTimeLabel, animationSpeedLabel;
	private JTextField transRateTextField, recoveryRateTextField, gridSizeTextFieldM, gridSizeTextFieldN, initDistributionTextField, numOfSimulationTextField, simulationTimeTextField; 
    private JButton startStopButton;
    private JSlider animationSpeedSlider;
    private JPanel gridSizeFieldsPanel;
	// contructor sets up UI elements and adds listeners
	public RightPanel(SirCalculator sirCalculator) {    	
    	this.sirCalculator = sirCalculator;
    	
    	add(Box.createRigidArea(new Dimension(0, 12)));
    	
    	// Transmission rate user input area
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	transRateLabel = new JLabel("Transmission rate");
    	add(transRateLabel);
    	transRateTextField = new JTextField("0");
        add(transRateTextField);
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        // Recovery rate user input area
        recoveryRateLabel = new JLabel("Recovery rate");
    	add(recoveryRateLabel);
        recoveryRateTextField = new JTextField("0");
        add(recoveryRateTextField);
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        // Grid size user input area
        gridSizeFieldsPanel = new JPanel();
        gridSizeFieldsPanel.setLayout(new BoxLayout(gridSizeFieldsPanel, BoxLayout.X_AXIS));
        xLabel = new JLabel("x");        
        gridSizeLabel = new JLabel("Grid size");
    	add(gridSizeLabel);       
        gridSizeTextFieldM = new JTextField("0");        
        gridSizeTextFieldN = new JTextField("0");
        gridSizeFieldsPanel.add(gridSizeTextFieldM);
        gridSizeFieldsPanel.add(Box.createRigidArea(new Dimension(2,0)));
        gridSizeFieldsPanel.add(xLabel);
        gridSizeFieldsPanel.add(Box.createRigidArea(new Dimension(2,0)));
        gridSizeFieldsPanel.add(gridSizeTextFieldN);
        add(gridSizeFieldsPanel);
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        // Initial infected distribution user input area
        initDistributionLabel = new JLabel("Initial infected distribution");
    	add(initDistributionLabel);       
        initDistributionTextField = new JTextField("0");
        add(initDistributionTextField);
        add(Box.createRigidArea(new Dimension(0, 12)));
       
        // Number of simulations user input area
        numOfSimulationLabel = new JLabel("Number of simulations");
    	add(numOfSimulationLabel);
        numOfSimulationTextField = new JTextField("0");
        add(numOfSimulationTextField);
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        // Simulation time user input area
        simulationTimeLabel = new JLabel("Simulation time");
    	add(simulationTimeLabel);
        simulationTimeTextField = new JTextField("0");
        add(simulationTimeTextField);
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Start/Stop button
        startStopButton = new JButton("Start/Stop");
        //startStopButton.addActionListener(rightPanelController);
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	sirCalculator.loadParameters(retrieveDoubleParameters(), retrieveIntParameters());
            	sirCalculator.test();
            }
        });
        add(startStopButton);
        add(Box.createRigidArea(new Dimension(0, 30)));
        
        // Animation speed slider
        animationSpeedLabel = new JLabel("Animation speed");
    	add(animationSpeedLabel);      
        add(Box.createRigidArea(new Dimension(0, 12)));        
        animationSpeedSlider = new JSlider(JSlider.HORIZONTAL, 0, 60, 1);        
        animationSpeedSlider.setMajorTickSpacing(10);
        animationSpeedSlider.setPaintTicks(true);
        animationSpeedSlider.setPaintLabels(true);
        animationSpeedSlider.setMaximumSize(new Dimension(300, animationSpeedSlider.getPreferredSize().height));
        // animationSpeedSlider.addChangeListener(rightPanelController);
        add(animationSpeedSlider);
        
        // Setting the size of text fields
        transRateTextField.setMaximumSize(new Dimension(200, transRateTextField.getPreferredSize().height));
        recoveryRateTextField.setMaximumSize(new Dimension(200, recoveryRateTextField.getPreferredSize().height));
        gridSizeTextFieldM.setMaximumSize(new Dimension(50, gridSizeTextFieldM.getPreferredSize().height));
        gridSizeTextFieldN.setMaximumSize(new Dimension(50, gridSizeTextFieldN.getPreferredSize().height));
        numOfSimulationTextField.setMaximumSize(new Dimension(200, numOfSimulationTextField.getPreferredSize().height));
        simulationTimeTextField.setMaximumSize(new Dimension(200, simulationTimeTextField.getPreferredSize().height));
        initDistributionTextField.setMaximumSize(new Dimension(200, initDistributionTextField.getPreferredSize().height));               
    }
	// local helper functions to retrieve and parse from textfield
	private double tryCatchDouble(JTextField textField) {
		double value = 0;
		try {
            // Attempt to parse the text from the text field to a double
            value = Double.parseDouble(textField.getText());
        } catch (NumberFormatException ex) {
            // Handle non-numeric input
            JOptionPane.showMessageDialog(null, "Error: Enter correct data");
            textField.setText("0");
        }
    	return value;
	}

	private int tryCatchInt(JTextField textField) {
		int value = 0;
		try {
            // Attempt to parse the text from the text field to an int
            value = Math.abs(Integer.parseInt(textField.getText()));
        } catch (NumberFormatException ex) {
            // Handle non-numeric input
            JOptionPane.showMessageDialog(null, "Error: Enter correct data");
            textField.setText("0");
        }
    	return value;
	}
	// getter functions
	private List<Integer> retrieveIntParameters() {
		 List<Integer> intParameters = new ArrayList<>();
		 intParameters.add(tryCatchInt(gridSizeTextFieldM));
		 intParameters.add(tryCatchInt(gridSizeTextFieldN));
		 intParameters.add(tryCatchInt(numOfSimulationTextField));
		 intParameters.add(tryCatchInt(simulationTimeTextField));
		 return intParameters;
	}
	
	private List<Double> retrieveDoubleParameters() {
		 List<Double> doubleParameters = new ArrayList<>();
		 doubleParameters.add(tryCatchDouble(transRateTextField));
		 doubleParameters.add(tryCatchDouble(recoveryRateTextField));		 
		 return doubleParameters;
	}	
    
}

