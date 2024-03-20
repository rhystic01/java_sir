package testing;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class RightPanel extends JPanel {
	//private ActionListener startStopButtonListener;
	//private ChangeListener animationSpeedSliderListener;
	private RightPanelService rightPanelService;
	private JLabel xLabel, transRateLabel, recoveryRateLabel, gridSizeLabel, initDistributionLabel, numOfSimulationLabel, simulationTimeLabel, animationSpeedLabel;
	private JTextField transRateTextField, recoveryRateTextField, gridSizeTextFieldM, gridSizeTextFieldN, initDistributionTextField, numOfSimulationTextField, simulationTimeTextField; 
    private JButton startStopButton;
    private JSlider animationSpeedSlider;
    private JPanel gridSizeFieldsPanel;
	// contructor sets up UI elements and adds listeners
	public RightPanel(/*ActionListener startStopButtonListener, ChangeListener animationSpeedSliderListener,*/ RightPanelService rightPanelService ) {
    	//this.startStopButtonListener = startStopButtonListener;
    	//this.animationSpeedSliderListener = animationSpeedSliderListener;
    	this.rightPanelService = rightPanelService;
    	
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
            	rightPanelService.calculate(getParameters());
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
	// local helper function to use with function getParameter() below
	private double tryCatchDouble(JTextField textField) {
		double value = 0;
		try {
            // Attempt to parse the text from the text field to a double
            value = Double.parseDouble(textField.getText());
        } catch (NumberFormatException ex) {
            // Handle non-numeric input
            JOptionPane.showMessageDialog(null, "Błąd: Wprowadź poprawne dane");
            textField.setText("0");
        }
    	return value;
	}
    // function to retrieve the parameter from text fields
    private List<Double> getParameters() {
    	List<Double> parameters = new ArrayList<>();
    	parameters.add(tryCatchDouble(transRateTextField));
    	parameters.add(tryCatchDouble(recoveryRateTextField));
    	parameters.add(tryCatchDouble(numOfSimulationTextField));
    	parameters.add(tryCatchDouble(simulationTimeTextField));
    	parameters.add(tryCatchDouble(gridSizeTextFieldM));
    	parameters.add(tryCatchDouble(gridSizeTextFieldN)); 
    	return parameters;
    }
    
}

