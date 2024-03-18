package testing;

import javax.swing.*;

public class MyMenu extends JMenu {
    public MyMenu() {
        super("Options"); 
        
        JMenuItem clearParametersItem = new JMenuItem("Clear parameters"); 
        JMenuItem showGraphsItem = new JMenuItem("Show graphs");
        JMenuItem readFromFileItem = new JMenuItem("Read parameters from a file");
        JMenuItem saveToFileItem = new JMenuItem("Save simulation data to a file");

        // Add menu items to the menu
        add(clearParametersItem);
        add(showGraphsItem);
        add(readFromFileItem);
        add(saveToFileItem);
    }
}
