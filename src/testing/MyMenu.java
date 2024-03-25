package testing;

import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MyMenu extends JMenu {
	private JMenuItem clearParametersItem, showGraphsItem, readFromFileItem, saveToFileItem;
	private ActionListener myMenuController;
    public MyMenu(ActionListener myMenuController) {
    	super("Options"); 
    	
    	this.myMenuController = myMenuController;
    	
        clearParametersItem = new JMenuItem("Clear parameters"); 
        showGraphsItem = new JMenuItem("Show graphs");
        readFromFileItem = new JMenuItem("Read parameters from file");
        saveToFileItem = new JMenuItem("Save simulation data to file");
        
        clearParametersItem.addActionListener(myMenuController);
        showGraphsItem.addActionListener(myMenuController);
        readFromFileItem.addActionListener(myMenuController);
        saveToFileItem.addActionListener(myMenuController);

        // Add menu items to the menu
        add(clearParametersItem);
        add(showGraphsItem);
        add(readFromFileItem);
        add(saveToFileItem);
        
    }
    
}
