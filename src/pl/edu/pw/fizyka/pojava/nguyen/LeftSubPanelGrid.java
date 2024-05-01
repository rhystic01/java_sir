package pl.edu.pw.fizyka.pojava.nguyen;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

@SuppressWarnings("serial")
public class LeftSubPanelGrid extends JPanel implements DataCallback {
    public LeftSubPanelGrid() {
        JLabel label = new JLabel("Simulation animation grid");
        add(label);
        this.setBackground(Color.GRAY);
        
        Border border = BorderFactory.createLineBorder(Color.DARK_GRAY, 5);
        this.setBorder(border);
       
    }
    
    @Override
    public void onDataCalculated(String[][] grid) {
    	for (int i = 0; i < grid.length; i++) {
		    for (int j = 0; j < grid[i].length; j++) {
		        System.out.print(grid[i][j] + " ");
		    }
		    System.out.println();
    	}
    	System.out.println();
    	
    }       
    
}
