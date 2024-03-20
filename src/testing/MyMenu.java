package testing;

import java.awt.event.ActionListener;

import javax.swing.*;

@SuppressWarnings("serial")
public class MyMenu extends JMenu {
	private JMenuItem clearParametersItem, showGraphsItem, readFromFileItem, saveToFileItem;
	private ActionListener clearParametersListener, showGraphsListener, readFromFileListener, saveToFileListener;
    public MyMenu(ActionListener clearParametersListener, ActionListener showGraphsListener, ActionListener readFromFileListener, ActionListener saveToFileListener) {
    	super("Options"); 
    	
    	this.clearParametersListener = clearParametersListener;
    	this.showGraphsListener = showGraphsListener;
    	this.readFromFileListener = readFromFileListener;
    	this.saveToFileListener = saveToFileListener;
    	
        clearParametersItem = new JMenuItem("Wyczyść parametry"); 
        showGraphsItem = new JMenuItem("Pokaż wykresy");
        readFromFileItem = new JMenuItem("Wczytaj parametry z pliku");
        saveToFileItem = new JMenuItem("Zapisz dane symulacji do pliku");
        
        clearParametersItem.addActionListener(clearParametersListener);
        showGraphsItem.addActionListener(showGraphsListener);
        readFromFileItem.addActionListener(readFromFileListener);
        saveToFileItem.addActionListener(saveToFileListener);

        // Add menu items to the menu
        add(clearParametersItem);
        add(showGraphsItem);
        add(readFromFileItem);
        add(saveToFileItem);
        
    }
    
}
