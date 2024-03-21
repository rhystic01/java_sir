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
    	
        clearParametersItem = new JMenuItem("Wyczyść parametry"); 
        showGraphsItem = new JMenuItem("Pokaż wykresy");
        readFromFileItem = new JMenuItem("Wczytaj parametry z pliku");
        saveToFileItem = new JMenuItem("Zapisz dane symulacji do pliku");
        
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
