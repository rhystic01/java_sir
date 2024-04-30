package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private int dataError = 0;
     
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
        initDistributionTextField = new JTextField("0,0");
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
            	sirCalculator.loadParameters(retrieveTransRate(), retrieveRecoveryRate(), retrieveGridSizeM(), retrieveGridSizeN(),
            			retrieveNumOfSimulation(), retrieveSimulationTime(), retrieveInitialDistribution());
            	
            	System.out.println(dataError);
            	if(dataError == 0) {            		
                	sirCalculator.test();
            	} else {
            		JOptionPane.showMessageDialog(null, "Input data error: Wrong format or forbidden value", "Input data error", JOptionPane.ERROR_MESSAGE);
            	}      
            	dataError = 0;
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
        	dataError++;          
        }
    	return value;
	}

	private int tryCatchInt(JTextField textField) {
		int value = 0;
		try {
            // Attempt to parse the text from the text field to an int
            value = Math.abs(Integer.parseInt(textField.getText()));          
        } catch (NumberFormatException ex) {
        	dataError++;           
        }
    	return value;
	}
		
	
	// getter functions	
	private int retrieveGridSizeM() {
		return tryCatchInt(gridSizeTextFieldM);
	}
	private int retrieveGridSizeN() {
		return tryCatchInt(gridSizeTextFieldN);
	}
	private int retrieveNumOfSimulation() {
		return tryCatchInt(numOfSimulationTextField);
	}
	private int retrieveSimulationTime() {
		return tryCatchInt(simulationTimeTextField);
	}
	
	private double retrieveTransRate() {
		return tryCatchDouble(transRateTextField);
	}
	private double retrieveRecoveryRate() {
		return tryCatchDouble(recoveryRateTextField);
	}
	
	private List<List<Integer>> retrieveInitialDistribution() {
		// Define the regex pattern for the specified format
        String pattern = "\\d+,\\d+(;\\d+,\\d+)*"; // Matches digit(s), followed by a comma, followed by digit(s), followed by zero or more occurrences of (semicolon, digit(s), comma, digit(s))
        
        List<Integer> xCoordinates = new ArrayList<>();
        List<Integer> yCoordinates = new ArrayList<>();
        List<List<Integer>> xyCoordinatesLists = new ArrayList<>();
                      
        // Create a Pattern object
        Pattern p = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher m = p.matcher(initDistributionTextField.getText());            
        
        // If input matches, parse the coordinates
        if (m.matches()) {       	
        	String[] pairs = initDistributionTextField.getText().split(";");
            for (String pair : pairs) {
                String[] coordinates = pair.split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                xCoordinates.add(x);
                yCoordinates.add(y);
            }                                          	
        } else {
        	dataError++;
        }   
        xyCoordinatesLists.add(xCoordinates);
        xyCoordinatesLists.add(yCoordinates);
        
		return xyCoordinatesLists;
	}
	    
}

