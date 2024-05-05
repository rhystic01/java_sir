package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.*;

@SuppressWarnings("serial")
public class RightPanel extends JPanel {
	@SuppressWarnings("unused")
	private SirCalculator sirCalculator;
	private JLabel xLabel, transRateLabel, recoveryRateLabel, gridSizeLabel, initDistributionLabel, numOfSimulationLabel, simulationTimeLabel, animationSpeedLabel;
	private JTextField transRateTextField, recoveryRateTextField, gridSizeTextFieldM, gridSizeTextFieldN, initDistributionTextField, numOfSimulationTextField, simulationTimeTextField; 
    private JButton startStopButton;
    private JSlider animationSpeedSlider;
    private JPanel gridSizeFieldsPanel;
    private int dataError = 0;
     
	// GUI setup
	public RightPanel(SirCalculator sirCalculator, LeftSubPanelGrid leftSubPanelGrid) {    	
    	this.sirCalculator = sirCalculator;
    	
    	add(Box.createRigidArea(new Dimension(0, 12)));
    	
    	// Transmission rate user input area
    	this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    	transRateLabel = new JLabel("Transmission rate");
    	add(transRateLabel);
    	transRateTextField = new JTextField("0.3");
        add(transRateTextField);
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        // Recovery rate user input area
        recoveryRateLabel = new JLabel("Recovery rate");
    	add(recoveryRateLabel);
        recoveryRateTextField = new JTextField("0.2");
        add(recoveryRateTextField);
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        // Grid size user input area
        gridSizeFieldsPanel = new JPanel();
        gridSizeFieldsPanel.setLayout(new BoxLayout(gridSizeFieldsPanel, BoxLayout.X_AXIS));
        xLabel = new JLabel("x");        
        gridSizeLabel = new JLabel("Grid size");
    	add(gridSizeLabel);       
        gridSizeTextFieldM = new JTextField("30");        
        gridSizeTextFieldN = new JTextField("30");
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
        initDistributionTextField = new JTextField("15,15");
        add(initDistributionTextField);
        add(Box.createRigidArea(new Dimension(0, 12)));
       
        // Number of simulations user input area
        numOfSimulationLabel = new JLabel("Number of simulations");
    	add(numOfSimulationLabel);
        numOfSimulationTextField = new JTextField("1");
        add(numOfSimulationTextField);
        add(Box.createRigidArea(new Dimension(0, 12)));
        
        // Simulation time user input area
        simulationTimeLabel = new JLabel("Simulation time");
    	add(simulationTimeLabel);
        simulationTimeTextField = new JTextField("70");
        add(simulationTimeTextField);
        add(Box.createRigidArea(new Dimension(0, 20)));
        
        // Animation speed slider
        animationSpeedLabel = new JLabel("Animation speed");
    	add(animationSpeedLabel);      
        add(Box.createRigidArea(new Dimension(0, 12)));        
        animationSpeedSlider = new JSlider(JSlider.HORIZONTAL, 1, 60, 20);        
        animationSpeedSlider.setMajorTickSpacing(9);
        animationSpeedSlider.setPaintTicks(true);
        animationSpeedSlider.setPaintLabels(true);
        animationSpeedSlider.setMaximumSize(new Dimension(300, animationSpeedSlider.getPreferredSize().height));
        add(animationSpeedSlider);
        
        // Setting the size of text fields
        transRateTextField.setMaximumSize(new Dimension(200, transRateTextField.getPreferredSize().height));
        recoveryRateTextField.setMaximumSize(new Dimension(200, recoveryRateTextField.getPreferredSize().height));
        gridSizeTextFieldM.setMaximumSize(new Dimension(50, gridSizeTextFieldM.getPreferredSize().height));
        gridSizeTextFieldN.setMaximumSize(new Dimension(50, gridSizeTextFieldN.getPreferredSize().height));
        numOfSimulationTextField.setMaximumSize(new Dimension(200, numOfSimulationTextField.getPreferredSize().height));
        simulationTimeTextField.setMaximumSize(new Dimension(200, simulationTimeTextField.getPreferredSize().height));
        initDistributionTextField.setMaximumSize(new Dimension(200, initDistributionTextField.getPreferredSize().height)); 
                     
        // Start/Stop button
        startStopButton = new JButton("Start/Stop");
        add(Box.createRigidArea(new Dimension(0, 30)));  
        add(startStopButton);      
        // Start/Stop button logic
        startStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	// First load the parameters into the SirCalculator class and set animation speed
            	sirCalculator.loadParameters(retrieveTransRate(), retrieveRecoveryRate(), retrieveGridSizeM(),
            			retrieveGridSizeN(), retrieveNumOfSimulation(), retrieveSimulationTime(),		
            			retrieveInitialDistribution("x"), retrieveInitialDistribution("y"));            	
            	leftSubPanelGrid.setAnimationSpeed(animationSpeedSlider.getValue());            	
            	
            	// Only if the loaded data is valid and threads are not running, run the calculation and display threads
            	if(dataError == 0 && isInitialDistValid() && !sirCalculator.isRunning() && !leftSubPanelGrid.isRunning()) { 
            		Thread calculationThread = new Thread(sirCalculator);
                    Thread gridDisplayThread = new Thread(leftSubPanelGrid);                    
                    calculationThread.start();
                    gridDisplayThread.start();        
                // Show error message if data is invalid    
            	} else if(dataError != 0 || !isInitialDistValid()){
            		JOptionPane.showMessageDialog(null, "Input data error: Wrong format or forbidden value",
            				"Input data error", JOptionPane.ERROR_MESSAGE);
            	// if threads are running already, stop them
            	} else if(sirCalculator.isRunning() || leftSubPanelGrid.isRunning()) {
            		sirCalculator.stop();
            		leftSubPanelGrid.stop();
            	} 
            	dataError = 0;
            }
        });                           	                            
    }
	
	// Helper functions to parse the textfield data
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
			
	// Functions returning the specified parameters from text fields	
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
	private int[] retrieveInitialDistribution(String coordinate) {
		// Define the regex pattern for the specified format
		// Matches digit(s), followed by a comma, followed by digit(s),
		// followed by zero or more occurrences of (semicolon, digit(s), comma, digit(s))
        String pattern = "\\d+,\\d+(;\\d+,\\d+)*";       
                      
        // Create a Pattern object
        Pattern p = Pattern.compile(pattern);

        // Create a Matcher object
        Matcher m = p.matcher(initDistributionTextField.getText());            
        int[] xCoordinates = new int[0];
        int[] yCoordinates = new int[0];
        // If input matches, parse the coordinates
        if (m.matches()) {       	
        	String[] pairs = initDistributionTextField.getText().split(";");
        	xCoordinates = new int[pairs.length];
        	yCoordinates = new int[pairs.length];
            for (int ii=0; ii<pairs.length; ii++) {
                String[] coordinates = pairs[ii].split(",");
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                xCoordinates[ii] = x;
                yCoordinates[ii] = y;
               
            }                                          	
        } else {
        	dataError++;
        }   
        
        
		if(coordinate == "y") {
			return yCoordinates;
		} else {
			return xCoordinates;
		}		 
	}
	
	// Function checking if initial distribution is valid for the chosen grid size
	private boolean isInitialDistValid() {
		boolean isValid = true;
		int[] initialDistX = retrieveInitialDistribution("x");
		int[] initialDistY = retrieveInitialDistribution("y");
		int maxAllowedX = retrieveGridSizeM() - 1;
		int maxAllowedY = retrieveGridSizeN() - 1;
		
		for(int xCoordinate : initialDistX) {
			if(xCoordinate > maxAllowedX || xCoordinate < 0) isValid = false;
		}
		for(int yCoordinate : initialDistY) {
			if(yCoordinate > maxAllowedY || yCoordinate < 0) isValid = false;
		}
		
		return isValid;
	}
	
	public void setParameters(String transRate, String recovRate, String gridSizeM, String gridSizeN,
			String numOfSims, String simTime, String animationSpeed, String initDistribution) {
		transRateTextField.setText(transRate);
		recoveryRateTextField.setText(recovRate);
		gridSizeTextFieldM.setText(gridSizeM);
		gridSizeTextFieldN.setText(gridSizeN);
		initDistributionTextField.setText(initDistribution);
		numOfSimulationTextField.setText(numOfSims);
		simulationTimeTextField.setText(simTime);	
		animationSpeedSlider.setValue(Integer.parseInt(animationSpeed));
	}
}

