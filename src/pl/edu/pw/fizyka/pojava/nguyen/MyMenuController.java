package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class MyMenuController implements ActionListener {
	private LeftSubPanelGrid leftSubPanelGrid;
	private SirCalculator sirCalculator;
	private RightPanel rightPanel;
	
	public MyMenuController(LeftSubPanelGrid leftSubPanelGrid, SirCalculator sirCalculator, RightPanel rightPanel) {
		this.leftSubPanelGrid = leftSubPanelGrid;
		this.sirCalculator = sirCalculator;
		this.rightPanel = rightPanel;
	}
	
	private int writeStringToFile(String string) {
		if(string == null) {
			return -1;
		}
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
        return 0;
	}

	private String arrayToString(double[][] array) {
		if(sirCalculator.getTotalSIROverTime() != null) {
			StringBuilder sb = new StringBuilder();
	        sb.append(formatCoordinates(sirCalculator.getInitialDistX(),sirCalculator.getInitialDistY())+";;"+"\n");
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
		} else {
			return null;
		}
        
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
	
	private void setParametersFromFile() {
		// Create a file chooser
        JFileChooser fileChooser = new JFileChooser();
        
        // Show the file chooser dialog
        int result = fileChooser.showOpenDialog(null);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File file = fileChooser.getSelectedFile();
            
            try {
                // Create a BufferedReader to read the file
                BufferedReader reader = new BufferedReader(new FileReader(file));
                
                // Read the contents of the file
                String line = reader.readLine();
                
                // Split the line by "#" to separate each number
                String[] parameters = line.split("#");
                
                // Store each number into separate variables
                if(parameters.length == 8) {
                	String transRate = parameters[0];
                    String recovRate = parameters[1];
                    String gridSizeM = parameters[2];
                    String gridSizeN = parameters[3];
                    String numOfSims = parameters[4];
                    String simTime = parameters[5];
                    String animationSpeed = parameters[6];
                    String initialDist = parameters[7];
                    rightPanel.setParameters(transRate, recovRate, gridSizeM, gridSizeN, numOfSims, simTime, animationSpeed, initialDist);
                } else {
                	JOptionPane.showMessageDialog(null, "Wrong file format", "Error", JOptionPane.ERROR_MESSAGE);
                }                                                                                           
                // Close the reader
                reader.close();
            } catch (IOException e) {
            	JOptionPane.showMessageDialog(null, "Error opening file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
        	 JOptionPane.showMessageDialog(null, "No file selected.", "Canceled", JOptionPane.INFORMATION_MESSAGE);
        }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Clear parameters and grid")) {
			rightPanel.setParameters("","","","","","","1","");
			leftSubPanelGrid.resetAnimationPanel();
		}
		else if(e.getActionCommand().equals("Show graphs")) {
			if(sirCalculator.getTotalSIROverTime() != null) {
				// Create a dataset and add the data points
		        XYSeries susceptibleSeries = new XYSeries("Susceptible");
		        XYSeries infectedSeries = new XYSeries("Infected");
		        XYSeries removedSeries = new XYSeries("Removed");
		        int ii = 0;
		        for (double[] point : sirCalculator.getTotalSIROverTime()) {	        	
		        	susceptibleSeries.add(ii, point[0]);
		        	infectedSeries.add(ii, point[1]);
		        	removedSeries.add(ii, point[2]);
		        	ii++;
		        }
		        XYSeriesCollection dataset = new XYSeriesCollection();
		        dataset.addSeries(susceptibleSeries);
		        dataset.addSeries(infectedSeries);
		        dataset.addSeries(removedSeries);

		        // Create the scatter plot using the dataset
		        JFreeChart chart = ChartFactory.createScatterPlot(
		                "Average SIR over time",  // Chart title
		                "Time (arbitrary)",                // X-axis label
		                "Number of individuals",                // Y-axis label
		                dataset,                 // Dataset
		                PlotOrientation.VERTICAL,
		                true,                    // Include legend
		                true,                    // Include tooltips
		                false                    // Include URLs
		        );

		        // Customize the plot (if needed)
		        XYPlot plot = (XYPlot) chart.getPlot();
		        // Additional customization can be done here
		        XYItemRenderer renderer = plot.getRenderer();
		        renderer.setSeriesPaint(0, new Color(50, 230, 113)); 
		        renderer.setSeriesPaint(1, new Color(245, 60, 60));
		        renderer.setSeriesPaint(2, new Color(44, 54, 120));
		        plot.setBackgroundPaint(new Color(240, 240, 240));
		        plot.setDomainGridlinePaint(Color.BLACK);
		        plot.setRangeGridlinePaint(Color.BLACK);
		        
		        // Display the chart in a frame
		        JFrame frame = new JFrame("SIR graphs");
		        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		        frame.getContentPane().add(new ChartPanel(chart), BorderLayout.CENTER);
		        frame.pack();
		        frame.setVisible(true);
			} else {
				JOptionPane.showMessageDialog(null, "No simulation data to show a graph.", "No simulation data", JOptionPane.INFORMATION_MESSAGE);
			}
	        
	    }
		
		else if(e.getActionCommand().equals("Read parameters from file")) {
			setParametersFromFile();
		}
		else if(e.getActionCommand().equals("Save simulation data to file")) {
			if(writeStringToFile(arrayToString(sirCalculator.getTotalSIROverTime())) == -1) {
				JOptionPane.showMessageDialog(null, "No simulation data to be saved.", "No simulation data", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else if(e.getActionCommand().equals("Toggle cell borders")) {
			if(!leftSubPanelGrid.isCellBorderSet) {
				leftSubPanelGrid.isCellBorderSet = true;				
			} else if(leftSubPanelGrid.isCellBorderSet) {
				leftSubPanelGrid.isCellBorderSet = false;				
			}		
		}
		else if(e.getActionCommand().equals("Set parameters to default")) {
			rightPanel.setParameters("0.3","0.2","35","35","1","70","15","17,17");			
		}
		
	}
}
