package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.event.ActionListener;
import javax.swing.*;



@SuppressWarnings("serial")
public class MyMenu extends JMenu {
	private JMenuItem clearParametersItem, showGraphsItem, readFromFileItem,
	saveToFileItem, toggleBorderItem, parametersToDefaultItem;
	@SuppressWarnings("unused")
	private ActionListener myMenuController;
    public MyMenu(ActionListener myMenuController) {
    	super("Options"); 
    	
    	this.myMenuController = myMenuController;
    	
        clearParametersItem = new JMenuItem("Clear parameters and grid"); 
        showGraphsItem = new JMenuItem("Show graphs");
        readFromFileItem = new JMenuItem("Read parameters from file");
        saveToFileItem = new JMenuItem("Save simulation data to file");
        toggleBorderItem = new JMenuItem("Toggle cell borders");
        parametersToDefaultItem = new JMenuItem("Set parameters to default");
        
        clearParametersItem.addActionListener(myMenuController);
        showGraphsItem.addActionListener(myMenuController);
        readFromFileItem.addActionListener(myMenuController);
        saveToFileItem.addActionListener(myMenuController);
        toggleBorderItem.addActionListener(myMenuController);
        parametersToDefaultItem.addActionListener(myMenuController);

        // Add menu items to the menu
        add(clearParametersItem);
        add(showGraphsItem);
        add(readFromFileItem);
        add(saveToFileItem);
        add(toggleBorderItem);
        add(parametersToDefaultItem);
        
    }
    
}
