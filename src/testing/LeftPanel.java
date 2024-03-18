package testing;

import java.awt.BorderLayout;

import javax.swing.*;

public class LeftPanel extends JPanel {
    public LeftPanel() {
    	this.setLayout(new BorderLayout());
        // Initialize and customize left panel components
    	LeftSubPanel_grid leftSubPanel_grid = new LeftSubPanel_grid();
    	LeftSubPanel_graph leftSubPanel_graph = new LeftSubPanel_graph();
    	add(leftSubPanel_grid, BorderLayout.CENTER);
    	add(leftSubPanel_graph, BorderLayout.SOUTH);
    }
}
