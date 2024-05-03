package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

public class MyMenuController implements ActionListener {
	private LeftSubPanelGrid leftSubPanelGrid;
	private SirCalculator sirCalculator;
	private RightPanel rightPanel;
	
	public MyMenuController(LeftSubPanelGrid leftSubPanelGrid, SirCalculator sirCalculator, RightPanel rightPanel) {
		this.leftSubPanelGrid = leftSubPanelGrid;
		this.sirCalculator = sirCalculator;
		this.rightPanel = rightPanel;
	}
	
	private void writeStringToFile(String string) {
		// Create a file chooser
        JFileChooser fileChooser = new JFileChooser();

        // Configure the file chooser to allow the user to select a file for saving
        fileChooser.setDialogTitle("Save File");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        // Show the file chooser dialog to the user
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File fileToSave = fileChooser.getSelectedFile();

            try {
                // Create a FileWriter to write to the selected file
                //FileWriter writer = new FileWriter(fileToSave);
                
                // Alternatively, you can use a BufferedWriter for improved performance
                 BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave));

                // Write some content to the file
                writer.write(string);
                
                // Close the writer
                writer.close();
                
                JOptionPane.showMessageDialog(null, "File saved successfully: " + fileToSave.getAbsolutePath(), "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error saving file: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (userSelection == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(null, "User canceled the file chooser dialog.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
        }
	}

	private String arrayToString(double[][] array) {		
        StringBuilder sb = new StringBuilder();
        sb.append(formatCoordinates(sirCalculator.getInitialDistX(),sirCalculator.getInitialDistY())+"\n");
        sb.append("transRate: "+sirCalculator.getTransRate()+"; recoveryRate: "+sirCalculator.getRecoveryRate()+
        		"; grid dimensions: "+sirCalculator.getGridSizeM()+" "+sirCalculator.getGridSizeN()+"\n");        
        sb.append("time;S;I;R\n");
        for (int i = 0; i < array.length; i++) {
        	sb.append(i + ";");
            for (int j = 0; j < array[i].length; j++) {           	
                sb.append(array[i][j]);

                // Add a delimiter between elements (comma, space, etc.)
                sb.append(";");
            }

            // Remove the trailing delimiter
            sb.setLength(sb.length() - 1);

            // Add a newline character after each row
            sb.append("\n");
        }

        return sb.toString();
    }

	public static String formatCoordinates(int[] xCoordinates, int[] yCoordinates) {
		StringBuilder sb = new StringBuilder();
	    sb.append("Initial infected cells:;");

	    // Iterate over the arrays simultaneously
	    for (int i = 0; i < Math.min(xCoordinates.length, yCoordinates.length); i++) {
	            // Append the x and y coordinates separated by a comma
	    	sb.append(" ").append(xCoordinates[i]).append(",").append(yCoordinates[i]);

	            // Add a semicolon after each coordinate pair
	        sb.append(";");
	    }
	    sb.setLength(sb.length() - 1);
	    return sb.toString();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear parameters and grid")) {
			rightPanel.setParametersToEmpty();
			leftSubPanelGrid.resetAnimationPanel();
		}
		else if(e.getActionCommand().equals("Show graphs")) {
			System.out.println("Show graphs");
		}
		else if(e.getActionCommand().equals("Read parameters from file")) {
			System.out.println("Read parameters from file");
		}
		else if(e.getActionCommand().equals("Save simulation data to file")) {
			writeStringToFile(arrayToString(sirCalculator.getTotalSIROverTime()));
		}
		else if(e.getActionCommand().equals("Toggle cell borders")) {
			if(!leftSubPanelGrid.isCellBorderSet) {
				leftSubPanelGrid.isCellBorderSet = true;				
			} else if(leftSubPanelGrid.isCellBorderSet) {
				leftSubPanelGrid.isCellBorderSet = false;				
			}		
		}
		else if(e.getActionCommand().equals("Set parameters to default")) {
			rightPanel.setParametersToDefault();			
		}
		
	}
}
